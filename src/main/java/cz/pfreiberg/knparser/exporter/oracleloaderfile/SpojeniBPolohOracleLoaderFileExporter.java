package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBPoloh;

public class SpojeniBPolohOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "SPOJENI_B_POLOH";

	public SpojeniBPolohOracleLoaderFileExporter(List<SpojeniBPoloh> spojeniBPoloh,
			String prefix, String characterSet, String output) {
		super(spojeniBPoloh, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "BP_ID");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "OB_ID");
		controlFile = super.insertColumn(controlFile, "HP_ID");
		controlFile = super.insertColumn(controlFile, "DPM_ID");
		controlFile = super.insertVarcharColumn(controlFile, "PARAMETRY_SPOJENI", "100");
		controlFile = super.insertColumn(controlFile, "ZVB_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
