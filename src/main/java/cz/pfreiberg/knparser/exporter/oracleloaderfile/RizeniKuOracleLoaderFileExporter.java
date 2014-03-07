package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.RizeniKu;

public class RizeniKuOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "RIZENI_KU";

	public RizeniKuOracleLoaderFileExporter(List<RizeniKu> rizeniKu,
			String characterSet, String output, String prefix) {
		super(rizeniKu, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
