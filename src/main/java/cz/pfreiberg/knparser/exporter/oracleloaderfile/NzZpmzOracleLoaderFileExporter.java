package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;
import cz.pfreiberg.knparser.parser.Parser;

public class NzZpmzOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "NZ_ZPMZ";

	public NzZpmzOracleLoaderFileExporter(List<NzZpmz> nzZpmz,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, nzZpmz);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "NZ_ID");
		controlFile = super.insertColumn(controlFile, "ZPMZ_CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "ZPMZ_KATUZE_KOD");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
