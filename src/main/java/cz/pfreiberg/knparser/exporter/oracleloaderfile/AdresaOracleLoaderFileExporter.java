package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.Adresa;

public class AdresaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ADROBJ";

	public AdresaOracleLoaderFileExporter(List<Adresa> adresa, String characterSet,
			String output, String prefix) {
		super(adresa, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "OBJEKT_KOD");
		controlFile = super.insertColumn(controlFile, "ULICE_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "CIS_ORIENT", "4");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ULICE_NAZEV", "48");
		controlFile = super.end(controlFile);

		return controlFile;
	}
}
