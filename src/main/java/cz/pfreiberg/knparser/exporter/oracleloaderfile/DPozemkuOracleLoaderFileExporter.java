package cz.pfreiberg.knparser.exporter.oracleloaderfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.parser.ParserException;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DPozemkuOracleLoaderFileExporter extends OracleLoaderFileExporter {

	private List<DPozemku> dPozemku;
	private final String prefix;
	private final String characterSet;
	private final String output;
	private final String name = "D_POZEMKU";

	public DPozemkuOracleLoaderFileExporter(List<DPozemku> dPozemku,
			String prefix, String characterSet, String output) {
		this.dPozemku = dPozemku;
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

		controlFile = super.insertColumn(controlFile, "KOD");
		controlFile = super.insertColumn(controlFile, "NAZEV");
		controlFile = super.insertColumn(controlFile, "ZEMEDELSKA_KULTURA");
		controlFile = super.insertDate(controlFile, "PLATNOST_OD");
		controlFile = super.insertColumn(controlFile, "TYPPPD_KOD");
		controlFile = super.insertDate(controlFile, "PLATNOST_DO");
		controlFile = super.insertColumn(controlFile, "ZKRATKA");
		controlFile = super.insertColumn(controlFile, "STAVEBNI_PARCELA");
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
					dPozemku);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}