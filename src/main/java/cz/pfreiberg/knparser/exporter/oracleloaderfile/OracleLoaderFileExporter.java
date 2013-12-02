package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import cz.pfreiberg.knparser.exporter.Exporter;

public abstract class OracleLoaderFileExporter implements Exporter,
		OracleLoaderFileOperations {

	protected String loadFile;

	@Override
	public void makeControlFile() {
		loadFile = "LOAD DATA\nCHARACTERSET characterset_value\nINFILE \"infile_value\" \"STR '|'\"\nAPPEND\nINTO TABLE into_table_value\nFIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'";
	}

	protected void setCharacterSet(String characterSet) {
		loadFile = loadFile.replace("characterset_value", characterSet);
	}

	protected void setInfileValue(String file) {
		loadFile = loadFile.replace("infile_value", file);
	}

	protected void setIntoTable(String table) {
		loadFile = loadFile.replace("into_table_value", table);
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
