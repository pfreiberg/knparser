package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import cz.pfreiberg.knparser.exporter.Exporter;

public abstract class OracleLoaderFileExporter implements Exporter,
		OracleLoaderFileOperations {

	@Override
	public String makeControlFile() {
		final String termination = "'|" + Character.toString((char)21) + "'";
		return "LOAD DATA\n" + "CHARACTERSET characterset_value\n"
				+ "INFILE \"infile_value.TXT\" \"STR" + termination + "\" \n" 
				+ "APPEND\n"
				+ "INTO TABLE into_table_value\n"
				+ "FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'"  + "\n(\n" + "columns_value\n"
				+ ")";
	}

	protected String fillHeader(String loadFile, String characterSet,
			String name) {
		String output = loadFile.replace("characterset_value", characterSet);
		output = output.replace("infile_value", name);
		output = output.replace("into_table_value", name);
		return output;
	}

	protected String insertColumn(String loadFile, String name) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " NULLIF ( " + name + " = \"NULL\" ),\ncolumns_value");
		return output;
	}
	
	protected String insertVarcharColumn(String loadFile, String name, String value) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " CHAR("+ value +") NULLIF ( " + name + " = \"NULL\" ),\ncolumns_value");
		return output;
	}

	protected String insertDateColumn(String loadFile, String name) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " DATE \"DD.MM.YYYY HH24:MI:SS\" NULLIF ( " + name
				+ " = \"NULL\" ),\ncolumns_value");
		return output;
	}
	
	protected String insertZeroColumn(String loadFile, String name) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " \"0\",\ncolumns_value");
		return output;
	}

	protected String end(String loadFile) {
		String output = loadFile.replace(",\ncolumns_value", "");
		return output;
	}
	
	@Override
	public boolean find(String table, String date, String dateValue, String operation) {
		return false;

	}

	@Override
	public void insert(String table, Object rawRecord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String table, String date, String dateValue, String operation) {
		// TODO Auto-generated method stub

	}

}
