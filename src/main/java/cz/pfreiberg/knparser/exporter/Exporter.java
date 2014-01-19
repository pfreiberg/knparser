package cz.pfreiberg.knparser.exporter;

public interface Exporter {

	public boolean find(String table, String first, String firstValue, String second, String secondValue);

	public void insert(String table, Object rawRecord);

	public void delete(String table, String first, String firstValue, String second, String secondValue);

}
