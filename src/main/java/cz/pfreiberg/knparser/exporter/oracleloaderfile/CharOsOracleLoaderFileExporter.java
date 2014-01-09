package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.vlastnictvi.CharOs;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CharOsOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<CharOs> charOs;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "CHAR_OS";

	public CharOsOracleLoaderFileExporter(List<CharOs> charOs, String prefix,
			String characterSet, String output) {
		this.charOs = charOs;
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

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertVarcharColumn(controlFile, "OPSUB_TYPE", "10");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "3");
		controlFile = super.end(controlFile);

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name
					+ ".CFG"), controlFile,
					VfkUtil.convertEncoding(characterSet));
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
					charOs);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
