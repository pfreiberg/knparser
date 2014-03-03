package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Abstraktní třída poskytující společnou logiku pro práci nad Oracle Database.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public abstract class OracleDatabaseJdbcExporter implements Exporter,
		OracleDatabaseJdbcOperations {

	private static final Logger log = Logger
			.getLogger(OracleDatabaseJdbcExporter.class);

	protected static final int BATCH_MAX = 1000;
	protected int batchSize = 0;

	protected Connection connection;
	protected List<String> primaryKeys;
	protected List<String> methodsName;
	protected List<Object> primaryKeysValues;

	public OracleDatabaseJdbcExporter(ConnectionParameters connectionParameters) {
		establishConnection(connectionParameters);
	}

	public OracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		do {
			establishConnection(connectionParameters);
			primaryKeys = getPrimaryKeys(connection, name);
		} while (connection == null && primaryKeys == null);
		methodsName = getMethods(primaryKeys);
	}

	private void establishConnection(ConnectionParameters connectionParameters) {
		do {
			try {
				connection = getConnection(connectionParameters);
				break;
			} catch (SQLException e) {
				log.error("Connection failed.");
				log.debug("Stack trace:", e);
				log.info("Attempting to reconnect in 5 seconds.");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException i) {
					Thread.currentThread().interrupt();
				}
			}
		} while (true);
	}

	@Override
	public Connection getConnection(ConnectionParameters connection)
			throws SQLException {

		return DriverManager.getConnection(
				"jdbc:oracle:thin:@" + connection.getUrl(),
				connection.getUser(), connection.getPassword());

	}

	@Override
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error("Error during closing connection.");
			log.debug("Stack trace:", e);
		}
	}

	@Override
	public List<String> getPrimaryKeys(Connection connection, String table) {
		List<String> output = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String select = "SELECT TABLE_NAME, TYP, POC_PK, PK1, PK2, PK3, PK4, PORADI FROM TABLE_INFO WHERE TABLE_NAME = '"
					+ table + "'";
			ps = connection.prepareStatement(select);
			rs = ps.executeQuery();
			if (rs.next()) {
				int numberOfPrimaryKeys = rs.getInt("POC_PK");
				for (int i = 1; i <= numberOfPrimaryKeys; i++) {
					output.add(rs.getString("PK" + String.valueOf(i)));
				}
			}
		} catch (SQLException e) {
			log.error("Error during fetching primary keys for " + table);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error during closing connection.");
				log.debug("Stack trace:", e);
			}
		}
		return output;
	}

	@Override
	public boolean find(OracleDatabaseParameters parameters,
			Operations operation, boolean hasDate) throws JdbcException {
		String select = "";
		if (hasDate) {
			select = "SELECT * FROM " + parameters.getTable()
					+ " WHERE *pk* AND " + parameters.getDate()
					+ operation.getOperator() + parameters.getDateValue();
		} else {
			select = "SELECT * FROM " + parameters.getTable() + " WHERE *pk*";
		}

		select = composeSqlStatement(parameters, select);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(select);
			rs = ps.executeQuery();
			boolean isFound = (rs.next());
			return isFound;
		} catch (SQLException e) {
			throw new JdbcException("Error during " + select);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error during closing connection.");
				log.debug("Stack trace:", e);
			}
		}
	}

	@Override
	public void delete(OracleDatabaseParameters parameters,
			Operations operation, boolean hasDate) throws JdbcException {
		String delete = "";
		if (hasDate) {
			delete = "DELETE FROM " + parameters.getTable()
					+ " WHERE *pk* AND " + parameters.getDate()
					+ operation.getOperator() + parameters.getDateValue();
		} else {
			delete = "DELETE FROM " + parameters.getTable() + " WHERE *pk*";
		}
		delete = composeSqlStatement(parameters, delete);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(delete);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new JdbcException("Error during " + delete);
		}

	}

	protected void addToBatch(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.addBatch();
		preparedStatement.clearParameters();
		batchSize++;
		executeBatchIfFull(preparedStatement);
	}

	protected void executeBatchIfFull(PreparedStatement preparedStatement)
			throws SQLException {
		if (batchSize >= BATCH_MAX) {
			batchSize = 0;
			preparedStatement.executeBatch();
			preparedStatement.clearBatch();
		}
	}

	protected List<Object> getPrimaryKeysValues(Object record,
			List<String> methodsName) {
		List<Object> primaryKeyValues = new ArrayList<>();
		Class<?> c = null;
		for (int i = 0; i < methodsName.size(); i++) {
			try {
				c = record.getClass();
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke(c.cast(record)));
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				log.fatal("Fatal error during method call: "
						+ methodsName.get(i) + "\nClass: " + c.getName());
				log.debug("Stack trace:", e);
				closeConnection(connection);
				System.exit(1);
			}

		}
		return primaryKeyValues;
	}

	private String composeSqlStatement(OracleDatabaseParameters parameters,
			String statement) {
		for (int i = 0; i < primaryKeys.size(); i++) {
			statement = statement.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		statement = statement.replace(" AND *pk*", "");
		return statement;
	}

	private List<String> getMethods(List<String> primaryKeys) {
		List<String> methods = new ArrayList<>();
		for (int i = 0; i < primaryKeys.size(); i++) {
			methods.add(toCamelCase(primaryKeys.get(i)));
		}
		return methods;
	}

	private String toCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return ("get" + camelCaseString);
	}

	private String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

}
