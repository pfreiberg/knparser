package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Číselník typů budov".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TBudov {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;
	private String zadaniCd;
	private String zkratka;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
		this.kod = kod;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public Date getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(Date platnostOd) {
		this.platnostOd = platnostOd;
	}

	public Date getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(Date platnostDo) {
		this.platnostDo = platnostDo;
	}

	public String getZadaniCd() {
		return zadaniCd;
	}

	public void setZadaniCd(String zadaniCd) {
		this.zadaniCd = zadaniCd;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

	@Override
	public String toString() {
		return "TBudov [kod=" + kod + ", nazev=" + nazev + ", platnostOd="
				+ platnostOd + ", platnostDo=" + platnostDo + ", zadaniCd="
				+ zadaniCd + ", zkratka=" + zkratka + "]";
	}

}
