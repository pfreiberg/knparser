package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParcelyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String characterSet;
	private final String name = "PARCELY";

	public ParcelyOracleLoaderFileExporter(List<Parcely> parcely,
			String characterSet) {
		this.characterSet = characterSet;
		makeControlFile();
	}

	@Override
	public String makeControlFile() {
		String loadFile = super.makeControlFile();
		loadFile = super.fillHeader(loadFile, characterSet, name);
		loadFile = super.insertColumn(loadFile, "KOD");
		loadFile = super.insertDate(loadFile, "PLATNOST_OD");
		loadFile = super.end(loadFile);
		System.out.println(loadFile);
		return loadFile;
	}
	
	

}
