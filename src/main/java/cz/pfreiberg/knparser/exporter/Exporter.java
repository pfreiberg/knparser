package cz.pfreiberg.knparser.exporter;


public interface Exporter {

	public boolean find(String date, String dateValue);

	public void insert(String table, Object rawRecord);

	public void delete(String date, String dateValue);

}
