package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Budovy;

public class BudovyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "BUDOVY";

	public BudovyOracleLoaderFileExporter(List<Budovy> budovy,
			String characterSet, String output, String prefix) {
		super(budovy, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "TYPBUD_KOD");
		controlFile = super.insertColumn(controlFile, "CAOBCE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_DOMOVNI");
		controlFile = super.insertColumn(controlFile, "CENA_NEMOVITOSTI");
		controlFile = super.insertColumn(controlFile, "ZPVYBU_KOD");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertVarcharColumn(controlFile, "DOCASNA_STAVBA", "4");
		controlFile = super.insertVarcharColumn(controlFile, "JE_SOUCASTI", "4");
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
