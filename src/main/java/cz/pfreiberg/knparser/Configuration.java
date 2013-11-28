package cz.pfreiberg.knparser;

import cz.pfreiberg.knparser.parser.EncodingCzech;

public class Configuration {

	private String pathToFile;
	private String destinationOfOutput;
	private String prefix;
	private EncodingCzech encoding;

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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public EncodingCzech getEncoding() {
		return encoding;
	}

	public void setEncoding(EncodingCzech encoding) {
		this.encoding = encoding;
	}

}
