package cz.pfreiberg.knparser.domain.rezervovanacisla;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Dotčená historická parcelní čísla".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class DotHistParCisla {

	private Integer katuzeKod;
	private Integer parcis;
	private Integer parpod;
	private Integer parskup;

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getParcis() {
		return parcis;
	}

	public void setParcis(Integer parcis) {
		this.parcis = parcis;
	}

	public Integer getParpod() {
		return parpod;
	}

	public void setParpod(Integer parpod) {
		this.parpod = parpod;
	}

	public Integer getParskup() {
		return parskup;
	}

	public void setParskup(Integer parskup) {
		this.parskup = parskup;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(parcis) + ","
				+ VfkUtil.formatValue(parpod) + ","
				+ VfkUtil.formatValue(parskup) 
				+ VfkUtil.getTerminator();
	}

}
