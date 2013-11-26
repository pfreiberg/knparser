package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Číselník způsobů ochrany nemovitosti".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public class ZpOchranyNem {

	private int kod;
	private String nazev;
	private DateTime platnostOd;
	private DateTime platnostDo;
	private String pozemek;
	private String budova;
	private String jednotka;
	private int nemochr;

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

	public String getPozemek() {
		return pozemek;
	}

	public void setPozemek(String pozemek) {
		this.pozemek = pozemek;
	}

	public String getBudova() {
		return budova;
	}

	public void setBudova(String budova) {
		this.budova = budova;
	}

	public String getJednotka() {
		return jednotka;
	}

	public void setJednotka(String jednotka) {
		this.jednotka = jednotka;
	}

	public int getNemochr() {
		return nemochr;
	}

	public void setNemochr(int nemochr) {
		this.nemochr = nemochr;
	}

}
