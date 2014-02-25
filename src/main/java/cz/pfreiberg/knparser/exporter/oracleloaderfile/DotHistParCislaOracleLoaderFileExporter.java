package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotHistParCisla;

public class DotHistParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "DOT_HIST_PAR_CISLA";

	public DotHistParCislaOracleLoaderFileExporter(List<DotHistParCisla> dotHistParCisla,
			String prefix, String characterSet, String output) {
		super(dotHistParCisla, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "PARCIS");
		controlFile = super.insertColumn(controlFile, "PARPOD");
		controlFile = super.insertColumn(controlFile, "PARSKUP");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
