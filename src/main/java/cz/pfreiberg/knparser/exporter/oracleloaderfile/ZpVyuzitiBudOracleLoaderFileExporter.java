package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpVyuzitiBudOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<ZpVyuzitiBud> zpVyuzitiBud;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ZP_VYUZITI_BUD";

	public ZpVyuzitiBudOracleLoaderFileExporter(List<ZpVyuzitiBud> zpVyuzitiBud,
			String prefix, String characterSet, String output) {
		this.zpVyuzitiBud = zpVyuzitiBud;
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
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "8");
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
					zpVyuzitiBud, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
