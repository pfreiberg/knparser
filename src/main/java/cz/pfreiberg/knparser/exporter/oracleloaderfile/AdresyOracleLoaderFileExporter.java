package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Adresy;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class AdresyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Adresy> adresy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ADRESY";

	public AdresyOracleLoaderFileExporter(List<Adresy> adresy,
			String prefix, String characterSet, String output) {
		this.adresy = adresy;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (Parser.isFirstBatch()) {
			makeControlFile();
		}
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);

		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertColumn(controlFile, "TYP_ADRESY");
		controlFile = super.insertVarcharColumn(controlFile, "OKRES", "32");
		controlFile = super.insertVarcharColumn(controlFile, "OBEC", "48");
		controlFile = super.insertVarcharColumn(controlFile, "CAST_OBCE", "48");
		controlFile = super.insertColumn(controlFile, "CISLO_DOMOVNI");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV_ULICE", "48");
		controlFile = super.insertVarcharColumn(controlFile, "CISLO_ORIENTACNI", "4");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertVarcharColumn(controlFile, "STAT", "23");
		controlFile = super.insertVarcharColumn(controlFile, "TELEFON", "33");
		controlFile = super.insertVarcharColumn(controlFile, "FAX", "33");
		controlFile = super.insertVarcharColumn(controlFile, "MESTSKA_CAST", "48");
		controlFile = super.insertColumn(controlFile, "CP_CE");
		controlFile = super.insertColumn(controlFile, "KOD_ADRM");
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
					adresy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
