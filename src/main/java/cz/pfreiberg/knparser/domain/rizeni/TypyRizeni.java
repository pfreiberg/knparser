package cz.pfreiberg.knparser.domain.rizeni;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Typy řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TypyRizeni {

	private String kod;
	private String nazev;
	private String popis;
	private String zpoplatneni;

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

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public String getZpoplatneni() {
		return zpoplatneni;
	}

	public void setZpoplatneni(String zpoplatneni) {
		this.zpoplatneni = zpoplatneni;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(popis) + ","
				+ VfkUtil.formatValue(zpoplatneni)
				+ VfkUtil.getTerminator();
	}

}
