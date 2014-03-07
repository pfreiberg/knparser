package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.RUcelNem;

public class RUcelNemOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "R_UCEL_NEM";

	public RUcelNemOracleLoaderFileExporter(List<RUcelNem> rUcelNem,
			String characterSet, String output, String prefix) {
		super(rUcelNem, characterSet, output, prefix, name);
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
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.insertColumn(controlFile, "UCEL_KOD");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}
	
}
