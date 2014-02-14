package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TListin;
import cz.pfreiberg.knparser.parser.Parser;

public class TListinOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "T_LISTIN";

	public TListinOracleLoaderFileExporter(List<TListin> tListin,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, tListin);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "DRUHLIST");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
