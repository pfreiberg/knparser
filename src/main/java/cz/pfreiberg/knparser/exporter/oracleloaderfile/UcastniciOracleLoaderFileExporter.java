package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcastniciOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Ucastnici> ucastnici;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "UCASTNICI";

	public UcastniciOracleLoaderFileExporter(List<Ucastnici> ucastnici,
			String prefix, String characterSet, String output) {
		this.ucastnici = ucastnici;
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
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "DRUH_UCASTNIKA");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO", "100");
		controlFile = super.insertVarcharColumn(controlFile, "JMENO_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI", "100");
		controlFile = super.insertVarcharColumn(controlFile, "PRIJMENI_U", "100");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_PRED_JMENEM", "35");
		controlFile = super.insertVarcharColumn(controlFile, "TITUL_ZA_JMENEM", "10");
		controlFile = super.insertVarcharColumn(controlFile, "RC", "10");
		controlFile = super.insertVarcharColumn(controlFile, "RODNE_PRIJMENI", "100");
		controlFile = super.insertColumn(controlFile, "RODINNY_STAV");
		controlFile = super.insertVarcharColumn(controlFile, "OBCHODNI_JMENO", "255");
		controlFile = super.insertVarcharColumn(controlFile, "OBCHODNI_JMENO_U", "255");
		controlFile = super.insertVarcharColumn(controlFile, "DIC", "16");
		controlFile = super.insertColumn(controlFile, "ICO");
		controlFile = super.insertColumn(controlFile, "DOPLNEK_ICO");
		controlFile = super.insertVarcharColumn(controlFile, "OVEREN_PODPIS", "4");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_RS");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_OS");
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
					ucastnici);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
