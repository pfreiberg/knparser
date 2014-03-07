package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;

public class SpojeniBMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleLoaderFileExporter(List<SpojeniBMapy> spojeniBMapy,
			String characterSet, String output, String prefix) {
		super(spojeniBMapy, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertColumn(controlFile, "OP_ID");
		controlFile = super.insertColumn(controlFile, "DPM_ID");
		controlFile = super.insertColumn(controlFile, "HBPEJ_ID");
		controlFile = super.insertVarcharColumn(controlFile, "PARAMETRY_SPOJENI", "100");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
