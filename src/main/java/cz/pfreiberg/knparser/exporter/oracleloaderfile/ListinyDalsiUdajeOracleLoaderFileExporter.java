package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyDalsiUdajeOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<ListinyDalsiUdaje> listinyDalsiUdaje;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "LISTINY_DALSI_UDAJE";

	public ListinyDalsiUdajeOracleLoaderFileExporter(List<ListinyDalsiUdaje> listinyDalsiUdaje,
			String prefix, String characterSet, String output) {
		this.listinyDalsiUdaje = listinyDalsiUdaje;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (!VfkUtil.isControlFileCreated(output + prefix + name + ".CFG")) {
			makeControlFile();
		}
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "LISTIN_ID");
		controlFile = super.insertVarcharColumn(controlFile, "DUL_KOD", "3");
		controlFile = super.insertDateColumn(controlFile, "CREATE_DATE");
		controlFile = super.end(controlFile);

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name + ".CFG"),
					controlFile, VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controlFile;
	}

	@Override
	public void appendLoadFile() {
		try {
			File file = new File(output + prefix + name + ".TXT");
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					listinyDalsiUdaje, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
