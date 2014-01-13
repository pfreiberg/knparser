package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiJedOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<ZpVyuzitiJed> zpVyuzitiJed;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ZP_VYUZITI_JED";

	public ZpVyuzitiJedOracleLoaderFileExporter(
			List<ZpVyuzitiJed> zpVyuzitiJed, String prefix,
			String characterSet, String output) {
		this.zpVyuzitiJed = zpVyuzitiJed;
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
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "7");
		controlFile = super.insertColumn(controlFile, "DOPLKOD");
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
					zpVyuzitiJed);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
