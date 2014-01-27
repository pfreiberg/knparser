package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<SpojeniBMapy> spojeniBMapy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleLoaderFileExporter(List<SpojeniBMapy> spojeniBMapy,
			String prefix, String characterSet, String output) {
		this.spojeniBMapy = spojeniBMapy;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (Parser.isFirstBatch()) {
			makeControlFile();
		}
		appendLoadFile();
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

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name + ".CFG"),
					controlFile, VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controlFile;
	}

	@Override
	public void appendLoadFile() {
		try {
			File file = new File(output + prefix + name + ".TXT");
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					spojeniBMapy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
