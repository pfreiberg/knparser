package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Způsob využití budov".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpVyuzitiBud {

	private int kod;
	private String nazev;
	private DateTime platnostOd;
	private DateTime plastnostDo;
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

	public DateTime getPlastnostDo() {
		return plastnostDo;
	}

	public void setPlastnostDo(DateTime plastnostDo) {
		this.plastnostDo = plastnostDo;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

}
