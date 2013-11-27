package cz.pfreiberg.knparser.domain.jednotky;

import java.util.Date;

/**
 * Třída reprezentující "Číselník typů jednotek".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TJednotek {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;
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

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

}
