package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Číselník způsobů využití pozemku".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpVyuzitiPoz {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Integer typppKod;
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

	public Integer getTypppKod() {
		return typppKod;
	}

	public void setTypppKod(Integer typppKod) {
		this.typppKod = typppKod;
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