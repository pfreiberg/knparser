package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

public class OracleDatabaseParameters {

	private String table;
	private String date;
	private String dateValue;

	public OracleDatabaseParameters(String table, String date, Date dateValue) {
		this.table = table;
		this.date = date;
		setDateValue(dateValue);
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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
		return table + "," + date + "," + dateValue;
	}

}
