package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.exporter.Exporter;

public class OracleDatabaseJdbcExporter implements Exporter,
		OracleDatabaseJdbcOperations {

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void delete(String table, String date, String dateValue,
			String operation) {
		throw new UnsupportedOperationException();

	}

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
	public List<String> getPrimaryKeys(Connection connection, String table) {
		List<String> output = new ArrayList<>();
		try {
			String select = "SELECT TABLE_NAME, TYP, POC_PK, PK1, PK2, PK3, PK4, PORADI FROM TABLE_INFO WHERE typ like 'HIS%' AND TABLE_NAME = '"
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
