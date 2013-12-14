package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotHistParCisla;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DotHistParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<DotHistParCisla> dotHistParCisla;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "DOT_HIST_PAR_CISLA";

	public DotHistParCislaOracleLoaderFileExporter(List<DotHistParCisla> dotHistParCisla,
			String prefix, String characterSet, String output) {
		this.dotHistParCisla = dotHistParCisla;
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

		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "PARCIS");
		controlFile = super.insertColumn(controlFile, "PARPOD");
		controlFile = super.insertColumn(controlFile, "PARSKUP");
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
					dotHistParCisla);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}