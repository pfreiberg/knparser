package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.parser.Parser;

public class TPrvkuPDatOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "T_PRVKU_P_DAT";

	public TPrvkuPDatOracleLoaderFileExporter(List<TPrvkuPDat> tPrvkuPDat,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, tPrvkuPDat);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "POLOHOPIS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "EDITOVATELNY", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "VYZNAM", "240");
		controlFile = super.insertVarcharColumn(controlFile, "KRIVKA", "4");
		controlFile = super.insertColumn(controlFile, "TYP_PRVKU");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
