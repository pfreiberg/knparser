package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OpravSubjektyOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<OpravSubjekty> opravSubjekty;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "OPRAV_SUBJEKTY";

	public OpravSubjektyOracleLoaderFileExporter(
			List<OpravSubjekty> opravSubjekty, String prefix,
			String characterSet, String output) {
		this.opravSubjekty = opravSubjekty;
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

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "ID_JE_1_PARTNER_BSM");
		controlFile = super.insertColumn(controlFile, "ID_JE_2_PARTNER_BSM");
		controlFile = super.insertColumn(controlFile, "ID_ZDROJ");
		controlFile = super.insertVarcharColumn(controlFile, "OPSUB_TYPE", "10");
		controlFile = super.insertColumn(controlFile, "CHAROS_KOD");
		controlFile = super.insertColumn(controlFile, "ICO");
		controlFile = super.insertColumn(controlFile, "DOPLNEK_ICO");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "255");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV_U", "255");
		controlFile = super.insertVarcharColumn(controlFile, "RODNE_CISLO", "10");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_PRED_JMENEM", "35");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO", "100");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_ZA_JMENEM", "10");
		controlFile = super.insertColumn(controlFile, "CISLO_DOMOVNI");
		controlFile = super.insertVarcharColumn(controlFile, "CISLO_ORIENTACNI", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV_ULICE", "48");
		controlFile = super.insertVarcharColumn(controlFile, "CAST_OBCE", "48");
		controlFile = super.insertVarcharColumn(controlFile, "OBEC", "48");
		controlFile = super.insertVarcharColumn(controlFile, "OKRES", "32");
		controlFile = super.insertVarcharColumn(controlFile, "STAT", "23");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertVarcharColumn(controlFile, "MESTSKA_CAST", "48");
		controlFile = super.insertColumn(controlFile, "CP_CE");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "KOD_ADRM");
		controlFile = super.insertColumn(controlFile, "ID_NADRIZENE_PO");
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
					opravSubjekty);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
