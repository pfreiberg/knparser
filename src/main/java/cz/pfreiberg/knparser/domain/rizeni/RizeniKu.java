package cz.pfreiberg.knparser.domain.rizeni;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Vazba Řízení – Katastrální území".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RizeniKu {

	private Integer katuzeKod;
	private Long rizeniId;

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(rizeniId)
				+ VfkUtil.getTerminator();

	}

}
