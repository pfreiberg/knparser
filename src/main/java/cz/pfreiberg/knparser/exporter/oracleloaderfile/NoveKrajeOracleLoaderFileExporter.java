package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.NoveKraje;

public class NoveKrajeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "NOVE_KRAJE";

	public NoveKrajeOracleLoaderFileExporter(List<NoveKraje> noveKraje,
			String characterSet, String output, String prefix) {
		super(noveKraje, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "32");
		controlFile = super.insertVarcharColumn(controlFile, "NUTS3", "5");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
