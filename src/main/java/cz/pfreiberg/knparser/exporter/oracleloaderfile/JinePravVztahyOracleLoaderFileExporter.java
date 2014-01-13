package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JinePravVztahyOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<JinePravVztahy> jinePravVztahy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "JINE_PRAV_VZTAHY";

	public JinePravVztahyOracleLoaderFileExporter(
			List<JinePravVztahy> jinePravVztahy, String prefix,
			String characterSet, String output) {
		this.jinePravVztahy = jinePravVztahy;
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

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PAR_ID_PRO");
		controlFile = super.insertColumn(controlFile, "BUD_ID_PRO");
		controlFile = super.insertColumn(controlFile, "JED_ID_PRO");
		controlFile = super.insertColumn(controlFile, "PAR_ID_K");
		controlFile = super.insertColumn(controlFile, "BUD_ID_K");
		controlFile = super.insertColumn(controlFile, "JED_ID_K");
		controlFile = super.insertVarcharColumn(controlFile, "TYPRAV_KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS_PRAVNIHO_VZTAHU", "4000");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_PRO");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_K");
		controlFile = super.insertVarcharColumn(controlFile, "PODIL_POHLEDAVKA", "60");
		controlFile = super.insertColumn(controlFile, "HJPV_ID");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_2_PRO");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS2", "250");
		controlFile = super.insertDateColumn(controlFile, "PORADI_CAS");
		controlFile = super.insertVarcharColumn(controlFile, "PORADI_TEXT", "250");
		controlFile = super.insertColumn(controlFile, "PS_ID_PRO");
		controlFile = super.insertColumn(controlFile, "DATUM_UKONCENI");
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
					jinePravVztahy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
