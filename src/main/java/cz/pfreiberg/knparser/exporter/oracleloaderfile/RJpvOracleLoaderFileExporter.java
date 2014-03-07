package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;

public class RJpvOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "R_JPV";

	public RJpvOracleLoaderFileExporter(List<RJpv> rJpv, String characterSet,
			String output, String prefix) {
		super(rJpv, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
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
