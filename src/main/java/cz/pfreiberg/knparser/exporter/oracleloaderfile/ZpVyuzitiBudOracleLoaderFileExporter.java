package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;

public class ZpVyuzitiBudOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZP_VYUZITI_BUD";

	public ZpVyuzitiBudOracleLoaderFileExporter(List<ZpVyuzitiBud> zpVyuzitiBud,
			String prefix, String characterSet, String output) {
		super(zpVyuzitiBud, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "8");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
