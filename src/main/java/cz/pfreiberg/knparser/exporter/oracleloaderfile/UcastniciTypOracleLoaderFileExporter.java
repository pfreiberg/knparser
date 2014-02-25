package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;

public class UcastniciTypOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "UCASTNICI_TYP";

	public UcastniciTypOracleLoaderFileExporter(
			List<UcastniciTyp> ucastniciTyp, String prefix,
			String characterSet, String output) {
		super(ucastniciTyp, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertVarcharColumn(controlFile, "TYPUCA_KOD", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
