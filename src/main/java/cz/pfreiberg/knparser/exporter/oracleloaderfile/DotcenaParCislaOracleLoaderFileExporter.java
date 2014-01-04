package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotcenaParCisla;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DotcenaParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<DotcenaParCisla> dotcenaParCisla;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "DOTCENA_PAR_CISLA";

	public DotcenaParCislaOracleLoaderFileExporter(List<DotcenaParCisla> dotcenaParCisla,
			String prefix, String characterSet, String output) {
		this.dotcenaParCisla = dotcenaParCisla;
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
		controlFile = super.insertColumn(controlFile, "KMENOVE_CISLO_PAR");
		controlFile = super.insertColumn(controlFile, "PODDELENI_CISLA_PAR");
		controlFile = super.insertColumn(controlFile, "DRUH_CISLOVANI_PAR");
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
					dotcenaParCisla, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
