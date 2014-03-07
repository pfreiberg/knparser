package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;

public class NavrhyZmenKmOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "NAVRHY_ZMEN_KM";

	public NavrhyZmenKmOracleLoaderFileExporter(List<NavrhyZmenKm> navrhyZmenKm,
			String characterSet, String output, String prefix) {
		super(navrhyZmenKm, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "STAV_NZ");
		controlFile = super.insertVarcharColumn(controlFile, "NZ_TYPE", "10");
		controlFile = super.insertVarcharColumn(controlFile, "PORIZENI_DAT_NZ", "4");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertVarcharColumn(controlFile, "CISLO_PLANKU", "60");
		controlFile = super.insertVarcharColumn(controlFile, "VYHOTOVIL", "60");
		controlFile = super.insertVarcharColumn(controlFile, "OZNACENI_MAPOVEHO_LISTU", "60");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
