package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ZobrazeniVb;
import cz.pfreiberg.knparser.parser.Parser;

public class ZobrazeniVbOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "ZOBRAZENI_VB";

	public ZobrazeniVbOracleLoaderFileExporter(List<ZobrazeniVb> zobrazeniVb,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, zobrazeniVb);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "HJPV_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
