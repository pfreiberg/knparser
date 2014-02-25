package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBoduBp;

public class ObrazyBoduBpOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "OBRAZY_BODU_BP";

	public ObrazyBoduBpOracleLoaderFileExporter(List<ObrazyBoduBp> obrazyBoduBp,
			String prefix, String characterSet, String output) {
		super(obrazyBoduBp, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertVarcharColumn(controlFile, "TEXT", "255");
		controlFile = super.insertColumn(controlFile, "VELIKOST");
		controlFile = super.insertColumn(controlFile, "UHEL");
		controlFile = super.insertColumn(controlFile, "BP_ID");
		controlFile = super.insertVarcharColumn(controlFile, "OBBP_TYPE", "10");
		controlFile = super.insertColumn(controlFile, "VZTAZNY_BOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
