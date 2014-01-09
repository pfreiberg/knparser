package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.vlastnictvi.Telesa;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TelesaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Telesa> telesa;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "TELESA";

	public TelesaOracleLoaderFileExporter(List<Telesa> telesa, String prefix,
			String characterSet, String output) {
		this.telesa = telesa;
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

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_TEL");
		controlFile = super.end(controlFile);

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name
					+ ".CFG"), controlFile,
					VfkUtil.convertEncoding(characterSet));
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
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					telesa);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
