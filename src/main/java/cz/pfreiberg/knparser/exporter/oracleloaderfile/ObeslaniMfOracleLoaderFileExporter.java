package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObeslaniMfOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<ObeslaniMf> obeslaniMf;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "OBESLANI_MF";

	public ObeslaniMfOracleLoaderFileExporter(List<ObeslaniMf> obeslaniMf,
			String prefix, String characterSet, String output) {
		this.obeslaniMf = obeslaniMf;
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

		controlFile = super.insertColumn(controlFile, "OBESLANI_ID");
		controlFile = super.insertColumn(controlFile, "ZPUSOB_OBESLANI");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD");
		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertColumn(controlFile, "STAV_OBESLANI");
		controlFile = super.insertDate(controlFile, "DATUM_PRIJETI_DORUCENKY");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID");
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
					obeslaniMf, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
