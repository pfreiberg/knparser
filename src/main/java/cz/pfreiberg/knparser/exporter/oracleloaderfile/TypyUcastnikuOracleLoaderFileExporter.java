package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;
import cz.pfreiberg.knparser.parser.Parser;

public class TypyUcastnikuOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "TYPY_UCASTNIKU";

	public TypyUcastnikuOracleLoaderFileExporter(List<TypyUcastniku> typyUcastniku,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, typyUcastniku);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "20");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
