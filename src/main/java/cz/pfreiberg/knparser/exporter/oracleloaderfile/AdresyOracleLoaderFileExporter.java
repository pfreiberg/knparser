package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Adresy;

public class AdresyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ADRESY";

	public AdresyOracleLoaderFileExporter(List<Adresy> adresy,
			String characterSet, String output, String prefix) {
		super(adresy, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertColumn(controlFile, "TYP_ADRESY");
		controlFile = super.insertVarcharColumn(controlFile, "OKRES", "32");
		controlFile = super.insertVarcharColumn(controlFile, "OBEC", "48");
		controlFile = super.insertVarcharColumn(controlFile, "CAST_OBCE", "48");
		controlFile = super.insertColumn(controlFile, "CISLO_DOMOVNI");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV_ULICE", "48");
		controlFile = super.insertVarcharColumn(controlFile, "CISLO_ORIENTACNI", "4");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertVarcharColumn(controlFile, "STAT", "23");
		controlFile = super.insertVarcharColumn(controlFile, "TELEFON", "33");
		controlFile = super.insertVarcharColumn(controlFile, "FAX", "33");
		controlFile = super.insertVarcharColumn(controlFile, "MESTSKA_CAST", "48");
		controlFile = super.insertColumn(controlFile, "CP_CE");
		controlFile = super.insertColumn(controlFile, "KOD_ADRM");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
