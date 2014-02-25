package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.PravaStavby;

public class PravaStavbyOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "PRAVA_STAVBY";

	public PravaStavbyOracleLoaderFileExporter(List<PravaStavby> pravaStavby,
			String prefix, String characterSet, String output) {
		super(pravaStavby, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_PRIJETI");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertDateColumn(controlFile, "DATUM_UKONCENI");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
