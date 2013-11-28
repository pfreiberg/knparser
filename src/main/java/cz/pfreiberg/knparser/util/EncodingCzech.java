package cz.pfreiberg.knparser.util;

public enum EncodingCzech {

	iso88592("iso-8859-2", "WE8ISO8859P2"), windows1250("windows-1250", "EE8MSWIN1250");
	private String encoding;
	private String encodingVfk;
	
	private EncodingCzech(String encoding, String encodingVfk) {
		this.encoding = encoding;
		this.encodingVfk = encodingVfk;
	}
	
	public String getEncoding() {
		return encoding;
	}

	public String getEncodingVfk() {
		return encodingVfk;
	}

	public boolean equalsVfk(String value) {
		return (value == null) ? false : encodingVfk.equals(value);
	}

}
