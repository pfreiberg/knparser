package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TPredmetuR;

public class TPredmetuROracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "T_PREDMETU_R";

	public TPredmetuROracleLoaderFileExporter(List<TPredmetuR> tPredmetuR,
			String characterSet, String output, String prefix) {
		super(tPredmetuR, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
