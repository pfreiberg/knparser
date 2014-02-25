package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.BudObj;

public class BudObjOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "BUDOBJ";

	public BudObjOracleLoaderFileExporter(List<BudObj> budObj,
			String prefix, String characterSet, String output) {
		super(budObj, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "CISDOM_HOD");
		controlFile = super.insertColumn(controlFile, "ID_KN");
		controlFile = super.insertVarcharColumn(controlFile, "CB_KN", "4");
		controlFile = super.insertColumn(controlFile, "ID_UA");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
