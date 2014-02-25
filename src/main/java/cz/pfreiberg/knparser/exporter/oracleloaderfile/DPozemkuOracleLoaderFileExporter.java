package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;

public class DPozemkuOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "D_POZEMKU";

	public DPozemkuOracleLoaderFileExporter(List<DPozemku> dPozemku,
			String prefix, String characterSet, String output) {
		super(dPozemku, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertVarcharColumn(controlFile, "ZEMEDELSKA_KULTURA", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "9");
		controlFile = super.insertVarcharColumn(controlFile, "STAVEBNI_PARCELA", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
