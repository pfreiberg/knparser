package cz.pfreiberg.knparser.parser;

public enum EncodingCzech {

	iso88592("WE8ISO8859P2"), windows1250("EE8MSWIN1250");
	private String encoding;

	private EncodingCzech(String encoding) {
		this.encoding = encoding;
	}

	public String getEncoding() {
		return encoding;
	}

	public boolean equals(String value) {
		return (value == null) ? false : encoding.equals(value);
	}

}
