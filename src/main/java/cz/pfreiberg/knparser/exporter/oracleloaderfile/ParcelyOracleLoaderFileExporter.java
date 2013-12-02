package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParcelyOracleLoaderFileExporter extends OracleLoaderFileExporter {
	
	private final String characterSet;
	private final String name = "PARCELY";
	
	public ParcelyOracleLoaderFileExporter(List<Parcely> parcely, String characterSet)
	{
		this.characterSet = characterSet;
	}
	
	@Override
	public void makeControlFile() {
		String loadFile = super.loadFile;
		loadFile = loadFile.replace("characterset_value", characterSet);
		loadFile = loadFile.replace("infile_value", name);
		loadFile = loadFile.replace("into_table_value", name);
		
		
		System.out.println(loadFile);
	}

}
