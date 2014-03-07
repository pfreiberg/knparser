package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.RezCislaPbpp;

public class RezCislaPbppOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "REZ_CISLA_PBPP";

	public RezCislaPbppOracleLoaderFileExporter(List<RezCislaPbpp> rezCislaPbpp,
			String characterSet, String output, String prefix) {
		super(rezCislaPbpp, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "REZZPMZ_CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
