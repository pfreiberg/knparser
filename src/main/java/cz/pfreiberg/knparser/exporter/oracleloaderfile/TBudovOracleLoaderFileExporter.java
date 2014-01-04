package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TBudovOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<TBudov> tBudov;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "T_BUDOV";

	public TBudovOracleLoaderFileExporter(List<TBudov> tBudov,
			String prefix, String characterSet, String output) {
		this.tBudov = tBudov;
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

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "NAZEV");
		controlFile = super.insertDate(controlFile, "PLATNOST_OD");
		controlFile = super.insertDate(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "ZADANI_CD");
		controlFile = super.insertColumn(controlFile, "ZKRATKA");
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
					tBudov, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
