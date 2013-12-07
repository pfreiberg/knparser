package cz.pfreiberg.knparser.domain.geometrickyplan;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Vazební tabulka návrhy změn KM – ZPMZ".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class NzZpmz {

	private Long nzId;
	private Integer zpmzCisloZpmz;
	private Integer zpmzKatuzeKod;

	public Long getNzId() {
		return nzId;
	}

	public void setNzId(Long nzId) {
		this.nzId = nzId;
	}

	public Integer getZpmzCisloZpmz() {
		return zpmzCisloZpmz;
	}

	public void setZpmzCisloZpmz(Integer zpmzCisloZpmz) {
		this.zpmzCisloZpmz = zpmzCisloZpmz;
	}

	public Integer getZpmzKatuzeKod() {
		return zpmzKatuzeKod;
	}

	public void setZpmzKatuzeKod(Integer zpmzKatuzeKod) {
		this.zpmzKatuzeKod = zpmzKatuzeKod;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(nzId) + ","
				+ VfkUtil.formatValue(zpmzCisloZpmz) + ","
				+ VfkUtil.formatValue(zpmzKatuzeKod) 
				+ VfkUtil.getTerminator();
	}

}
