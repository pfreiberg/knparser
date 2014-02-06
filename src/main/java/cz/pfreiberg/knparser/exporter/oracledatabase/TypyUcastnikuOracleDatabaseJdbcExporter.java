package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TypyUcastnikuOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<TypyUcastniku> typyUcastniku;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "TYPY_UCASTNIKU";

	public TypyUcastnikuOracleDatabaseJdbcExporter(
			List<TypyUcastniku> typyUcastniku,
			ConnectionParameters connectionParameters) {
		this.typyUcastniku = typyUcastniku;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TypyUcastniku record : typyUcastniku) {
			primaryKeysValues = getPrimaryKeysValues(record);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Object> getPrimaryKeysValues(Object record) {
		List<Object> primaryKeyValues = new ArrayList<>();
		try {
			for (int i = 0; i < methodsName.size(); i++) {
				Class<?> c = Class
						.forName("cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((TypyUcastniku) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(TypyUcastniku record) {

		if (find(name, null, null, null)) {
			delete(name, null, null, null);
			insert(name, record, true);
		} else {	
			insert(name, record, true);
		} 
	}

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		String select = "SELECT * FROM " + table + " WHERE *pk* AND ";

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			boolean isFound = preparedStatement.executeQuery().next();
			preparedStatement.close();
			return isFound;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			TypyUcastniku record = (TypyUcastniku) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3, record.getPopis());
		
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(String table, String date, String dateValue,
			String operation) {
		String delete = "DELETE FROM " + table + " WHERE *pk* AND ";
		for (int i = 0; i < primaryKeys.size(); i++) {
			delete = delete.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		delete = delete.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(delete);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}