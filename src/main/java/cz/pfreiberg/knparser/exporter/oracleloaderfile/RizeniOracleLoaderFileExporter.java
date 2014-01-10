package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RizeniOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Rizeni> rizeni;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "RIZENI";

	public RizeniOracleLoaderFileExporter(List<Rizeni> rizeni,
			String prefix, String characterSet, String output) {
		this.rizeni = rizeni;
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
		controlFile = super.insertVarcharColumn(controlFile, "TYPRIZ_KOD", "4");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO");
		controlFile = super.insertDateColumn(controlFile, "ROK");
		controlFile = super.insertVarcharColumn(controlFile, "STAV", "20");
		controlFile = super.insertColumn(controlFile, "FUNKCE_KOD");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD");
		controlFile = super.insertColumn(controlFile, "FUNKCE_KOD_VYZNAMNA");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD_VYZNAMNA");
		controlFile = super.insertVarcharColumn(controlFile, "UZISYS_USERNAME", "30");
		controlFile = super.insertVarcharColumn(controlFile, "UZIROL_NAZEV", "30");
		controlFile = super.insertVarcharColumn(controlFile, "OSVOBOZENO", "4");
		controlFile = super.insertColumn(controlFile, "HODNOTA_KOLKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM");
		controlFile = super.insertDateColumn(controlFile, "DATUM2");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.insertDateColumn(controlFile, "DATUM_UZAVRENI");
		controlFile = super.insertColumn(controlFile, "PRARES_KOD");
		controlFile = super.end(controlFile);

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name + ".CFG"),
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
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					rizeni);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
