package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Další údaje listin".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class DalsiUdajeListiny {

	private String kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
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

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) 
				+ VfkUtil.getTerminator();
	}

}
