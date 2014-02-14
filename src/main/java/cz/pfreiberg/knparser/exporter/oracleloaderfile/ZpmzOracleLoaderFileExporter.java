package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;
import cz.pfreiberg.knparser.parser.Parser;

public class ZpmzOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "ZPMZ";

	public ZpmzOracleLoaderFileExporter(List<Zpmz> zpmz, String prefix,
			String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, zpmz);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
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
