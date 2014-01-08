package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class MapoveListyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<MapoveListy> mapoveListy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "MAPOVE_LISTY";

	public MapoveListyOracleLoaderFileExporter(List<MapoveListy> mapoveListy,
			String prefix, String characterSet, String output) {
		this.mapoveListy = mapoveListy;
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
		controlFile = super.insertVarcharColumn(controlFile, "OZNACENI_MAPOVEHO_LISTU", "100");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "MAPA", "5");
		controlFile = super.end(controlFile);

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name + ".CFG"),
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
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					mapoveListy, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
