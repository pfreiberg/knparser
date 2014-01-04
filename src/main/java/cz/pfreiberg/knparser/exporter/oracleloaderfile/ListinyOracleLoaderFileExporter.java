package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<Listiny> listiny;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "LISTINY";

	public ListinyOracleLoaderFileExporter(List<Listiny> listiny,
			String prefix, String characterSet, String output) {
		this.listiny = listiny;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		if (!VfkUtil.isControlFileCreated(output + prefix + name + ".CFG")) {
			makeControlFile();
		}
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "TYPLIST_KOD");
		controlFile = super.insertColumn(controlFile, "POPIS");
		controlFile = super.insertColumn(controlFile, "OBSAH");
		controlFile = super.insertColumn(controlFile, "STRAN");
		controlFile = super.insertDate(controlFile, "DATUM_VYHOTOVENI");
		controlFile = super.insertColumn(controlFile, "ZHOTOVITEL");
		controlFile = super.insertColumn(controlFile, "PORADOVE_CISLO_ZHOTOVITELE");
		controlFile = super.insertDate(controlFile, "ROK_ZHOTOVITELE");
		controlFile = super.insertColumn(controlFile, "DOPLNENI_ZHOTOVITELE");
		controlFile = super.insertColumn(controlFile, "ZKRATKA");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "ZMEN_PRAV_VZTAHU");
		controlFile = super.insertDate(controlFile, "DATUM_PRAV_MOCI");
		controlFile = super.insertDate(controlFile, "DATUM_VYKONATELNOSTI");
		controlFile = super.insertDate(controlFile, "DATUM_HIST_OD");
		controlFile = super.insertDate(controlFile, "DATUM_HIST_DO");

		try {
			FileUtils.writeStringToFile(new File(output + prefix + name + ".CFG"),
					controlFile, VfkUtil.convertEncoding(characterSet));
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controlFile;
	}

	@Override
	public void appendLoadFile() {
		try {
			File file = new File(output + prefix + name + ".TXT");
			FileUtils.writeLines(file, VfkUtil.convertEncoding(characterSet),
					listiny, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
