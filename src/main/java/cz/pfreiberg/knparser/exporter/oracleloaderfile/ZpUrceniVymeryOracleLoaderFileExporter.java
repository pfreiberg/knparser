package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;

public class ZpUrceniVymeryOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZP_URCENI_VYMERY";

	public ZpUrceniVymeryOracleLoaderFileExporter(List<ZpUrceniVymery> zpUrceniVymery,
			String prefix, String characterSet, String output) {
		super(zpUrceniVymery, characterSet, output, prefix, name);
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
