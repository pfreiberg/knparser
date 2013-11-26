package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Číselník zdrojů parcel ZE".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public class ZdrojeParcelZe {

	private int kod;
	private String nazev;
	private DateTime platnostOd;
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
