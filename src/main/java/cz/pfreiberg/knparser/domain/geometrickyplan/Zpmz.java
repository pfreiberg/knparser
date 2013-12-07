package cz.pfreiberg.knparser.domain.geometrickyplan;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Hlavičky ZPMZ".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Zpmz {

	private Integer katuzeKod;
	private Integer cisloZpmz;
	private Long ppzId;
	private Integer stavZpmz;
	private String merickyNacrt;
	private String zapisnikPodrobMereni;
	private String vypocetProtokolVymer;
	private Integer typsosKod;

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

	public Long getPpzId() {
		return ppzId;
	}

	public void setPpzId(Long ppzId) {
		this.ppzId = ppzId;
	}

	public Integer getStavZpmz() {
		return stavZpmz;
	}

	public void setStavZpmz(Integer stavZpmz) {
		this.stavZpmz = stavZpmz;
	}

	public String getMerickyNacrt() {
		return merickyNacrt;
	}

	public void setMerickyNacrt(String merickyNacrt) {
		this.merickyNacrt = merickyNacrt;
	}

	public String getZapisnikPodrobMereni() {
		return zapisnikPodrobMereni;
	}

	public void setZapisnikPodrobMereni(String zapisnikPodrobMereni) {
		this.zapisnikPodrobMereni = zapisnikPodrobMereni;
	}

	public String getVypocetProtokolVymer() {
		return vypocetProtokolVymer;
	}

	public void setVypocetProtokolVymer(String vypocetProtokolVymer) {
		this.vypocetProtokolVymer = vypocetProtokolVymer;
	}

	public Integer getTypsosKod() {
		return typsosKod;
	}

	public void setTypsosKod(Integer typsosKod) {
		this.typsosKod = typsosKod;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(cisloZpmz) + ","
				+ VfkUtil.formatValue(ppzId) + ","
				+ VfkUtil.formatValue(stavZpmz) + ","
				+ VfkUtil.formatValue(merickyNacrt) + ","
				+ VfkUtil.formatValue(zapisnikPodrobMereni) + ","
				+ VfkUtil.formatValue(vypocetProtokolVymer) + ","
				+ VfkUtil.formatValue(typsosKod)
				+ VfkUtil.getTerminator();
	}

}
