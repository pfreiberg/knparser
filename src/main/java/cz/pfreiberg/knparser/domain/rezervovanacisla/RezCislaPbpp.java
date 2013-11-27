package cz.pfreiberg.knparser.domain.rezervovanacisla;

/**
 * 
 * Třída reprezentující "Rezervovaná čísla bodu PBPP".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RezCislaPbpp {

	private Integer cisloBodu;
	private Integer katuzeKod;
	private Integer rezzpmzCisloZpmz;
	private Long rizeniId;

	public Integer getCisloBodu() {
		return cisloBodu;
	}

	public void setCisloBodu(Integer cisloBodu) {
		this.cisloBodu = cisloBodu;
	}

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getRezzpmzCisloZpmz() {
		return rezzpmzCisloZpmz;
	}

	public void setRezzpmzCisloZpmz(Integer rezzpmzCisloZpmz) {
		this.rezzpmzCisloZpmz = rezzpmzCisloZpmz;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

}
