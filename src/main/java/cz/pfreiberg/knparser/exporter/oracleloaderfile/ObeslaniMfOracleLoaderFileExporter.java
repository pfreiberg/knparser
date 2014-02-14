package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.parser.Parser;

public class ObeslaniMfOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "OBESLANI_MF";

	public ObeslaniMfOracleLoaderFileExporter(List<ObeslaniMf> obeslaniMf,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, obeslaniMf);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "OBESLANI_ID");
		controlFile = super.insertColumn(controlFile, "ZPUSOB_OBESLANI");
		controlFile = super.insertColumn(controlFile, "TYPOPE_KOD");
		controlFile = super.insertColumn(controlFile, "UCAST_ID");
		controlFile = super.insertColumn(controlFile, "STAV_OBESLANI");
		controlFile = super.insertDateColumn(controlFile, "DATUM_PRIJETI_DORUCENKY");
		controlFile = super.insertColumn(controlFile, "OPSUB_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
