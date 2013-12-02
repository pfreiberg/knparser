package cz.pfreiberg.knparser;

import java.io.IOException;
import java.util.Properties;

public class KnParser {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(KnParser.class
					.getResourceAsStream("KnParser.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Configuration configuration = new Configuration();
		configuration.setPathToFile(properties.getProperty("file"));
		configuration.setDestinationOfOutput(properties.getProperty("output"));

		Controller controller = new Controller(configuration);
		controller.run();
	}

}
