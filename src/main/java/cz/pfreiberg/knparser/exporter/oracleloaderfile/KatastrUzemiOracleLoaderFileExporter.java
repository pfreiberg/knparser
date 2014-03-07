package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;

public class KatastrUzemiOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "KATASTR_UZEMI";

	public KatastrUzemiOracleLoaderFileExporter(List<KatastrUzemi> katastrUzemi,
			String characterSet, String output, String prefix) {
		super(katastrUzemi, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "OBCE_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "48");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
