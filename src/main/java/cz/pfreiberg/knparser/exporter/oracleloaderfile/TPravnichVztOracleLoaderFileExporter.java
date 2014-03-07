package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;

public class TPravnichVztOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "T_PRAVNICH_VZT";

	public TPravnichVztOracleLoaderFileExporter(
			List<TPravnichVzt> tPravnichVzt, String characterSet,
			String output, String prefix) {
		super(tPravnichVzt, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertColumn(controlFile, "TPR_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertVarcharColumn(controlFile, "VLASTNICTVI", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PRO_OS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PRO_NEMOVITOST", "4");
		controlFile = super.insertVarcharColumn(controlFile, "K_NEMOVITOSTI", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "SEKCE", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "VLVZTAH");
		controlFile = super.insertVarcharColumn(controlFile, "K_OS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PODIL_VERITELE", "4");
		controlFile = super.insertVarcharColumn(controlFile, "PORADI", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
