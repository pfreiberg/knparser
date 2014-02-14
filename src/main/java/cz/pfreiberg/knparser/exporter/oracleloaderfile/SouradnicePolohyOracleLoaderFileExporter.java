package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.util.List;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradnicePolohy;
import cz.pfreiberg.knparser.parser.Parser;

public class SouradnicePolohyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String name = "SOURADNICE_POLOHY";

	public SouradnicePolohyOracleLoaderFileExporter(List<SouradnicePolohy> souradnicePolohy,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		output = output + prefix + name;

		if (Parser.isFirstBatch()) {
			super.appendControlFile(output, characterSet, makeControlFile());
		}
		super.appendLoadFile(output, characterSet, souradnicePolohy);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);
		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertZeroColumn(controlFile, "STAV_DAT");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.insertColumn(controlFile, "CISLO_ZPMZ");
		controlFile = super.insertColumn(controlFile, "CISLO_TL");
		controlFile = super.insertColumn(controlFile, "CISLO_BODU");
		controlFile = super.insertColumn(controlFile, "UPLNE_CISLO");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertColumn(controlFile, "KODCHB_KOD");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD_MER");
		controlFile = super.insertColumn(controlFile, "CISLO_ZPMZ_MER");
		controlFile = super.end(controlFile);
		
		return controlFile;
	}

}
