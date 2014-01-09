package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObjektyRizeniOracleLoaderFileExporter extends
		OracleLoaderFileExporter {

	private List<ObjektyRizeni> objektyRizeni;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "OBJEKTY_RIZENI";

	public ObjektyRizeniOracleLoaderFileExporter(
			List<ObjektyRizeni> objektyRizeni, String prefix,
			String characterSet, String output) {
		this.objektyRizeni = objektyRizeni;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (!VfkUtil.isControlFileCreated(output + prefix + name + ".CFG")) {
			makeControlFile();
		}
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

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

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name
					+ ".CFG"), controlFile,
					VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controlFile;
	}

	@Override
	public void appendLoadFile() {
		try {
			File file = new File(output + prefix + name + ".TXT");
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					objektyRizeni);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
