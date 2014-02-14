package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;
import cz.pfreiberg.knparser.parser.Parser;

public class NavrhyZmenKmOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "NAVRHY_ZMEN_KM";

	public NavrhyZmenKmOracleLoaderFileExporter(List<NavrhyZmenKm> navrhyZmenKm,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, navrhyZmenKm);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
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
