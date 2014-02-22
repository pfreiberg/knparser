package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public abstract class OracleDatabaseJdbcExporter implements Exporter,
		OracleDatabaseJdbcOperations {

	@Override
	public Connection getConnection(ConnectionParameters connection) {
		Connection database = null;

		try {
			database = DriverManager.getConnection("jdbc:oracle:thin:@"
					+ connection.getUrl(), connection.getUser(),
					connection.getPassword());
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			return database;
		}

		if (database != null) {
			return database;
		} else {
			System.out.println("Failed to make connection!");
		}

		return database;
	}
	
	@Override
	public boolean find(OracleDatabaseParameters parameters, Operations operation, boolean hasDate) {
	
		String select = "";
		if (hasDate) {
			select = "SELECT * FROM " + parameters.getTable() + " WHERE *pk* AND " + parameters.getDate()
					+ operation.getOperator() + parameters.getDateValue();
		}
		else {
			select = "SELECT * FROM " + parameters.getTable() + " WHERE *pk*";
		}
		
		select = composeSqlStatement(parameters, select);
		
		try {
			PreparedStatement preparedStatement = parameters.getConnection()
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
	
	public void delete(OracleDatabaseParameters parameters, Operations operation, boolean hasDate) {
		
		String delete = "";
		if (hasDate) {
			delete = "DELETE FROM " + parameters.getTable() + " WHERE *pk* AND " + parameters.getDate()
				+ operation + parameters.getDateValue();
		} else {

			delete = "DELETE FROM " + parameters.getTable() + " WHERE *pk*";
		}
		
		delete = composeSqlStatement(parameters, delete);
		
		try {
			PreparedStatement preparedStatement = parameters.getConnection()
					.prepareStatement(delete);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String composeSqlStatement(OracleDatabaseParameters parameters,
			String select) {
		for (int i = 0; i < parameters.getPrimaryKeys().size(); i++) {
			select = select.replace("*pk*", parameters.getPrimaryKeys().get(i) + " = "
					+ VfkUtil.formatValueDatabase(parameters.getPrimaryKeysValues().get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		return select;
	}
		

	@Override
	public List<String> getPrimaryKeys(Connection connection, String table) {
		List<String> output = new ArrayList<>();
		try {
			String select = "SELECT TABLE_NAME, TYP, POC_PK, PK1, PK2, PK3, PK4, PORADI FROM TABLE_INFO WHERE TABLE_NAME = '"
					+ table + "'";
			ResultSet rs = connection.prepareStatement(select).executeQuery();
			if (rs.next()) {
				int numberOfPrimaryKeys = rs.getInt("POC_PK");
				for (int i = 1; i <= numberOfPrimaryKeys; i++) {
					output.add(rs.getString("PK" + String.valueOf(i)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	protected List<Object> getPrimaryKeysValues(Object record, List<String> methodsName) {
		List<Object> primaryKeyValues = new ArrayList<>();
		for (int i = 0; i < methodsName.size(); i++) {
			try {
				Class<?> c = record.getClass();
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke(c.cast(record)));
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return primaryKeyValues;
	}

	// TODO smazat?
	protected Date subtractOneSecond(Date originalDate) {
		Calendar modifiedDate = Calendar.getInstance();
		modifiedDate.setTime(originalDate);
		modifiedDate.add(Calendar.SECOND, -1);
		originalDate = modifiedDate.getTime();
		return originalDate;
	}


	protected static List<String> getMethods(List<String> primaryKeys) {
		List<String> methods = new ArrayList<>();
		for (int i = 0; i < primaryKeys.size(); i++) {
			methods.add(toCamelCase(primaryKeys.get(i)));
		}
		return methods;
	}

	private static String toCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return ("get" + camelCaseString);
	}

	private static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
}
