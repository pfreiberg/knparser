package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import cz.pfreiberg.knparser.exporter.Exporter;

public abstract class OracleLoaderFileExporter implements Exporter,
		OracleLoaderFileOperations {
	
	protected String loadFile = "LOAD DATA\nCHARACTERSET characterset_value\nINFILE \"infile_value\" \"STR '|'\"\nAPPEND\nINTO TABLE into_table_value\nFIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'";

	@Override
	public void appendLoadFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeControlFile() {
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
