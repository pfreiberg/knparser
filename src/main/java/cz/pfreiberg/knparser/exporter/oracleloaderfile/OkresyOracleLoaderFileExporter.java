package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;

public class OkresyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "OKRESY";

	public OkresyOracleLoaderFileExporter(List<Okresy> okresy,
			String prefix, String characterSet, String output) {
		super(okresy, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "KRAJE_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "32");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "NUTS4", "6");
		controlFile = super.insertColumn(controlFile, "NKRAJE_KOD");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
