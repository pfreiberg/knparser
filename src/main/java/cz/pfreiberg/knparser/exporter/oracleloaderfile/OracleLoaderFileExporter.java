package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public abstract class OracleLoaderFileExporter implements Exporter,
		OracleLoaderFileOperations {

	private static final Logger log = Logger
			.getLogger(OracleLoaderFileExporter.class);

	public OracleLoaderFileExporter(List<?> list, String characterSet,
			String output, String prefix, String name) {

		output = output + prefix + name;
		if (Parser.isFirstBatch()) {
			String controlFileTemplate = getTemplateOfControlFile(characterSet,
					prefix + name);
			appendControlFile(output, characterSet,
					makeControlFile(controlFileTemplate));
		}
		appendLoadFile(output, characterSet, list);

	}

	private String getTemplateOfControlFile(String characterSet, String name) {
		final String termination = "'|" + Character.toString((char) 21) + "'";
		String template = "LOAD DATA\n" + "CHARACTERSET " + characterSet + "\n"
				+ "INFILE \"" + name + ".TXT\" \"STR" + termination + "\" \n"
				+ "APPEND\n" + "INTO TABLE " + name + "\n"
				+ "FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'"
				+ "\n(\n" + "columns_value\n" + ")";
		return template;
	}

	@Override
	public void appendControlFile(String name, String characterSet,
			String controlFile) {
		try {
			FileManager.writeToConfigFile(new File(name + ".CFG"), controlFile,
					VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			log.error("File " + name + ".CFG" + " was not created.");
			log.debug("Stack trace:", e);
		}

	}

	@Override
	public void appendLoadFile(String name, String characterSet,
			Collection<?> lines) {
		try {
			File file = new File(name + ".TXT");
			FileManager.writeToDataFile(file,
					VfkUtil.convertEncoding(characterSet), lines);
		} catch (IOException | ParserException e) {
			log.error("File " + name + ".TXT" + " is corrupted. Missing data.");
			log.debug("Stack trace:", e);
		}
	}

	protected String insertColumn(String loadFile, String name) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " NULLIF ( " + name + " = \"NULL\" ),\ncolumns_value");
		return output;
	}

	protected String insertVarcharColumn(String loadFile, String name,
			String value) {
		String output = loadFile.replace("columns_value", "\t" + name
				+ " CHAR(" + value + ") NULLIF ( " + name
				+ " = \"NULL\" ),\ncolumns_value");
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
