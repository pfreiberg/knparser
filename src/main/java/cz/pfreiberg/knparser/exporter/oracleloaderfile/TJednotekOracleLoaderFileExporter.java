package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.TJednotek;

public class TJednotekOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "T_JEDNOTEK";

	public TJednotekOracleLoaderFileExporter(List<TJednotek> tJednotek,
			String prefix, String characterSet, String output) {
		super(tJednotek, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "7");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
