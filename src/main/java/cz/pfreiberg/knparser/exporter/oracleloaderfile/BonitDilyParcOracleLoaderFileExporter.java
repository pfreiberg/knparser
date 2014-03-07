package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;

public class BonitDilyParcOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "BONIT_DILY_PARC";

	public BonitDilyParcOracleLoaderFileExporter(List<BonitDilyParc> bonitDilyParc,
			String characterSet, String output, String prefix) {
		super(bonitDilyParc, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PAR_ID");
		controlFile = super.insertVarcharColumn(controlFile, "BPEJ_KOD", "5");
		controlFile = super.insertColumn(controlFile, "VYMERA");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
