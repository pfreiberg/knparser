package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.Adresa;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class AdresaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Adresa> adresa;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ADROBJ";

	public AdresaOracleLoaderFileExporter(List<Adresa> adresa, String prefix,
			String characterSet, String output) {
		this.adresa = adresa;
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
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "OBJEKT_KOD");
		controlFile = super.insertColumn(controlFile, "ULICE_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "CIS_ORIENT", "4");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ULICE_NAZEV", "48");
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
					adresa);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
