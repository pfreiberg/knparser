package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiUdajeListinyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<DalsiUdajeListiny> dalsiUdajeListiny;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "DALSI_UDAJE_LISTINY";

	public DalsiUdajeListinyOracleLoaderFileExporter(List<DalsiUdajeListiny> dalsiUdajeListiny,
			String prefix, String characterSet, String output) {
		this.dalsiUdajeListiny = dalsiUdajeListiny;
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

		controlFile = super.insertVarcharColumn(controlFile, "KOD", "3");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
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
					dalsiUdajeListiny, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
