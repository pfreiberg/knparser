package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;
import cz.pfreiberg.knparser.parser.Parser;

public class PredmetyRizeniOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "PREDMETY_RIZENI";

	public PredmetyRizeniOracleLoaderFileExporter(List<PredmetyRizeni> predmetyRizeni,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, predmetyRizeni);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "TYPPRE_KOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
