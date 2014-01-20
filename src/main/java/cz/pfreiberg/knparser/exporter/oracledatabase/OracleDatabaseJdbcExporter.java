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
	
	@Override
	public boolean find(Object record) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void insert(String table, Object rawRecord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String table, String first, String firstValue,
			String second, String secondValue) {
		// TODO Auto-generated method stub

	}


}
