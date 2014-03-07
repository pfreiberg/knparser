package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jednotky.Jednotky;

public class JednotkyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "JEDNOTKY";

	public JednotkyOracleLoaderFileExporter(List<Jednotky> jednotky,
			String characterSet, String output, String prefix) {
		super(jednotky, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
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
