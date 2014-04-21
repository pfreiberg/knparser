package cz.pfreiberg.knparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.parser.ParserException;

/**
 * Vstupní bod programu. Zpracuje parametry, se kterými byl program spuštěn,
 * vytvoří Controller, který zodpovídá za zpracování VFK a předá mu kontrolu nad
 * během.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * @version 2.0 (6.3.2014)
 * 
 */
public class KnParser {

	private static final Logger log = Logger.getLogger(KnParser.class);

	public static void main(String[] args) {
		log.info("KnParser started.");

		boolean parseWholeFolder = false;
		boolean toDatabase = false;
		Configuration configuration = new Configuration();

		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "--all":
				parseWholeFolder = true;
				break;
			case "--database":
				toDatabase = true;
				break;
			case "--input":
				i++;
				configuration.setInput(args[i]);
				break;
			case "--output":
				i++;
				configuration.setOutput(args[i]);
				break;
			default:
				log.fatal("Invalid command line switch.");
				return;
			}
		}

		try {

			if (configuration.getInput() == null) {
				log.fatal("Input is not specified.");
				return;
			}

			loadPropertyFile(toDatabase, configuration);
			if (toDatabase) {
				if (!configuration.isConnectionParametersValid()) {
					log.fatal("KnParser.properties must contain url, username and password to be able to connect to database.");
					return;
				}
			} else {
				if (configuration.getOutput() == null) {
					log.fatal("Output is not specified.");
					return;
				}
			}
		} catch (NullPointerException e) {
			log.fatal("KnParser.properties was not found.");
			log.debug("Stack trace: " + e);
			return;
		} catch (IOException e) {
			log.fatal("Error during reading KnParser.properties");
			log.debug("Stack trace: " + e);
			return;
		} catch (NumberFormatException e) {
			log.fatal("KnParser.properties is corrupted - couldn't obtain number of rows.");
			log.debug("Stack trace: " + e);
			return;
		}

		if (parseWholeFolder) {
			parseFolder(configuration);
		} else {
			parseFile(configuration);
		}
		log.info("KnParser finished.");
	}

	private static void loadPropertyFile(boolean toDatabase,
			Configuration configuration) throws IOException,
			NumberFormatException {
		Properties properties = new Properties();
		properties.load(KnParser.class
				.getResourceAsStream("KnParser.properties"));

		String numberOfRows = properties.getProperty("numberOfRows");
		configuration.setNumberOfRows(Integer.parseInt(numberOfRows));
		if (toDatabase) {
			ConnectionParameters connection = getConnectionParameters(properties);
			configuration.setConnection(connection);
		}
	}

	private static ConnectionParameters getConnectionParameters(
			Properties properties) {
		ConnectionParameters connection = new ConnectionParameters();
		connection.setUrl(properties.getProperty("url"));
		connection.setUser(properties.getProperty("username"));
		connection.setPassword(properties.getProperty("password"));
		return connection;
	}

	private static void parseFolder(Configuration configuration) {
		String input = configuration.getInput();
		String output = configuration.getOutput();
		List<String> files = getFilenames(input);

		for (int i = 0; i < files.size(); i++) {
			configuration = new Configuration(input + File.separator + files.get(i),
					output + files.get(i) + File.separator,
					configuration.getNumberOfRows(),
					configuration.getConnection());
			parseFile(configuration);
		}
	}

	private static List<String> getFilenames(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<String> output = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				output.add(listOfFiles[i].getName());
			}
		}

		return output;
	}

	private static void parseFile(Configuration configuration) {
		log.info("Parsing file " + configuration.getInput());
		try {
			Controller controller = new Controller(configuration);
			controller.run();
		} catch (FileNotFoundException e) {
			log.fatal("Input file was NOT found.");
			log.debug("Stack trace:", e);
		} catch (ParserException e) {
			log.fatal(e.getMessage());
			log.debug("Stack trace:", e);
		} catch (IOException e) {
			log.fatal("Error during reading input file.");
			log.debug("Stack trace:", e);
		}
	}

}
