package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cz.pfreiberg.knparser.exporter.Exporter;

public class OracleDatabaseJdbcExporter implements Exporter,
		OracleDatabaseJdbcOperations {

	@Override
	public void getConnection() {
		Connection connection = null;
/*
		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + url, username, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
		
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}*/
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
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
