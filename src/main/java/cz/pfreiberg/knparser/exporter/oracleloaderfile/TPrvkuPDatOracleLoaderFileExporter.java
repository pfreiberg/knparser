package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPrvkuPDatOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<TPrvkuPDat> tPrvkuPDat;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "T_PRVKU_P_DAT";

	public TPrvkuPDatOracleLoaderFileExporter(List<TPrvkuPDat> tPrvkuPDat,
			String prefix, String characterSet, String output) {
		this.tPrvkuPDat = tPrvkuPDat;
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

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "POLOHOPIS");
		controlFile = super.insertColumn(controlFile, "EDITOVATELNY");
		controlFile = super.insertDate(controlFile, "PLATNOST_OD");
		controlFile = super.insertColumn(controlFile, "VYZNAM");
		controlFile = super.insertColumn(controlFile, "KRIVKA");
		controlFile = super.insertColumn(controlFile, "TYP_PRVKU");
		controlFile = super.insertDate(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name + ".CFG"),
					controlFile, VfkUtil.convertEncoding(characterSet));
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
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					tPrvkuPDat, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
