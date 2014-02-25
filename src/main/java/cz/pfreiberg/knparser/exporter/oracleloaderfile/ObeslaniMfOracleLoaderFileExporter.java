package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;

public class ObeslaniMfOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "OBESLANI_MF";

	public ObeslaniMfOracleLoaderFileExporter(List<ObeslaniMf> obeslaniMf,
			String prefix, String characterSet, String output) {
		super(obeslaniMf, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
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
