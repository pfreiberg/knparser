package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.util.Operations;

public interface OracleDatabaseJdbcOperations {
	
	public Connection getConnection(ConnectionParameters connection) throws SQLException;
	
	public void closeConnection(Connection connection);
	
	public void closePreparedStatement(PreparedStatement preparedStatement);
	
	public void closeResultSet(ResultSet resultSet);
	
	public List<String> getPrimaryKeys(Connection connection, String table) throws SQLException;
	
	public boolean find(OracleDatabaseParameters parameters, Operations operation, boolean hasDate) throws JdbcException;
	
	public void delete(OracleDatabaseParameters parameters, Operations operation, boolean hasDate) throws JdbcException;
	
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException, JdbcException;

}
