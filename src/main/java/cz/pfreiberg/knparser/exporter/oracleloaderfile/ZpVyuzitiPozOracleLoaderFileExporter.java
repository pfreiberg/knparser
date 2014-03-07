package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;

public class ZpVyuzitiPozOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZP_VYUZITI_POZ";

	public ZpVyuzitiPozOracleLoaderFileExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz,
			String characterSet, String output, String prefix) {
		super(zpVyuzitiPoz, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "16");
		controlFile = super.end(controlFile);

		return controlFile;
	}
}
