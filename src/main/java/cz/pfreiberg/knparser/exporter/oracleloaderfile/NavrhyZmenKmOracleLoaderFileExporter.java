package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;
import cz.pfreiberg.knparser.parser.ParserException;
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
		controlFile = super.insertColumn(controlFile, "NZ_TYPE");
		controlFile = super.insertColumn(controlFile, "PORIZENI_DAT_NZ");
		controlFile = super.insertColumn(controlFile, "RIZENI_ID");
		controlFile = super.insertColumn(controlFile, "CISLO_PLANKU");
		controlFile = super.insertColumn(controlFile, "VYHOTOVIL");
		controlFile = super.insertColumn(controlFile, "OZNACENI_MAPOVEHO_LISTU");
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
					navrhyZmenKm, true);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
