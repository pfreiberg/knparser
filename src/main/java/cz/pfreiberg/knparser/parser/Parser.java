package cz.pfreiberg.knparser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cz.pfreiberg.knparser.Configuration;

public class Parser {

	Configuration configuration;

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException {
		this.configuration = configuration;
		File file = new File(configuration.getPathToFile());
		String encoding = getEncoding(file);
		Scanner scanner = getScanner(file, encoding);
		setConfiguration(scanner);
	}

	public void parseFile() {

	}

	private String getEncoding(File file) throws FileNotFoundException {
		// TODO jak vybrat správné kódování?
		Scanner tempScanner = new Scanner(file, "windows-1250");
		tempScanner.useDelimiter("\r\n");

		while (tempScanner.hasNext()) {
			String nextToken = tempScanner.next();
			if (nextToken.contains("&HCODEPAGE")) {
				tempScanner.close();
				return nextToken.split("\"")[1];
			}
		}

		tempScanner.close();
		return "";
	}

	private Scanner getScanner(File file, String encoding)
			throws ParserException, FileNotFoundException {
		if (encoding == "")
			throw new ParserException("Encoding was NOT found in the file.");
		else if (EncodingCzech.windows1250.equalsVfk(encoding)) {
			configuration.setEncoding(EncodingCzech.windows1250);
			return new Scanner(file, EncodingCzech.windows1250.getEncoding());
		} else if (EncodingCzech.iso88592.equalsVfk(encoding)) {
			configuration.setEncoding(EncodingCzech.iso88592);
			return new Scanner(file, EncodingCzech.iso88592.getEncoding());
		}
		throw new ParserException("Unsupported encoding.");
	}

	private void setConfiguration(Scanner scanner) throws ParserException {
		scanner.useDelimiter("\r\n");

		while (scanner.hasNext()) {
			String nextToken = scanner.next();
			if (nextToken.contains("&HZMENY")) {
				try {
					int hzmenyValue = Integer.parseInt(nextToken.split(";")[1]);
					if (hzmenyValue == 1) {
						configuration.setPrefix("TMP_");
					} else if (hzmenyValue == 0) {
						configuration.setPrefix("");
					} else
						throw new ParserException("HZMENY has undefine value.");
					return;
				} catch (NumberFormatException e) {
					throw new ParserException(
							"HZMENY must have number value (0 | 1).");
				}
			}
		}

	}
}
