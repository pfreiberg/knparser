package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;

public class ZpmzOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "ZPMZ";

	public ZpmzOracleLoaderFileExporter(List<Zpmz> zpmz, String characterSet,
			String output, String prefix) {
		super(zpmz, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "PPZ_ID");
		controlFile = super.insertColumn(controlFile, "STAV_ZPMZ");
		controlFile = super.insertVarcharColumn(controlFile, "MERICKY_NACRT", "4");
		controlFile = super.insertVarcharColumn(controlFile, "ZAPISNIK_PODROB_MERENI", "4");
		controlFile = super.insertVarcharColumn(controlFile, "VYPOCET_PROTOKOL_VYMER", "4");
		controlFile = super.insertColumn(controlFile, "TYPSOS_KOD");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
