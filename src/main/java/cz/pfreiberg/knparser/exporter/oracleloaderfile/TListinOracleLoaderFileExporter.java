package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.TListin;

public class TListinOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "T_LISTIN";

	public TListinOracleLoaderFileExporter(List<TListin> tListin,
			String characterSet, String output, String prefix) {
		super(tListin, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "DRUHLIST");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
