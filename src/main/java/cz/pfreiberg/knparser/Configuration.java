package cz.pfreiberg.knparser;

/**
 * Zapouzdržuje parametry, se kterými byl program spuštěn.
 *
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public class Configuration {

	private String pathToFile;
	private String destinationOfOutput;

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public String getDestinationOfOutput() {
		return destinationOfOutput;
	}

	public void setDestinationOfOutput(String destinationOfOutput) {
		this.destinationOfOutput = destinationOfOutput;
	}

}
