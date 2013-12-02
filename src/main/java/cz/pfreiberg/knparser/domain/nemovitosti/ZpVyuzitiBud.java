package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Způsob využití budov".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpVyuzitiBud {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date plastnostDo;
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

	public Date getPlastnostDo() {
		return plastnostDo;
	}

	public void setPlastnostDo(Date plastnostDo) {
		this.plastnostDo = plastnostDo;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

	@Override
	public String toString() {
		return "ZpVyuzitiBud [kod=" + kod + ", nazev=" + nazev
				+ ", platnostOd=" + platnostOd + ", plastnostDo=" + plastnostDo
				+ ", zkratka=" + zkratka + "]";
	}

}
