package cz.pfreiberg.knparser.exporter;


public interface Exporter {

	public boolean find(String table, String date, String dateValue, String operation);

	public void insert(String table, Object rawRecord, boolean isRecord);

	public void delete(String table, String date, String dateValue, String operation);

}
