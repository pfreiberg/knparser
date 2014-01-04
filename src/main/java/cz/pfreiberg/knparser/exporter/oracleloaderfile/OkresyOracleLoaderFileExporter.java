package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OkresyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Okresy> okresy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "OKRESY";

	public OkresyOracleLoaderFileExporter(List<Okresy> okresy,
			String prefix, String characterSet, String output) {
		this.okresy = okresy;
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
		controlFile = super.insertColumn(controlFile, "KRAJE_KOD");
		controlFile = super.insertColumn(controlFile, "NAZEV");
		controlFile = super.insertDate(controlFile, "PLATNOST_OD");
		controlFile = super.insertDate(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "NUTS4");
		controlFile = super.insertColumn(controlFile, "NKRAJE_KOD");
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
					okresy, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
