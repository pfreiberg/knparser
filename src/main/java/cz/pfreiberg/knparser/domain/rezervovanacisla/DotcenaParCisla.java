package cz.pfreiberg.knparser.domain.rezervovanacisla;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Dotčená parcelní čísla".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class DotcenaParCisla {

	private Integer katuzeKod;
	private Integer kmenoveCisloPar;
	private Integer poddeleniCislaPar;
	private Integer druhCislovaniPar;

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

	public Integer getPoddeleniCislaPar() {
		return poddeleniCislaPar;
	}

	public void setPoddeleniCislaPar(Integer poddeleniCislaPar) {
		this.poddeleniCislaPar = poddeleniCislaPar;
	}

	public Integer getDruhCislovaniPar() {
		return druhCislovaniPar;
	}

	public void setDruhCislovaniPar(Integer druhCislovaniPar) {
		this.druhCislovaniPar = druhCislovaniPar;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(kmenoveCisloPar) + ","
				+ VfkUtil.formatValue(poddeleniCislaPar) + ","
				+ VfkUtil.formatValue(druhCislovaniPar)
				+ VfkUtil.getTerminator();
	}

}
