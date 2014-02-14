package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;
import cz.pfreiberg.knparser.parser.Parser;

public class ZpVyuzitiJedOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "ZP_VYUZITI_JED";

	public ZpVyuzitiJedOracleLoaderFileExporter(
			List<ZpVyuzitiJed> zpVyuzitiJed, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, zpVyuzitiJed);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "7");
		controlFile = super.insertColumn(controlFile, "DOPLKOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
