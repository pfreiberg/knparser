package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.util.Operations;

public interface OracleDatabaseJdbcOperations {
	
	public Connection getConnection(ConnectionParameters connection);
	
	public List<String> getPrimaryKeys(Connection connection, String table);
	
	public boolean newFind(OracleDatabaseParameters parameters, Operations operation, boolean hasDate);

	public void insert(String table, Object rawRecord, boolean isRecord);
	
	public void newDelete(OracleDatabaseParameters parameters, Operations operation, boolean hasDate);

}
