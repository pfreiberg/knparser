package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotHistParCisla;
import cz.pfreiberg.knparser.parser.Parser;

public class DotHistParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "DOT_HIST_PAR_CISLA";

	public DotHistParCislaOracleLoaderFileExporter(List<DotHistParCisla> dotHistParCisla,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, dotHistParCisla);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "PARCIS");
		controlFile = super.insertColumn(controlFile, "PARPOD");
		controlFile = super.insertColumn(controlFile, "PARSKUP");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
