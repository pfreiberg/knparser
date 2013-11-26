package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Číselník způsobů využití pozemku".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpVyuzitiPoz {

	private int kod;
	private String nazev;
	private DateTime platnostOd;
	private int typppKod;
	private DateTime platnostDo;
	private String zkratka;

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public DateTime getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(DateTime platnostOd) {
		this.platnostOd = platnostOd;
	}

	public int getTypppKod() {
		return typppKod;
	}

	public void setTypppKod(int typppKod) {
		this.typppKod = typppKod;
	}

	public DateTime getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(DateTime platnostDo) {
		this.platnostDo = platnostDo;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

}
