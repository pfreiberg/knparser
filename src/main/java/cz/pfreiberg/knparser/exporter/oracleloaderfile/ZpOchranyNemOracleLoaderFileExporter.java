package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpOchranyNemOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<ZpOchranyNem> zpOchranyNem;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ZP_OCHRANY_NEM";

	public ZpOchranyNemOracleLoaderFileExporter(List<ZpOchranyNem> zpOchranyNem,
			String prefix, String characterSet, String output) {
		this.zpOchranyNem = zpOchranyNem;
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
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "POZEMEK", "1");
		controlFile = super.insertVarcharColumn(controlFile, "BUDOVA", "1");
		controlFile = super.insertVarcharColumn(controlFile, "JEDNOTKA", "1");
		controlFile = super.insertColumn(controlFile, "NEMOCHR");
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
					zpOchranyNem);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
