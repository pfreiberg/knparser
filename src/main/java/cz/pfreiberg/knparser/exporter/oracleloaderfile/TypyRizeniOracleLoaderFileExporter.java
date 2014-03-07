package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TypyRizeni;

public class TypyRizeniOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "TYPY_RIZENI";

	public TypyRizeniOracleLoaderFileExporter(List<TypyRizeni> typyRizeni,
			String characterSet, String output, String prefix) {
		super(typyRizeni, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "20");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertVarcharColumn(controlFile, "ZPOPLATNENI", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}
	
}
