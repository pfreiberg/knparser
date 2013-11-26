package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Výběr katastrálních území dle exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class KatastrUzemi {

	private Integer kod;
	private Integer obceKod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
		this.kod = kod;
	}

	public Integer getObceKod() {
		return obceKod;
	}

	public void setObceKod(Integer obceKod) {
		this.obceKod = obceKod;
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
