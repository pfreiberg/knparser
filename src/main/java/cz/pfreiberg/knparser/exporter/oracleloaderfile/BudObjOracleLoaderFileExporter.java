package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.BudObj;
import cz.pfreiberg.knparser.parser.Parser;

public class BudObjOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "BUDOBJ";

	public BudObjOracleLoaderFileExporter(List<BudObj> budObj,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, budObj);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "CISDOM_HOD");
		controlFile = super.insertColumn(controlFile, "ID_KN");
		controlFile = super.insertVarcharColumn(controlFile, "CB_KN", "4");
		controlFile = super.insertColumn(controlFile, "ID_UA");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
