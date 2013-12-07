package cz.pfreiberg.knparser.domain.rezervovanacisla;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Rezervovaná parcelní čísla".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RezParcelniCisla {

	private Integer katuzeKod;
	private Integer kmenoveCisloPar;
	private Integer druhCislovaniPar;
	private Integer poddeleniCislaPar;
	private Integer rezzpmzCisloZpmz;

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getKmenoveCisloPar() {
		return kmenoveCisloPar;
	}

	public void setKmenoveCisloPar(Integer kmenoveCisloPar) {
		this.kmenoveCisloPar = kmenoveCisloPar;
	}

	public Integer getDruhCislovaniPar() {
		return druhCislovaniPar;
	}

	public void setDruhCislovaniPar(Integer druhCislovaniPar) {
		this.druhCislovaniPar = druhCislovaniPar;
	}

	public Integer getPoddeleniCislaPar() {
		return poddeleniCislaPar;
	}

	public void setPoddeleniCislaPar(Integer poddeleniCislaPar) {
		this.poddeleniCislaPar = poddeleniCislaPar;
	}

	public Integer getRezzpmzCisloZpmz() {
		return rezzpmzCisloZpmz;
	}

	public void setRezzpmzCisloZpmz(Integer rezzpmzCisloZpmz) {
		this.rezzpmzCisloZpmz = rezzpmzCisloZpmz;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(kmenoveCisloPar) + ","
				+ VfkUtil.formatValue(druhCislovaniPar) + ","
				+ VfkUtil.formatValue(poddeleniCislaPar) + ","
				+ VfkUtil.formatValue(rezzpmzCisloZpmz)
				+ VfkUtil.getTerminator();
	}

}
