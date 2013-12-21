package cz.pfreiberg.knparser;

/**
 * Zapouzdržuje parametry, se kterými byl program spuštěn.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Configuration {

	private String input;
	private String output;

	public Configuration(String input, String output) {
		this.input = input;
		this.output = output;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
