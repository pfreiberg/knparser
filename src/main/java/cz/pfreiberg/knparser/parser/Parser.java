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
	String actualLine;
	
	private final String EOF = "\r\n";

	public Parser(Configuration configuration) throws FileNotFoundException,
			ParserException, IOException {
		this.configuration = configuration;
		file = new File(configuration.getPathToFile());
		vfk = new Vfk();
		vfk.setCodepage(VfkUtil.getEncoding(file));
		scanner = getScanner(file, vfk.getCodepage());
		actualLine = "";
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

}
