package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.parser.Parser;

public class SpojeniBMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleLoaderFileExporter(List<SpojeniBMapy> spojeniBMapy,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, spojeniBMapy);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
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
