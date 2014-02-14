package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.parser.Parser;

public class ParcelyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "PARCELY";

	public ParcelyOracleLoaderFileExporter(List<Parcely> parcely,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, parcely);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDateColumn(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertZeroColumn(controlFile, "PRIZNAK_KONTEXTU");
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
		controlFile = super.insertVarcharColumn(controlFile, "IDENT_BUD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "SOUCASTI", "4");
		controlFile = super.insertColumn(controlFile, "PS_ID");
		controlFile = super.insertVarcharColumn(controlFile, "IDENT_PS", "4");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
