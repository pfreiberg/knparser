package cz.pfreiberg.knparser.parser;

public enum EncodingCzech {

	WE8ISO8859P2("charset=iso-8859-2"), EE8MSWIN1250("charset=windows-1250");
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
