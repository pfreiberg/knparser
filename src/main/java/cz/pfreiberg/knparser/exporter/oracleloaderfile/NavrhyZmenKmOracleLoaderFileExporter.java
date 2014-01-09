package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.FileManager;
import cz.pfreiberg.knparser.util.VfkUtil;

public class NavrhyZmenKmOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<NavrhyZmenKm> navrhyZmenKm;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "NAVRHY_ZMEN_KM";

	public NavrhyZmenKmOracleLoaderFileExporter(List<NavrhyZmenKm> navrhyZmenKm,
			String prefix, String characterSet, String output) {
		this.navrhyZmenKm = navrhyZmenKm;
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
		controlFile = super.insertColumn(controlFile, "STAV_NZ");
		controlFile = super.insertVarcharColumn(controlFile, "NZ_TYPE", "10");
		controlFile = super.insertVarcharColumn(controlFile, "PORIZENI_DAT_NZ", "1");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertVarcharColumn(controlFile, "CISLO_PLANKU", "60");
		controlFile = super.insertVarcharColumn(controlFile, "VYHOTOVIL", "60");
		controlFile = super.insertVarcharColumn(controlFile, "OZNACENI_MAPOVEHO_LISTU", "60");
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

	@Override
	public void appendLoadFile() {
		try {
			File file = new File(output + prefix + name + ".TXT");
			FileManager.writeToDataFile(file, VfkUtil.convertEncoding(characterSet),
					navrhyZmenKm);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
