package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

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
	
	@Override
	public void appendLoadFile(String name, String characterSet, Collection<?> lines) {
		try {
			File file = new File(name + ".TXT");
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					lines);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
