package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;

public interface OracleDatabaseJdbcOperations {
	
	public Connection getConnection(ConnectionParameters connection);
	
	public void prepareStatement();
	
	public void clearStatement();
	
	public void execute();

}
