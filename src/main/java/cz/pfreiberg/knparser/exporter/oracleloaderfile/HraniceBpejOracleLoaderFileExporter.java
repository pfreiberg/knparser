package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.HraniceBpej;

public class HraniceBpejOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "HRANICE_BPEJ";

	public HraniceBpejOracleLoaderFileExporter(List<HraniceBpej> hraniceBpej,
			String characterSet, String output, String prefix) {
		super(hraniceBpej, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "BPEJ_KOD_HRANICE_1", "5");
		controlFile = super.insertVarcharColumn(controlFile, "BPEJ_KOD_HRANICE_2", "5");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
