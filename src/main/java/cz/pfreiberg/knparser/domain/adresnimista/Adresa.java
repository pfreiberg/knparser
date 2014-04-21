package cz.pfreiberg.knparser.domain.adresnimista;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Adresa".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Adresa {

	private Integer kod;
	private Integer objektKod;
	private Integer uliceKod;
	private String cisOrient;
	private Integer psc;
	private Date platnostOd;
	private Date platnostDo;
	private String uliceNazev;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
		this.kod = kod;
	}

	public Integer getObjektKod() {
		return objektKod;
	}

	public void setObjektKod(Integer objektKod) {
		this.objektKod = objektKod;
	}

	public Integer getUliceKod() {
		return uliceKod;
	}

	public void setUliceKod(Integer uliceKod) {
		this.uliceKod = uliceKod;
	}

	public String getCisOrient() {
		return cisOrient;
	}

	public void setCisOrient(String cisOrient) {
		this.cisOrient = cisOrient;
	}

	public Integer getPsc() {
		return psc;
	}

	public void setPsc(Integer psc) {
		this.psc = psc;
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

	public String getUliceNazev() {
		return uliceNazev;
	}

	public void setUliceNazev(String uliceNazev) {
		this.uliceNazev = uliceNazev;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + ","
				+ VfkUtil.formatValue(objektKod) + ","
				+ VfkUtil.formatValue(uliceKod) + ","
				+ VfkUtil.formatValue(cisOrient) + ","
				+ VfkUtil.formatValue(psc) + ","
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(uliceNazev)
				+ VfkUtil.getTerminator();
	}

}
