package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.parser.Parser;

public class JednotkyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "JEDNOTKY";

	public JednotkyOracleLoaderFileExporter(List<Jednotky> jednotky,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, jednotky);
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
		controlFile = super.insertColumn(controlFile, "BUD_ID");
		controlFile = super.insertColumn(controlFile, "TYPJED_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_JEDNOTKY");
		controlFile = super.insertColumn(controlFile, "CENA_NEMOVITOSTI");
		controlFile = super.insertColumn(controlFile, "ZPVYJE_KOD");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertColumn(controlFile, "PODIL_CITATEL");
		controlFile = super.insertColumn(controlFile, "PODIL_JMENOVATEL");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "240");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
