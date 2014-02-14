package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;
import cz.pfreiberg.knparser.parser.Parser;

public class RJpvOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "R_JPV";

	public RJpvOracleLoaderFileExporter(List<RJpv> rJpv, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, rJpv);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "VERZE");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "HJPV_ID_1");
		controlFile = super.insertColumn(controlFile, "HJPV_ID_2");
		controlFile = super.insertColumn(controlFile, "TYPVAZBY_JPV");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
