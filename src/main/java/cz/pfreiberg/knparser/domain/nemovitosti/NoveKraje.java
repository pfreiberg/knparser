package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Číselník nových krajů - vázaně".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class NoveKraje {

	private Integer kod;
	private String nazev;
	private String nuts3;
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

	public String getNuts3() {
		return nuts3;
	}

	public void setNuts3(String nuts3) {
		this.nuts3 = nuts3;
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

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(nuts3) + ","
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) 
				+ VfkUtil.getTerminator();
	}

}
