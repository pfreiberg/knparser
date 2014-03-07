package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotcenaParCisla;

public class DotcenaParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "DOTCENA_PAR_CISLA";

	public DotcenaParCislaOracleLoaderFileExporter(List<DotcenaParCisla> dotcenaParCisla,
			String characterSet, String output, String prefix) {
		super(dotcenaParCisla, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "KMENOVE_CISLO_PAR");
		controlFile = super.insertColumn(controlFile, "PODDELENI_CISLA_PAR");
		controlFile = super.insertColumn(controlFile, "DRUH_CISLOVANI_PAR");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
