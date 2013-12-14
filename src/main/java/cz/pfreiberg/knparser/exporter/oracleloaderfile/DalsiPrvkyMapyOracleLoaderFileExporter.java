package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.DalsiPrvkyMapy;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiPrvkyMapyOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<DalsiPrvkyMapy> dalsiPrvkyMapy;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "DALSI_PRVKY_MAPY";

	public DalsiPrvkyMapyOracleLoaderFileExporter(List<DalsiPrvkyMapy> dalsiPrvkyMapy,
			String prefix, String characterSet, String output) {
		this.dalsiPrvkyMapy = dalsiPrvkyMapy;
		this.prefix = prefix;
		this.characterSet = characterSet;
		this.output = output;

		makeControlFile();
		appendLoadFile();
	}

	@Override
	public String makeControlFile() {
		String controlFile = super.makeControlFile();
		controlFile = super.fillHeader(controlFile, characterSet, name);

		controlFile = super.insertColumn(controlFile, "ID");
		controlFile = super.insertColumn(controlFile, "STAV_DAT");
		controlFile = super.insertDate(controlFile, "DATUM_VZNIKU");
		controlFile = super.insertDate(controlFile, "DATUM_ZANIKU");
		controlFile = super.insertColumn(controlFile, "PRIZNAK_KONTEXTU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_VZNIKU");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID_ZANIKU");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_Y");
		controlFile = super.insertColumn(controlFile, "SOURADNICE_X");
		controlFile = super.insertColumn(controlFile, "TEXT");
		controlFile = super.insertColumn(controlFile, "VELIKOST");
		controlFile = super.insertColumn(controlFile, "UHEL");
		controlFile = super.insertColumn(controlFile, "BP_ID");
		controlFile = super.insertColumn(controlFile, "DPM_TYPE");
		controlFile = super.insertColumn(controlFile, "VZTAZNY_BOD");
		controlFile = super.insertColumn(controlFile, "KATUZE_KOD");
		controlFile = super.end(controlFile);

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
					dalsiPrvkyMapy);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
