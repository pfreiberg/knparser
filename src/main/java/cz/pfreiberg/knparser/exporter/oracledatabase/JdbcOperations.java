package cz.pfreiberg.knparser.exporter.oracledatabase;

public interface JdbcOperations {
	
	public void getConnection();
	
	public void prepareStatement();
	
	public void clearStatement();
	
	public void execute();

}
