package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Číselník typů souřadnicových systémů".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TSouradSys {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;

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

}
