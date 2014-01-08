package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBoduBp;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyBoduBpOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<ObrazyBoduBp> obrazyBoduBp;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "OBRAZY_BODU_BP";

	public ObrazyBoduBpOracleLoaderFileExporter(List<ObrazyBoduBp> obrazyBoduBp,
			String prefix, String characterSet, String output) {
		this.obrazyBoduBp = obrazyBoduBp;
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
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertVarcharColumn(controlFile, "TEXT", "255");
		controlFile = super.insertColumn(controlFile, "VELIKOST");
		controlFile = super.insertColumn(controlFile, "UHEL");
		controlFile = super.insertColumn(controlFile, "BP_ID");
		controlFile = super.insertVarcharColumn(controlFile, "OBBP_TYPE", "10");
		controlFile = super.insertColumn(controlFile, "VZTAZNY_BOD");
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
					obrazyBoduBp, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
