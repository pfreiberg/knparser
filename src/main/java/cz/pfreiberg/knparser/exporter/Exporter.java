package cz.pfreiberg.knparser.exporter;

public interface Exporter {

	public boolean find(String table, String first, String firstValue, String second, String secondValue);

	public void insert();

	public void update();

}
