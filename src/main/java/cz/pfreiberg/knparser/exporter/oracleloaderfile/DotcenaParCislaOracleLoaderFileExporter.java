package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rezervovanacisla.DotcenaParCisla;
import cz.pfreiberg.knparser.parser.Parser;

public class DotcenaParCislaOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "DOTCENA_PAR_CISLA";

	public DotcenaParCislaOracleLoaderFileExporter(List<DotcenaParCisla> dotcenaParCisla,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output , characterSet, dotcenaParCisla);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "KMENOVE_CISLO_PAR");
		controlFile = super.insertColumn(controlFile, "PODDELENI_CISLA_PAR");
		controlFile = super.insertColumn(controlFile, "DRUH_CISLOVANI_PAR");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
