package cz.pfreiberg.knparser.exporter.oracledatabase;

public class JdbcException extends Exception {

	private static final long serialVersionUID = 1L;

	public JdbcException() {
	}

	public JdbcException(String message) {
		super(message);
	}

}
