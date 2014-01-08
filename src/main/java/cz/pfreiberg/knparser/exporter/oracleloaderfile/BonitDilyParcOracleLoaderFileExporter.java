package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class BonitDilyParcOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<BonitDilyParc> bonitDilyParc;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "BONIT_DILY_PARC";

	public BonitDilyParcOracleLoaderFileExporter(List<BonitDilyParc> bonitDilyParc,
			String prefix, String characterSet, String output) {
		this.bonitDilyParc = bonitDilyParc;
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

		controlFile = super.insertColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PAR_ID");
		controlFile = super.insertVarcharColumn(controlFile, "BPEJ_KOD", "5");
		controlFile = super.insertColumn(controlFile, "VYMERA");
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
					bonitDilyParc, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
