package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Parcely> parcely;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "PARCELY";

	public ParcelyOracleLoaderFileExporter(List<Parcely> parcely,
			String prefix, String characterSet, String output) {
		this.parcely = parcely;
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
		controlFile = super.insertColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PKN_ID");
		controlFile = super.insertVarcharColumn(controlFile, "PAR_TYPE", "10");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD_PUV");
		controlFile = super.insertColumn(controlFile, "DRUH_CISLOVANI_PAR");
		controlFile = super.insertColumn(controlFile, "KMENOVE_CISLO_PAR");
		controlFile = super.insertColumn(controlFile, "ZDPAZE_KOD");
		controlFile = super.insertColumn(controlFile, "PODDELENI_CISLA_PAR");
		controlFile = super.insertColumn(controlFile, "DIL_PARCELY");
		controlFile = super.insertColumn(controlFile, "MAPLIS_KOD");
		controlFile = super.insertColumn(controlFile, "ZPURVY_KOD");
		controlFile = super.insertColumn(controlFile, "DRUPOZ_KOD");
		controlFile = super.insertColumn(controlFile, "ZPVYPA_KOD");
		controlFile = super.insertColumn(controlFile, "TYP_PARCELY");
		controlFile = super.insertColumn(controlFile, "VYMERA_PARCELY");
		controlFile = super.insertColumn(controlFile, "CENA_NEMOVITOSTI");
		controlFile = super.insertVarcharColumn(controlFile, "DEFINICNI_BOD_PAR", "100");
		controlFile = super.insertColumn(controlFile, "TEL_ID");
		controlFile = super.insertColumn(controlFile, "PAR_ID");
		controlFile = super.insertColumn(controlFile, "BUD_ID");
		controlFile = super.insertVarcharColumn(controlFile, "IDENT_BUD", "1");
		controlFile = super.insertVarcharColumn(controlFile, "SOUCASTI", "1");
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.insertVarcharColumn(controlFile, "IDENT_PS", "1");
		controlFile = super.end(controlFile);

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name
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
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					parcely, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
