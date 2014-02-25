package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;

public class MapoveListyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "MAPOVE_LISTY";

	public MapoveListyOracleLoaderFileExporter(List<MapoveListy> mapoveListy,
			String prefix, String characterSet, String output) {
		super(mapoveListy, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertVarcharColumn(controlFile, "OZNACENI_MAPOVEHO_LISTU", "100");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.insertVarcharColumn(controlFile, "MAPA", "5");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
