package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.parser.Parser;

public class ObjektyRizeniOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "OBJEKTY_RIZENI";

	public ObjektyRizeniOracleLoaderFileExporter(
			List<ObjektyRizeni> objektyRizeni, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, objektyRizeni);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
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
