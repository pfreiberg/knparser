package cz.pfreiberg.knparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cz.pfreiberg.knparser.exporter.oracledatabase.ConnectionParameters;

/**
 * Vstupní bod programu. Zpracuje parametry, se kterými byl program spuštěn,
 * vytvoří Controller, který zodpovídá za zpracování VFK a předá mu kontrolu nad
 * během.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * @version 1.0 (8.1.2014)
 * 
 */
public class KnParser {

	public static void main(String[] args) {

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
				configuration.setOutput(args[i] + "\\");
				break;
			default:
				System.out.println("Invalid command line switch.");
				return;
			}
		}

		Properties properties = new Properties();
		try {
			properties.load(KnParser.class
					.getResourceAsStream("KnParser.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		configuration.setNumberOfRows(properties.getProperty("numberOfRows"));

		ConnectionParameters connection = null;
		if (toDatabase) {
			connection = getConnectionParameters(properties);
		}
		configuration.setConnection(connection);

		if (parseWholeFolder) {
			parseFolder(configuration, connection);
		} else {
			Controller controller = new Controller(configuration);
			controller.run();
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

	private static void parseFolder(Configuration configuration,
			ConnectionParameters connection) {

		String input = configuration.getInput();
		String output = configuration.getOutput();
		String numberOfRows = configuration.getNumberOfRows();

		List<String> files = getFilenames(input);

		for (int i = 0; i < files.size(); i++) {
			configuration = new Configuration(input + "\\" + files.get(i),
					output + files.get(i) + "\\", numberOfRows, connection);
			Controller controller = new Controller(configuration);
			controller.run();
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

}
