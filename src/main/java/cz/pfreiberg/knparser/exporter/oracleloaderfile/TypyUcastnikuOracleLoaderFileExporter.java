package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;

public class TypyUcastnikuOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "TYPY_UCASTNIKU";

	public TypyUcastnikuOracleLoaderFileExporter(List<TypyUcastniku> typyUcastniku,
			String prefix, String characterSet, String output) {
		super(typyUcastniku, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "20");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
