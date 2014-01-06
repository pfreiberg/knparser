package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.parser.ParserException;
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
		controlFile = super.insertColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDate(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDate(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "ID_JE_1_PARTNER_BSM");
		controlFile = super.insertColumn(controlFile, "ID_JE_2_PARTNER_BSM");
		controlFile = super.insertColumn(controlFile, "ID_ZDROJ");
		controlFile = super.insertColumn(controlFile, "OPSUB_TYPE");
		controlFile = super.insertColumn(controlFile, "CHAROS_KOD");
		controlFile = super.insertColumn(controlFile, "ICO");
		controlFile = super.insertColumn(controlFile, "DOPLNEK_ICO");
		controlFile = super.insertBigColumn(controlFile, "NAZEV", "255");
		controlFile = super.insertBigColumn(controlFile, "NAZEV_U", "255");
		controlFile = super.insertColumn(controlFile, "RODNE_CISLO");
		controlFile = super.insertColumn(controlFile, "TITUL_PRED_JMENEM");
		controlFile = super.insertColumn(controlFile, "JMENO");
		controlFile = super.insertColumn(controlFile, "JMENO_U");
		controlFile = super.insertColumn(controlFile, "PRIJMENI");
		controlFile = super.insertColumn(controlFile, "PRIJMENI_U");
		controlFile = super.insertColumn(controlFile, "TITUL_ZA_JMENEM");
		controlFile = super.insertColumn(controlFile, "CISLO_DOMOVNI");
		controlFile = super.insertColumn(controlFile, "CISLO_ORIENTACNI");
		controlFile = super.insertColumn(controlFile, "NAZEV_ULICE");
		controlFile = super.insertColumn(controlFile, "CAST_OBCE");
		controlFile = super.insertColumn(controlFile, "OBEC");
		controlFile = super.insertColumn(controlFile, "OKRES");
		controlFile = super.insertColumn(controlFile, "STAT");
		controlFile = super.insertColumn(controlFile, "PSC");
		controlFile = super.insertColumn(controlFile, "MESTSKA_CAST");
		controlFile = super.insertColumn(controlFile, "CP_CE");
		controlFile = super.insertDate(controlFile, "DATUM_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "KOD_ADRM");
		controlFile = super.insertColumn(controlFile, "ID_NADRIZENE_PO");
		controlFile = super.end(controlFile);

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name
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
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					opravSubjekty, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
