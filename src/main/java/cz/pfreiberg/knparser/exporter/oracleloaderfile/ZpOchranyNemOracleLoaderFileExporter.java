package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.parser.Parser;

public class ZpOchranyNemOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "ZP_OCHRANY_NEM";

	public ZpOchranyNemOracleLoaderFileExporter(List<ZpOchranyNem> zpOchranyNem,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, zpOchranyNem);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "POZEMEK", "4");
		controlFile = super.insertVarcharColumn(controlFile, "BUDOVA", "4");
		controlFile = super.insertVarcharColumn(controlFile, "JEDNOTKA", "4");
		controlFile = super.insertColumn(controlFile, "NEMOCHR");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
