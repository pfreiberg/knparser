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
	private String numberOfRows;
	private ConnectionParameters connection;

	public Configuration() {
	}

	public Configuration(String input, String output, String numberOfRows,
			ConnectionParameters connection) {
		this.input = input;
		this.output = output;
		this.numberOfRows = numberOfRows;
		this.connection = connection;
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

	public String getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(String numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public ConnectionParameters getConnection() {
		return connection;
	}

	public void setConnection(ConnectionParameters connection) {
		this.connection = connection;
	}

}
