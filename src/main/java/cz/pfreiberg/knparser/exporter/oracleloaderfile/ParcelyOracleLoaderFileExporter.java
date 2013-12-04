package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParcelyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String characterSet;
	private final String name = "PARCELY";

	public ParcelyOracleLoaderFileExporter(List<Parcely> parcely,
			String characterSet) {
		this.characterSet = characterSet;
		String output = makeControlFile();

		try {
			FileUtils.writeStringToFile(new File("C:/Users/pfreiberg/Desktop/output.txt"), output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String makeControlFile() {
		String loadFile = super.makeControlFile();
		loadFile = super.fillHeader(loadFile, characterSet, name);
		
		loadFile = super.insertColumn(loadFile, "ID");
		loadFile = super.insertColumn(loadFile, "STAV_DAT");
		loadFile = super.insertDate(loadFile, "DATUM_VZNIKU");
		loadFile = super.insertDate(loadFile, "DATUM_ZANIKU");
		loadFile = super.insertColumn(loadFile, "PRIZNAK_KONTEXTU");
		loadFile = super.insertColumn(loadFile, "RIZENI_ID_VZNIKU");
		loadFile = super.insertColumn(loadFile, "RIZENI_ID_ZANIKU");
		loadFile = super.insertColumn(loadFile, "PKN_ID");
		loadFile = super.insertColumn(loadFile, "PAR_TYPE");
		loadFile = super.insertColumn(loadFile, "KATUZE_KOD");
		loadFile = super.insertColumn(loadFile, "KATUZE_KOD_PUV");
		loadFile = super.insertColumn(loadFile, "DRUH_CISLOVANI_PAR");
		loadFile = super.insertColumn(loadFile, "KMENOVE_CISLO_PAR");
		loadFile = super.insertColumn(loadFile, "ZDPAZE_KOD");
		loadFile = super.insertColumn(loadFile, "PODDELENI_CISLA_PAR");
		loadFile = super.insertColumn(loadFile, "DIL_PARCELY");
		loadFile = super.insertColumn(loadFile, "MAPLIS_KOD");
		loadFile = super.insertColumn(loadFile, "ZPURVY_KOD");
		loadFile = super.insertColumn(loadFile, "DRUPOZ_KOD");
		loadFile = super.insertColumn(loadFile, "ZPVYPA_KOD");
		loadFile = super.insertColumn(loadFile, "TYP_PARCELY");
		loadFile = super.insertColumn(loadFile, "VYMERA_PARCELY");
		loadFile = super.insertColumn(loadFile, "CENA_NEMOVITOSTI");
		loadFile = super.insertColumn(loadFile, "DEFINICNI_BOD_PAR");
		loadFile = super.insertColumn(loadFile, "TEL_ID");
		loadFile = super.insertColumn(loadFile, "PAR_ID");
		loadFile = super.insertColumn(loadFile, "BUD_ID");
		loadFile = super.insertColumn(loadFile, "IDENT_ID");
		loadFile = super.end(loadFile);
		
		return loadFile;
	}
	
	

}
