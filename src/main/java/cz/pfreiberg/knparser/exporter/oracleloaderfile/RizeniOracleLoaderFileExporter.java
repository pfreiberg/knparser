package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.parser.ParserException;
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

		makeControlFile();
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "TYPRIZ_KOD");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO");
		controlFile = super.insertDate(controlFile, "ROK");
		controlFile = super.insertColumn(controlFile, "STAV");
		controlFile = super.insertColumn(controlFile, "FUNKCE_KOD");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD");
		controlFile = super.insertColumn(controlFile, "FUNKCE_KOD_VYZNAMNA");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD_VYZNAMNA");
		controlFile = super.insertColumn(controlFile, "UZISYS_USERNAME");
		controlFile = super.insertColumn(controlFile, "UZIROL_NAZEV");
		controlFile = super.insertColumn(controlFile, "OSVOBOZENO");
		controlFile = super.insertColumn(controlFile, "HODNOTA_KOLKU");
		controlFile = super.insertDate(controlFile, "DATUM");
		controlFile = super.insertDate(controlFile, "DATUM2");
		controlFile = super.insertColumn(controlFile, "POPIS");
		controlFile = super.insertDate(controlFile, "DATUM_UZAVRENI");
		controlFile = super.insertColumn(controlFile, "PRARES_KOD");
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
					rizeni);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
