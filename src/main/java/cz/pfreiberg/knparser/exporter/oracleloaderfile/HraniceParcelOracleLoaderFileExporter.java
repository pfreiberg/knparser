package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel;

public class HraniceParcelOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "HRANICE_PARCEL";

	public HraniceParcelOracleLoaderFileExporter(List<HraniceParcel> hraniceParcel,
			String prefix, String characterSet, String output) {
		super(hraniceParcel, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertColumn(controlFile, "PAR_ID_1");
		controlFile = super.insertColumn(controlFile, "PAR_ID_2");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
