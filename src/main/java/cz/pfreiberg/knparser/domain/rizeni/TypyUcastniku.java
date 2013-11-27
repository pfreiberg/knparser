package cz.pfreiberg.knparser.domain.rizeni;

/**
 * 
 * Třída reprezentující "Typy účastníků řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TypyUcastniku {

	private String kod;
	private String nazev;
	private String popis;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

}
