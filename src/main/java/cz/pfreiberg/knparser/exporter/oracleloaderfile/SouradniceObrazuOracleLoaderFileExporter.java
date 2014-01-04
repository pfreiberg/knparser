package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SouradniceObrazuOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<SouradniceObrazu> souradniceObrazu;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "SOURADNICE_OBRAZU";

	public SouradniceObrazuOracleLoaderFileExporter(List<SouradniceObrazu> souradniceObrazu,
			String prefix, String characterSet, String output) {
		this.souradniceObrazu = souradniceObrazu;
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
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "CISLO_TL");
		controlFile = super.insertColumn(controlFile, "CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "UPLNE_CISLO");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertColumn(controlFile, "KODCHB_KOD");
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
					souradniceObrazu, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
