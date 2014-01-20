package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.util.List;

public interface OracleDatabaseJdbcOperations {
	
	public Connection getConnection(ConnectionParameters connection);
	
	public List<String> getPrimaryKeys(Connection connection, String table);
	
	public void prepareStatement();
	
	public void clearStatement();
	
	public void execute();

}
