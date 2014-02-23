package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cz.pfreiberg.knparser.util.VfkUtil;

public class OracleDatabaseParameters {

	private Connection connection;
	private String table;
	private List<String> primaryKeys;
	private List<Object> primaryKeysValues;
	private String date;
	private String dateValue;

	public OracleDatabaseParameters(Connection connection, String table,
			List<String> primaryKeys, List<Object> primaryKeysValues,
			String date, Date dateValue) {
		this.connection = connection;
		this.table = table;
		this.primaryKeys = primaryKeys;
		this.primaryKeysValues = primaryKeysValues;
		this.date = date;
		setDateValue(dateValue);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<Object> getPrimaryKeysValues() {
		return primaryKeysValues;
	}

	public void setPrimaryKeysValues(List<Object> primaryKeysValues) {
		this.primaryKeysValues = primaryKeysValues;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}
	
	public void setDateValue(Date dateValue) {
		if (dateValue != null) {
			this.dateValue = VfkUtil.formatValueDatabase(dateValue);
		}
	}

	@Override
	public String toString() {
		return connection + "," + table + "," + primaryKeys + ","
				+ primaryKeysValues + "," + date + "," + dateValue;
	}

}
