package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	public void prepareStatement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearStatement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean find(String table, String first, String firstValue, String second, String secondValue) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void insert(String table, Object rawRecord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
