package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;

public class TBudovOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "T_BUDOV";

	public TBudovOracleLoaderFileExporter(List<TBudov> tBudov, String characterSet,
			String output, String prefix) {
		super(tBudov, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZADANI_CD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "9");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
