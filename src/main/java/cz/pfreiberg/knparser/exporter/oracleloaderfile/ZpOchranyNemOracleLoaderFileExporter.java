package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;

public class ZpOchranyNemOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZP_OCHRANY_NEM";

	public ZpOchranyNemOracleLoaderFileExporter(List<ZpOchranyNem> zpOchranyNem,
			String characterSet, String output, String prefix) {
		super(zpOchranyNem, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "POZEMEK", "4");
		controlFile = super.insertVarcharColumn(controlFile, "BUDOVA", "4");
		controlFile = super.insertVarcharColumn(controlFile, "JEDNOTKA", "4");
		controlFile = super.insertColumn(controlFile, "NEMOCHR");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
