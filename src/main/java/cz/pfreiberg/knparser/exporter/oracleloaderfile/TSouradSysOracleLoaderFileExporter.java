package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TSouradSys;

public class TSouradSysOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "T_SOURAD_SYS";

	public TSouradSysOracleLoaderFileExporter(List<TSouradSys> tSouradSys,
			String characterSet, String output, String prefix) {
		super(tSouradSys, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertVarcharColumn(controlFile, "NAZEV", "20");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_OD");
		controlFile = super.insertDateColumn(controlFile, "PLATNOST_DO");
		controlFile = super.end(controlFile);

		return controlFile;
	}

}
