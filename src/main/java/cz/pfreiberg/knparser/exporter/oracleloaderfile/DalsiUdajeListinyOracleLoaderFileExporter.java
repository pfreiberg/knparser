package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;

public class DalsiUdajeListinyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "DALSI_UDAJE_LISTINY";

	public DalsiUdajeListinyOracleLoaderFileExporter(List<DalsiUdajeListiny> dalsiUdajeListiny,
			String prefix, String characterSet, String output) {
		super(dalsiUdajeListiny, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {

		controlFile = super.insertVarcharColumn(controlFile, "KOD", "4");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "60");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
