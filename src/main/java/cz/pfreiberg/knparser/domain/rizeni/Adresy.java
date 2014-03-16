package cz.pfreiberg.knparser.domain.rizeni;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Adresy účastníků řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Adresy {

	private Long ucastId;
	private Integer typAdresy;
	private String okres;
	private String obec;
	private String castObce;
	private Integer cisloDomovni;
	private String nazevUlice;
	private String cisloOrientacni;
	private Integer psc;
	private String stat;
	private String telefon;
	private String fax;
	private String mestskaCast;
	private Integer cpCe;
	private Integer kodAdrm;

	public Long getUcastId() {
		return ucastId;
	}

	public void setUcastId(Long ucastId) {
		this.ucastId = ucastId;
	}

	public Integer getTypAdresy() {
		return typAdresy;
	}

	public void setTypAdresy(Integer typAdresy) {
		this.typAdresy = typAdresy;
	}

	public String getOkres() {
		return okres;
	}

	public void setOkres(String okres) {
		this.okres = okres;
	}

	public String getObec() {
		return obec;
	}

	public void setObec(String obec) {
		this.obec = obec;
	}

	public String getCastObce() {
		return castObce;
	}

	public void setCastObce(String castObce) {
		this.castObce = castObce;
	}

	public Integer getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(Integer cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
	}

	public String getNazevUlice() {
		return nazevUlice;
	}

	public void setNazevUlice(String nazevUlice) {
		this.nazevUlice = nazevUlice;
	}

	public String getCisloOrientacni() {
		return cisloOrientacni;
	}

	public void setCisloOrientacni(String cisloOrientacni) {
		this.cisloOrientacni = cisloOrientacni;
	}

	public Integer getPsc() {
		return psc;
	}

	public void setPsc(Integer psc) {
		this.psc = psc;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMestskaCast() {
		return mestskaCast;
	}

	public void setMestskaCast(String mestskaCast) {
		this.mestskaCast = mestskaCast;
	}

	public Integer getCpCe() {
		return cpCe;
	}

	public void setCpCe(Integer cpCe) {
		this.cpCe = cpCe;
	}

	public Integer getKodAdrm() {
		return kodAdrm;
	}

	public void setKodAdrm(Integer kodAdrm) {
		this.kodAdrm = kodAdrm;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(ucastId) + ","
				+ VfkUtil.formatValue(typAdresy) + ","
				+ VfkUtil.formatValue(okres) + "," 
				+ VfkUtil.formatValue(obec) + "," 
				+ VfkUtil.formatValue(castObce) + ","
				+ VfkUtil.formatValue(cisloDomovni) + ","
				+ VfkUtil.formatValue(nazevUlice) + ","
				+ VfkUtil.formatValue(cisloOrientacni) + ","
				+ VfkUtil.formatValue(psc) + ","
				+ VfkUtil.formatValue(stat) + "," 
				+ VfkUtil.formatValue(telefon) + ","
				+ VfkUtil.formatValue(fax) + ","
				+ VfkUtil.formatValue(mestskaCast) + ","
				+ VfkUtil.formatValue(cpCe) + ","
				+ VfkUtil.formatValue(kodAdrm)
				+ VfkUtil.getTerminator();
	}

}
