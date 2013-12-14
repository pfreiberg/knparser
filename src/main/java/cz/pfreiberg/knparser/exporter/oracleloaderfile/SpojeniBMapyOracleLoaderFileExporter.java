package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<SpojeniBMapy> spojeniBMapy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleLoaderFileExporter(List<SpojeniBMapy> spojeniBMapy,
			String prefix, String characterSet, String output) {
		this.spojeniBMapy = spojeniBMapy;
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

		controlFile = super.insertDate(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDate(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertColumn(controlFile, "OP_ID");
		controlFile = super.insertColumn(controlFile, "DPM_ID");
		controlFile = super.insertColumn(controlFile, "HBPEJ_ID");
		controlFile = super.insertColumn(controlFile, "PARAMETRY_SPOJENI");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
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
					spojeniBMapy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
