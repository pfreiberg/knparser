package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PrvkyOMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<PrvkyOMapy> prvkyOMapy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "PRVKY_O_MAPY";

	public PrvkyOMapyOracleLoaderFileExporter(List<PrvkyOMapy> prvkyOMapy,
			String prefix, String characterSet, String output) {
		this.prvkyOMapy = prvkyOMapy;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (!VfkUtil.isControlFileCreated(output + prefix + name + ".CFG")) {
			makeControlFile();
		}
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "TEXT", "255");
		controlFile = super.insertColumn(controlFile, "VELIKOST");
		controlFile = super.insertColumn(controlFile, "UHEL");
		controlFile = super.insertColumn(controlFile, "VZTAZNY_BOD");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		
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
					prvkyOMapy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
