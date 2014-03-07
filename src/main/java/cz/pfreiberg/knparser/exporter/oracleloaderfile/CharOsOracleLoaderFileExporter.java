package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.vlastnictvi.CharOs;

public class CharOsOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "CHAR_OS";

	public CharOsOracleLoaderFileExporter(List<CharOs> charOs, String characterSet,
			String output, String prefix) {
		super(charOs, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertVarcharColumn(controlFile, "OPSUB_TYPE", "10");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
