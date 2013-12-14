package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;
import cz.pfreiberg.knparser.parser.ParserException;
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

		makeControlFile();
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "DRUH_UCASTNIKA");
		controlFile = super.insertColumn(controlFile, "JMENO");
		controlFile = super.insertColumn(controlFile, "JMENO_U");
		controlFile = super.insertColumn(controlFile, "PRIJMENI");
		controlFile = super.insertColumn(controlFile, "PRIJMENI_U");
		controlFile = super.insertColumn(controlFile, "TITUL_PRED_JMENEM");
		controlFile = super.insertColumn(controlFile, "TITUL_ZA_JMENEM");
		controlFile = super.insertColumn(controlFile, "RC");
		controlFile = super.insertColumn(controlFile, "RODNE_PRIJMENI");
		controlFile = super.insertColumn(controlFile, "RODINNY_STAV");
		controlFile = super.insertColumn(controlFile, "OBCHODNI_JMENO");
		controlFile = super.insertColumn(controlFile, "OBCHODNI_JMENO_U");
		controlFile = super.insertColumn(controlFile, "DIC");
		controlFile = super.insertColumn(controlFile, "ICO");
		controlFile = super.insertColumn(controlFile, "DOPLNEK_ICO");
		controlFile = super.insertColumn(controlFile, "OVEREN_PODPIS");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_RS");
		controlFile = super.insertColumn(controlFile, "OVEREN_PROTI_OS");
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
					ucastnici);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}