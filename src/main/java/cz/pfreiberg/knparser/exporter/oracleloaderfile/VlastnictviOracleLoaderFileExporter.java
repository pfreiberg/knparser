package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.vlastnictvi.Vlastnictvi;

public class VlastnictviOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "VLASTNICTVI";

	public VlastnictviOracleLoaderFileExporter(List<Vlastnictvi> vlastnictvi,
			String prefix, String characterSet, String output) {
		super(vlastnictvi, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "OPSUB_ID");
		controlFile = super.insertVarcharColumn(controlFile, "TYPRAV_KOD", "4");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertColumn(controlFile, "PODIL_CITATEL");
		controlFile = super.insertColumn(controlFile, "PODIL_JMENOVATEL");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU2");
		controlFile = super.insertColumn(controlFile, "PAR_ID");
		controlFile = super.insertColumn(controlFile, "BUD_ID");
		controlFile = super.insertColumn(controlFile, "JED_ID");
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
