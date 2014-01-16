package cz.pfreiberg.knparser.exporter.oracledatabase;

public interface OracleDatabaseJdbcOperations {
	
	public void getConnection(ConnectionParameters connection);
	
	public void prepareStatement();
	
	public void clearStatement();
	
	public void execute();

}
