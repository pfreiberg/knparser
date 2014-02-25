package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;

public class ZpVyuzitiJedOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "ZP_VYUZITI_JED";

	public ZpVyuzitiJedOracleLoaderFileExporter(
			List<ZpVyuzitiJed> zpVyuzitiJed, String prefix,
			String characterSet, String output) {
		super(zpVyuzitiJed, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "7");
		controlFile = super.insertColumn(controlFile, "DOPLKOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
