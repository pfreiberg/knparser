package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Číselník typů předmětu řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TPredmetuR {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private String popis;
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

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
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
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(popis) + ","
				+ VfkUtil.formatValue(platnostDo)
				+ VfkUtil.getTerminator();
	}

}
