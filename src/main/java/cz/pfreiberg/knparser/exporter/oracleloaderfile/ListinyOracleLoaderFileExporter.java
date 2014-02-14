package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "LISTINY";

	public ListinyOracleLoaderFileExporter(List<Listiny> listiny,
			String prefix, String characterSet, String output) {
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (Parser.isFirstBatch()) {
			makeControlFile();
		}
		super.appendLoadFile(output + prefix + name, characterSet, listiny);
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, prefix + name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "TYPLIST_KOD");
		controlFile = super.insertVarcharColumn(controlFile, "POPIS", "2000");
		controlFile = super.insertVarcharColumn(controlFile, "OBSAH", "4");
		controlFile = super.insertColumn(controlFile, "STRAN");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VYHOTOVENI");
		controlFile = super.insertVarcharColumn(controlFile, "ZHOTOVITEL", "60");
		controlFile = super.insertVarcharColumn(controlFile, "PORADOVE_CISLO_ZHOTOVITELE", "8");
		controlFile = super.insertDateColumn(controlFile, "ROK_ZHOTOVITELE");
		controlFile = super.insertVarcharColumn(controlFile, "DOPLNENI_ZHOTOVITELE", "60");
		controlFile = super.insertVarcharColumn(controlFile, "ZKRATKA", "10");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertVarcharColumn(controlFile, "ZMENA_PRAV_VZTAHU", "4");
		controlFile = super.insertDateColumn(controlFile, "DATUM_PRAV_MOCI");
		controlFile = super.insertDateColumn(controlFile, "DATUM_VYKONATELNOSTI");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_OD");
		controlFile = super.insertDateColumn(controlFile, "DATUM_HIST_DO");
		controlFile = super.end(controlFile);

		try {
			FileManager.writeToConfigFile(new File(output + prefix + name + ".CFG"),
					controlFile, VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controlFile;
	}

}
