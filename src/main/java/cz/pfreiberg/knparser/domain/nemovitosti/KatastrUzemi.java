package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Výběr katastrálních území dle exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class KatastrUzemi {

	private int kod;
	private int obceKod;
	private String nazev;
	private DateTime platnostOd;
	private DateTime platnostDo;

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public int getObceKod() {
		return obceKod;
	}

	public void setObceKod(int obceKod) {
		this.obceKod = obceKod;
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

}
