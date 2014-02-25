package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;

public class PredmetyRizeniOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "PREDMETY_RIZENI";

	public PredmetyRizeniOracleLoaderFileExporter(List<PredmetyRizeni> predmetyRizeni,
			String prefix, String characterSet, String output) {
		super(predmetyRizeni, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "TYPPRE_KOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
