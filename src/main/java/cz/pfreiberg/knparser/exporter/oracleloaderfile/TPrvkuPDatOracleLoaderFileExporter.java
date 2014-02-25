package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;

public class TPrvkuPDatOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "T_PRVKU_P_DAT";

	public TPrvkuPDatOracleLoaderFileExporter(List<TPrvkuPDat> tPrvkuPDat,
			String prefix, String characterSet, String output) {
		super(tPrvkuPDat, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
	
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "POLOHOPIS", "4");
		controlFile = super.insertVarcharColumn(controlFile, "EDITOVATELNY", "4");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertVarcharColumn(controlFile, "VYZNAM", "240");
		controlFile = super.insertVarcharColumn(controlFile, "KRIVKA", "4");
		controlFile = super.insertColumn(controlFile, "TYP_PRVKU");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
