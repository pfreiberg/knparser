package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Číselník okresů – vázaně".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Okresy {

	private Integer kod;
	private Integer krajeKod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;
	private String nuts4;
	private Integer nKrajeKod;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
		this.kod = kod;
	}

	public Integer getKrajeKod() {
		return krajeKod;
	}

	public void setKrajeKod(Integer krajeKod) {
		this.krajeKod = krajeKod;
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

	public String getNuts4() {
		return nuts4;
	}

	public void setNuts4(String nuts4) {
		this.nuts4 = nuts4;
	}

	public Integer getNKrajeKod() {
		return nKrajeKod;
	}

	public void setNKrajeKod(Integer nKrajeKod) {
		this.nKrajeKod = nKrajeKod;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + ","
				+ VfkUtil.formatValue(krajeKod) + ","
				+ VfkUtil.formatValue(nazev) + ","
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(nuts4) + ","
				+ VfkUtil.formatValue(nKrajeKod) 
				+ VfkUtil.getTerminator();
	}
}
