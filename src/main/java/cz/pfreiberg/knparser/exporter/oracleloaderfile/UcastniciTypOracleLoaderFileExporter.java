package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;
import cz.pfreiberg.knparser.parser.Parser;

public class UcastniciTypOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "UCASTNICI_TYP";

	public UcastniciTypOracleLoaderFileExporter(
			List<UcastniciTyp> ucastniciTyp, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, ucastniciTyp);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertVarcharColumn(controlFile, "TYPUCA_KOD", "4");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
