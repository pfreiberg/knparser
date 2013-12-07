package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Souřadnice obrazu bodů polohopisu v mapě".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class SouradniceObrazu {

	private Long id;
	private Integer stavDat;
	private Integer katuzeKod;
	private Integer cisloZpmz;
	private Integer cisloTl;
	private Long cisloBodu;
	private Long uplneCislo;
	private Double souradniceY;
	private Double souradniceX;
	private Integer kodchbKod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStavDat() {
		return stavDat;
	}

	public void setStavDat(Integer stavDat) {
		this.stavDat = stavDat;
	}

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getCisloZpmz() {
		return cisloZpmz;
	}

	public void setCisloZpmz(Integer cisloZpmz) {
		this.cisloZpmz = cisloZpmz;
	}

	public Integer getCisloTl() {
		return cisloTl;
	}

	public void setCisloTl(Integer cisloTl) {
		this.cisloTl = cisloTl;
	}

	public Long getCisloBodu() {
		return cisloBodu;
	}

	public void setCisloBodu(Long cisloBodu) {
		this.cisloBodu = cisloBodu;
	}

	public Long getUplneCislo() {
		return uplneCislo;
	}

	public void setUplneCislo(Long uplneCislo) {
		this.uplneCislo = uplneCislo;
	}

	public Double getSouradniceY() {
		return souradniceY;
	}

	public void setSouradniceY(Double souradniceY) {
		this.souradniceY = souradniceY;
	}

	public Double getSouradniceX() {
		return souradniceX;
	}

	public void setSouradniceX(Double souradniceX) {
		this.souradniceX = souradniceX;
	}

	public Integer getKodchbKod() {
		return kodchbKod;
	}

	public void setKodchbKod(Integer kodchbKod) {
		this.kodchbKod = kodchbKod;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(id) + ","
				+ VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(cisloZpmz) + ","
				+ VfkUtil.formatValue(cisloTl) + ","
				+ VfkUtil.formatValue(cisloBodu) + ","
				+ VfkUtil.formatValue(uplneCislo) + ","
				+ VfkUtil.formatValue(souradniceY) + ","
				+ VfkUtil.formatValue(souradniceX) + ","
				+ VfkUtil.formatValue(kodchbKod)
				+ VfkUtil.getTerminator();
	}

}
