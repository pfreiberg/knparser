package cz.pfreiberg.knparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Vstupní bod programu. Zpracuje parametry, se kterými byl program spuštěn,
 * vytvoří Controller, který zodpovídá za zpracování VFK a předá mu kontrolu nad
 * během.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class KnParser {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(KnParser.class
					.getResourceAsStream("KnParser.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String input = properties.getProperty("input");
		String output = properties.getProperty("output");
		
		List<String> files = getAllNames(properties.getProperty("input"));
		
		for (int i = 0; i < files.size(); i++)
		{
			Configuration configuration = new Configuration(input + files.get(i), output + files.get(i) + "/");
			Controller controller = new Controller(configuration);
			controller.run();
		}
	}

	private static List<String> getAllNames(String path) {

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
