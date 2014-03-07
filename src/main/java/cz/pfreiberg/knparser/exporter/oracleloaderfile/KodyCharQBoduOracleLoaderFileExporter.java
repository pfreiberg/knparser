package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.KodyCharQBodu;

public class KodyCharQBoduOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "KODY_CHAR_Q_BODU";

	public KodyCharQBoduOracleLoaderFileExporter(List<KodyCharQBodu> kodyCharQBodu, String characterSet,
			String output, String prefix) {
		super(kodyCharQBodu, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
