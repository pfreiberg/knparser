package cz.pfreiberg.knparser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import cz.pfreiberg.knparser.Configuration;
import cz.pfreiberg.knparser.domain.Vfk;
import cz.pfreiberg.knparser.util.EncodingCzech;
import cz.pfreiberg.knparser.util.VfkUtil;

public class Parser {

	Configuration configuration;
	File file;
	Vfk vfk;
	Scanner scanner;
	String actualLine;

	private final String EOF = "\r\n";

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		this.configuration = configuration;
		file = new File(configuration.getPathToFile());
		vfk = new Vfk();
		vfk.setCodepage(VfkUtil.getEncoding(file));
		scanner = getScanner(file, vfk.getCodepage());
		scanner.useDelimiter(EOF);
	}

	public void parse() {
		actualLine = "";
		while (scanner.hasNext()) {
			actualLine = scanner.next();
			String actualNode = actualLine.split(";")[0];
			actualLine = actualLine.replace(actualNode + ";", "");
			actualLine += "; ";

			switch (actualNode) {
			case "&DPAR":
				vfk.getParcely().add(ParserParcely.parse(actualLine));
				break;
			case "&DBUD":
				vfk.getBudovy().add(ParserBudovy.parse(actualLine));
				break;
			case "&DCABU":
				vfk.getCastiBudov().add(ParserCastiBudov.parse(actualLine));
				break;
			case "&DZPOCHN":
				vfk.getZpOchranyNem().add(ParserZpOchranyNem.parse(actualLine));
				break;
			case "&DDRUPOZ":
				vfk.getDPozemku().add(ParserDPozemku.parse(actualLine));
				break;
			case "&DZPVYPO":
				vfk.getZpVyuzitiPoz().add(ParserZpVyuzitiPoz.parse(actualLine));
				break;
			case "&DZDPAZE":
				vfk.getZdrojeParcelZe().add(
						ParserZdrojeParcelZe.parse(actualLine));
				break;
			case "&DZPURVY":
				vfk.getZpUrceniVymery().add(
						ParserZpUrceniVymery.parse(actualLine));
				break;
			case "&DTYPBUD":
				vfk.getTBudov().add(ParserTBudov.parse(actualLine));
				break;
			case "&DMAPLIS":
				vfk.getMapoveListy().add(ParserMapoveListy.parse(actualLine));
				break;
			case "&DKATUZE":
				vfk.getKatastrUzemi().add(ParserKatastrUzemi.parse(actualLine));
				break;
			case "&DOBCE":
				vfk.getObce().add(ParserObce.parse(actualLine));
				break;
			case "&DCASOBC":
				vfk.getCastiObci().add(ParserCastiObci.parse(actualLine));
				break;
			case "&DOKRESY":
				vfk.getOkresy().add(ParserOkresy.parse(actualLine));
				break;
			case "&DKRAJE":
				vfk.getKraje().add(ParserKraje.parse(actualLine));
				break;
			case "&DNKRAJE":
				vfk.getNoveKraje().add(ParserNoveKraje.parse(actualLine));
				break;
			case "&DRZO":
				vfk.getRZpochr().add(ParserRZpochr.parse(actualLine));
				break;
			case "&DZPVYBU":
				vfk.getZpVyuzitiBud().add(ParserZpVyuzitiBud.parse(actualLine));
				break;

			}
		}
		System.out.println("End of file.");

		// TODO testovací výpis
		for (int i = 0; i < 10; i++) {
			System.out.println(vfk.getBudovy().get(i));
		}
	}

	private Scanner getScanner(File file, String codepage)
			throws ParserException, FileNotFoundException {
		return new Scanner(file, getEncodingForScanner(codepage));
	}

	private String getEncodingForScanner(String codepage)
			throws ParserException {
		if (EncodingCzech.windows1250.equalsVfk(codepage)) {
			return EncodingCzech.windows1250.getEncoding();
		} else if (EncodingCzech.iso88592.equalsVfk(codepage)) {
			return EncodingCzech.iso88592.getEncoding();
		}
		throw new ParserException("Unsupported encoding.");
	}

}
