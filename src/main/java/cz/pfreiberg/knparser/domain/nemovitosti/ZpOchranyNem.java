package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Číselník způsobů ochrany nemovitosti".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpOchranyNem {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;
	private String pozemek;
	private String budova;
	private String jednotka;
	private Integer nemochr;

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

	public Integer getNemochr() {
		return nemochr;
	}

	public void setNemochr(Integer nemochr) {
		this.nemochr = nemochr;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(pozemek) + ","
				+ VfkUtil.formatValue(budova) + ","
				+ VfkUtil.formatValue(jednotka) + ","
				+ VfkUtil.formatValue(nemochr)
				+ VfkUtil.getTerminator();
	}

}
