package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;

public class ZdrojeParcelZeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZDROJE_PARCEL_ZE";

	public ZdrojeParcelZeOracleLoaderFileExporter(List<ZdrojeParcelZe> zdrojeParcelZe,
			String prefix, String characterSet, String output) {
		super(zdrojeParcelZe, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
