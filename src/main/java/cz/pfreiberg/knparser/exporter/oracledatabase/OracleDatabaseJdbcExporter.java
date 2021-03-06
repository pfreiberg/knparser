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
		JdbcOperations {

	private static final Logger log = Logger
			.getLogger(OracleDatabaseJdbcExporter.class);

	protected final Connection connection;
	protected List<String> primaryKeys;
	protected List<String> methodsName;
	protected List<Object> primaryKeysValues;

	public OracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		Connection tempConnection = null;
		int attempts = 5;
		do {
			tempConnection = establishConnection(connectionParameters, attempts);
			attempts--;
		} while (tempConnection == null && attempts > 0);

		if (tempConnection == null) {
			log.fatal("Can't established connection for table " + name);
			log.info("Exiting.");
			System.exit(0);
		}

		primaryKeys = getPrimaryKeys(tempConnection, name);
		if (primaryKeys == null) {
			log.fatal("Can't fetch primary keys for table " + name + ".");
			log.info("Exiting.");
			closeConnection(tempConnection);
			System.exit(0);
		}

		connection = tempConnection;
		methodsName = getMethods(primaryKeys);
	}

	private Connection establishConnection(
			ConnectionParameters connectionParameters, int attempts) {
		try {
			return getConnection(connectionParameters);
		} catch (SQLException e) {
			log.error("Connection failed.");
			log.debug("Stack trace:", e);
			try {
				if ((attempts - 1) > 0) {
					log.info("Attempting to reconnect in 10 seconds.");
					log.info((attempts - 1) + " attempts remaining.");
					Thread.sleep(10000);
				}
			} catch (InterruptedException i) {
				Thread.currentThread().interrupt();
			}
			return null;
		}
	}

	@Override
	public final Connection getConnection(ConnectionParameters connection)
			throws SQLException {

		return DriverManager.getConnection(
				"jdbc:oracle:thin:@" + connection.getUrl(),
				connection.getUser(), connection.getPassword());

	}

	@Override
	public final void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error("Error during closing connection.");
			log.debug("Stack trace:", e);
		}
	}

	@Override
	public final void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			log.error("Error during closing prepared statement.");
			log.debug("Stack trace:", e);
		}
	}

	@Override
	public final void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			log.error("Error during closing result set.");
			log.debug("Stack trace:", e);
		}
	}

	@Override
	public final List<String> getPrimaryKeys(Connection connection, String table) {
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
			log.debug("Stack trace:", e);
			return null;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
		return output;
	}

	@Override
	public final boolean find(OracleDatabaseParameters parameters,
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
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during " + select);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	}

	@Override
	public final void delete(OracleDatabaseParameters parameters,
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
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(delete);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during " + delete);
		} finally {
			closePreparedStatement(ps);
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

	protected void executeStatement(PreparedStatement preparedStatement)
			throws SQLException {
		preparedStatement.execute();
		preparedStatement.clearParameters();
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

	private String toCamelCase(String value) {
		String[] parts = value.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return ("get" + camelCaseString);
	}

	private String toProperCase(String value) {
		return value.substring(0, 1).toUpperCase()
				+ value.substring(1).toLowerCase();
	}

}
