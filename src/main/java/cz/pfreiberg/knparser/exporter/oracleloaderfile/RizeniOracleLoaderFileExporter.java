package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.parser.Parser;

public class RizeniOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "RIZENI";

	public RizeniOracleLoaderFileExporter(List<Rizeni> rizeni,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, rizeni);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
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
		
		return controlFile;
	}

}
