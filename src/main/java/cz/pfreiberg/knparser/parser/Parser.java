package cz.pfreiberg.knparser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import cz.pfreiberg.knparser.Configuration;

public class Parser {

	Configuration configuration;
	File file;
	Vfk vfk;
	Scanner scanner;
	String actualLine = "";
	
	private final String EOF = "\r\n";

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		this.configuration = configuration;
		file = new File(configuration.getPathToFile());
		vfk = new Vfk();
		vfk.setCodepage(VfkUtil.getEncoding(file));
		Scanner scanner = getScanner(file, vfk.getCodepage());
		setConfiguration(scanner);
	}

	public void parseFile() {

	}


	private Scanner getScanner(File file, String codepage)
			throws ParserException, FileNotFoundException {
		if (EncodingCzech.windows1250.equalsVfk(codepage)) {
			return new Scanner(file, EncodingCzech.windows1250.getEncoding());
		} else if (EncodingCzech.iso88592.equalsVfk(codepage)) {
			return new Scanner(file, EncodingCzech.iso88592.getEncoding());
		}
		throw new ParserException("Unsupported encoding.");
	}

	private void setConfiguration(Scanner scanner) throws ParserException {
		scanner.useDelimiter(EOF);

		while (scanner.hasNext()) {
			String nextToken = scanner.next();
			if (nextToken.contains("&HZMENY")) {
				try {
					int hzmenyValue = Integer.parseInt(nextToken.split(";")[1]);
					if (hzmenyValue == 1) {
						vfk.setZmeny(1);
					} else if (hzmenyValue == 0) {
						vfk.setZmeny(0);
					} else
						throw new ParserException("HZMENY has undefine value.");
				} catch (NumberFormatException e) {
					throw new ParserException(
							"HZMENY must have number value (0 | 1).");
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new ParserException("HZMENY has undefine value.");
				}
			}
		}
	}
}
