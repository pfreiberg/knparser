package cz.pfreiberg.knparser.exporter.oracledatabase;

/**
 * Zapouzdřuje parametry nutné pro připojení k databázi.
 * 
 * @author Petr Freiberg
 *
 */
public class ConnectionParameters {

	private String url;
	private String user;
	private String password;

	public ConnectionParameters() {
	}

	public ConnectionParameters(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
