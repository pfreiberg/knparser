package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<TPravnichVzt> tPravnichVzt;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "T_PRAVNICH_VZT";

	public TPravnichVztOracleLoaderFileExporter(
			List<TPravnichVzt> tPravnichVzt, String prefix,
			String characterSet, String output) {
		this.tPravnichVzt = tPravnichVzt;
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

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "TPR_KOD");
		controlFile = super.insertColumn(controlFile, "NAZEV");
		controlFile = super.insertColumn(controlFile, "VLASTNICTVI");
		controlFile = super.insertColumn(controlFile, "PRO_OS");
		controlFile = super.insertColumn(controlFile, "PRO_NEMOVITOST");
		controlFile = super.insertColumn(controlFile, "K_NEMOVITOSTI");
		controlFile = super.insertDate(controlFile, "PLATNOST_OD");
		controlFile = super.insertColumn(controlFile, "SEKCE");
		controlFile = super.insertDate(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "VLVZTAH");
		controlFile = super.insertColumn(controlFile, "K_OS");
		controlFile = super.insertColumn(controlFile, "PODIL_VERITELE");
		controlFile = super.insertColumn(controlFile, "PORADI");
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
					tPravnichVzt);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
