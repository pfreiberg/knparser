package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.RezParcelniCisla;

public class RezParcelniCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "REZ_PARCELNI_CISLA";

	public RezParcelniCislaOracleLoaderFileExporter(List<RezParcelniCisla> rezParcelniCisla,
			String prefix, String characterSet, String output) {
		super(rezParcelniCisla, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "KMENOVE_CISLO_PAR");
		controlFile = super.insertColumn(controlFile, "DRUH_CISLOVANI_PAR");
		controlFile = super.insertColumn(controlFile, "PODDELENI_CISLA_PAR");
		controlFile = super.insertColumn(controlFile, "REZZPMZ_CISLO_ZPMZ");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
