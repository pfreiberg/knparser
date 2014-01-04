package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ZpmzOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Zpmz> zpmz;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "ZPMZ";

	public ZpmzOracleLoaderFileExporter(List<Zpmz> zpmz,
			String prefix, String characterSet, String output) {
		this.zpmz = zpmz;
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

		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "PPZ_ID");
		controlFile = super.insertColumn(controlFile, "STAV_ZPMZ");
		controlFile = super.insertColumn(controlFile, "MERICKY_NACRT");
		controlFile = super.insertColumn(controlFile, "ZAPISNIK_PODROB_MERENI");
		controlFile = super.insertColumn(controlFile, "VYPOCET_PROTOKOL_VYMER");
		controlFile = super.insertColumn(controlFile, "TYPSOS_KOD");
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
					zpmz, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
