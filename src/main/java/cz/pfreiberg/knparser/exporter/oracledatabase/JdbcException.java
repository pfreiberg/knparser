package cz.pfreiberg.knparser.exporter.oracledatabase;

public class JdbcException extends Exception {

	private static final long serialVersionUID = 3852029639030373131L;

	public JdbcException() {
	}

	public JdbcException(String message) {
		super(message);
	}

}
