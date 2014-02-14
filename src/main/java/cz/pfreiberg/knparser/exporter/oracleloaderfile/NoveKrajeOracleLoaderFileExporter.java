package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.NoveKraje;
import cz.pfreiberg.knparser.parser.Parser;

public class NoveKrajeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "NOVE_KRAJE";

	public NoveKrajeOracleLoaderFileExporter(List<NoveKraje> noveKraje,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, noveKraje);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "32");
		controlFile = super.insertVarcharColumn(controlFile, "NUTS3", "5");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
