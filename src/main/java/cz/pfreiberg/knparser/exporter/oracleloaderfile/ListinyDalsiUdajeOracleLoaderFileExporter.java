package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.parser.Parser;

public class ListinyDalsiUdajeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "LISTINY_DALSI_UDAJE";

	public ListinyDalsiUdajeOracleLoaderFileExporter(List<ListinyDalsiUdaje> listinyDalsiUdaje,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, listinyDalsiUdaje);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "LISTIN_ID");
		controlFile = super.insertVarcharColumn(controlFile, "DUL_KOD", "4");
		controlFile = super.insertDateColumn(controlFile, "CREATE_DATE");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
