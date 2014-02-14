package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TypyRizeni;
import cz.pfreiberg.knparser.parser.Parser;

public class TypyRizeniOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "TYPY_RIZENI";

	public TypyRizeniOracleLoaderFileExporter(List<TypyRizeni> typyRizeni,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, typyRizeni);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "20");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertVarcharColumn(controlFile, "ZPOPLATNENI", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}
	
}
