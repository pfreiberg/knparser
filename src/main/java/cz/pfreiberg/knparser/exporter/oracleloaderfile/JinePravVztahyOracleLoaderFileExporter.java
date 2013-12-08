package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.parser.ParserException;
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

		makeControlFile();
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
		controlFile = super.insertColumn(controlFile, "PAR_ID_PRO");
		controlFile = super.insertColumn(controlFile, "BUD_ID_PRO");
		controlFile = super.insertColumn(controlFile, "JED_ID_PRO");
		controlFile = super.insertColumn(controlFile, "PAR_ID_K");
		controlFile = super.insertColumn(controlFile, "BUD_ID_K");
		controlFile = super.insertColumn(controlFile, "JED_ID_K");
		controlFile = super.insertColumn(controlFile, "TYPRAV_KOD");
		controlFile = super.insertColumn(controlFile, "POPIS_PRAVNIHO_VZTAHU");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_PRO");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_K");
		controlFile = super.insertColumn(controlFile, "PODIL_POHLEDAVKA");
		controlFile = super.insertColumn(controlFile, "HJPV_ID");
		controlFile = super.insertDate(controlFile, "DATUM_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID_2_PRO");
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
					jinePravVztahy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
