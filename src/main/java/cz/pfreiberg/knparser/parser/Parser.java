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
		int i = 0;
		while (scanner.hasNext()) {
			actualLine = scanner.next();
			String actualNode = actualLine.split(";")[0];
			actualLine = actualLine.replace(actualNode + ";", "");

			switch (actualNode) {
			case "&DPAR":
				vfk.getParcely().add(ParserParcely.parse(actualLine));
			}
			i++;
		}
		System.out.println("End of file.");
		
		//TODO testovací výpis
		for (i = 0; i < 10; i++) {
			System.out.println(vfk.getParcely().get(i));
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
