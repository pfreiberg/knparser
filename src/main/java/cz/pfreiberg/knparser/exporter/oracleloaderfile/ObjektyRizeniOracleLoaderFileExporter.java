package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;

public class ObjektyRizeniOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final static String name = "OBJEKTY_RIZENI";

	public ObjektyRizeniOracleLoaderFileExporter(
			List<ObjektyRizeni> objektyRizeni, String prefix,
			String characterSet, String output) {
		super(objektyRizeni, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "PAR_ID");
		controlFile = super.insertColumn(controlFile, "BUD_ID");
		controlFile = super.insertColumn(controlFile, "JED_ID");
		controlFile = super.insertDateColumn(controlFile, "DATUM_PLOMBY");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ODSTRANENI_PLOMBY");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_OD");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_DO");
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
