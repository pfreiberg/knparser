package cz.pfreiberg.knparser;

import java.io.FileNotFoundException;
import java.io.IOException;

import cz.pfreiberg.knparser.parser.Parser;
import cz.pfreiberg.knparser.parser.ParserException;

public class Controller {

	public static void main(String[] args) {

		if (args.length == 0) {
			return;
		}

		Configuration configuration = new Configuration();
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "--load-file":
				i++;
				configuration.setPathToFile(args[i]);
				break;
			case "--destination":
				i++;
				configuration.setDestinationOfOutput(args[i]);
				break;
			default:
				System.out.println("Invalid command line switch.");
				return;
			}
		}

		try {
			Parser parser = new Parser(configuration);
			parser.parseFile();
		} catch (FileNotFoundException e) {
			System.out.println("Input file was NOT found.");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
