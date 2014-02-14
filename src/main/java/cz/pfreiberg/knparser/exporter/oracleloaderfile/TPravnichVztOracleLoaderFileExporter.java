package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "T_PRAVNICH_VZT";

	public TPravnichVztOracleLoaderFileExporter(
			List<TPravnichVzt> tPravnichVzt, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (Parser.isFirstBatch()) {
			makeControlFile();
		}
		super.appendLoadFile(output + prefix + name, characterSet, tPravnichVzt);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);

		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertColumn(controlFile, "TPR_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertVarcharColumn(controlFile, "VLASTNICTVI", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PRO_OS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PRO_NEMOVITOST", "4");
		controlFile = super.insertVarcharColumn(controlFile, "K_NEMOVITOSTI", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "SEKCE", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "VLVZTAH");
		controlFile = super.insertVarcharColumn(controlFile, "K_OS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PODIL_VERITELE", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PORADI", "4");
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

}
