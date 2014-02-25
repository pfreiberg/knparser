package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.CastiObci;

public class CastiObciOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "CASTI_OBCI";

	public CastiObciOracleLoaderFileExporter(List<CastiObci> castiObci,
			String prefix, String characterSet, String output) {
		super(castiObci, characterSet, output, prefix, name);
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
