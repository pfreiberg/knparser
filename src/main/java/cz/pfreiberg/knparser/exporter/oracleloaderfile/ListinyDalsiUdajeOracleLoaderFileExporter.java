package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;

public class ListinyDalsiUdajeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "LISTINY_DALSI_UDAJE";

	public ListinyDalsiUdajeOracleLoaderFileExporter(List<ListinyDalsiUdaje> listinyDalsiUdaje,
			String prefix, String characterSet, String output) {
		super(listinyDalsiUdaje, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "LISTIN_ID");
		controlFile = super.insertVarcharColumn(controlFile, "DUL_KOD", "4");
		controlFile = super.insertDateColumn(controlFile, "CREATE_DATE");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
