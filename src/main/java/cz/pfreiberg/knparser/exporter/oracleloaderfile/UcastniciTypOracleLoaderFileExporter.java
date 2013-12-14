package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class UcastniciTypOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<UcastniciTyp> ucastniciTyp;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "UCASTNICI_TYP";

	public UcastniciTypOracleLoaderFileExporter(
			List<UcastniciTyp> ucastniciTyp, String prefix,
			String characterSet, String output) {
		this.ucastniciTyp = ucastniciTyp;
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

		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertColumn(controlFile, "TYPUCA_KOD");
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
					ucastniciTyp);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}