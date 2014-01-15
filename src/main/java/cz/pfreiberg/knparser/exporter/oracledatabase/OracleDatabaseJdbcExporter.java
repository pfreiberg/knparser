package cz.pfreiberg.knparser.exporter.oracledatabase;

import cz.pfreiberg.knparser.exporter.Exporter;

import oracle.jdbc.driver.OracleDriver;

public class OracleDatabaseJdbcExporter implements Exporter, OracleDatabaseJdbcOperations {

	@Override
	public void getConnection() {
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
		
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
