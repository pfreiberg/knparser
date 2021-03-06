package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;

public class UcastniciOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "UCASTNICI";

	public UcastniciOracleLoaderFileExporter(List<Ucastnici> ucastnici,
			String characterSet, String output, String prefix) {
		super(ucastnici, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "DRUH_UCASTNIKA");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO", "100");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_PRED_JMENEM", "35");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_ZA_JMENEM", "10");
		controlFile = super.insertVarcharColumn(controlFile, "RC", "10");
		controlFile = super.insertVarcharColumn(controlFile, "RODNE_PRIJMENI", "100");
		controlFile = super.insertColumn(controlFile, "RODINNY_STAV");
		controlFile = super.insertVarcharColumn(controlFile, "OBCHODNI_JMENO", "255");
		controlFile = super.insertVarcharColumn(controlFile, "OBCHODNI_JMENO_U", "255");
		controlFile = super.insertVarcharColumn(controlFile, "DIC", "16");
		controlFile = super.insertColumn(controlFile, "ICO");
		controlFile = super.insertColumn(controlFile, "DOPLNEK_ICO");
		controlFile = super.insertVarcharColumn(controlFile, "OVEREN_PODPIS", "4");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_RS");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_OS");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
