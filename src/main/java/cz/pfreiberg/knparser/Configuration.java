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
	private int numberOfRows;
	private ConnectionParameters connection;

	public Configuration() {
	}

	public Configuration(String input, String output, int numberOfRows,
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

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public ConnectionParameters getConnection() {
		return connection;
	}

	public void setConnection(ConnectionParameters connection) {
		this.connection = connection;
	}

	public boolean isConnectionParametersValid() {
		if (connection != null)
			return (connection.getUrl() != null && connection.getUser() != null && connection
					.getPassword() != null);
		return false;
	}

}
