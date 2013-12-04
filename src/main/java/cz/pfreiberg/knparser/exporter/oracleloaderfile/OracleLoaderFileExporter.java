package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import cz.pfreiberg.knparser.exporter.Exporter;

public abstract class OracleLoaderFileExporter implements Exporter,
		OracleLoaderFileOperations {
	
	@Override
	public String makeControlFile() {
		return "LOAD DATA\n"
				+ "CHARACTERSET characterset_value\n"
				+ "INFILE \"infile_value.TXT\"\n"
				+ "APPEND\n"
				+ "INTO TABLE into_table_value\n"
				+ "FIELDS TERMINATED BY '|' ";
	}

	protected String fillFile(String loadFile, String characterSet, String file, String table) {
		String output = loadFile.replace("characterset_value", characterSet);
		output = output.replace("infile_value", file);
		output = output.replace("into_table_value", table);
		return output;
	}

	@Override
	public void appendLoadFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
