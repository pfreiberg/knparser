package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;

public class NzZpmzOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final static String name = "NZ_ZPMZ";

	public NzZpmzOracleLoaderFileExporter(List<NzZpmz> nzZpmz,
			String prefix, String characterSet, String output) {
		super(nzZpmz, characterSet, output, prefix, name);
	}

	@Override
	public String makeControlFile(String controlFile) {
		
		controlFile = super.insertColumn(controlFile, "NZ_ID");
		controlFile = super.insertColumn(controlFile, "ZPMZ_CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "ZPMZ_KATUZE_KOD");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
