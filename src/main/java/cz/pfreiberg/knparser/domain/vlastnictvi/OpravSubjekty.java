package cz.pfreiberg.knparser.domain.vlastnictvi;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Oprávněné subjekty".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class OpravSubjekty {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long idJe1PartnerBsm;
	private Long idJe2PartnerBsm;
	private Long idZdroj;
	private String opsubType;
	private Integer charosKod;
	private Integer ico;
	private Integer doplnekIco;
	private String nazev;
	private String nazevU;
	private String rodneCislo;
	private String titulPredJmenem;
	private String jmeno;
	private String jmenoU;
	private String prijmeni;
	private String prijmeniU;
	private String titulZaJmenem;
	private Integer cisloDomovni;
	private String cisloOrientacni;
	private String nazevUlice;
	private String castObce;
	private String obec;
	private String okres;
	private String stat;
	private Integer psc;
	private String mestskaCast;
	private String cpCe;
	private Date datumVzniku2;
	private Long rizeniIdVzniku2;
	private Integer kodAdrm;
	private Long idNadrizenePo;

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

	public Date getDatumVzniku() {
		return datumVzniku;
	}

	public void setDatumVzniku(Date datumVzniku) {
		this.datumVzniku = datumVzniku;
	}

	public Date getDatumZaniku() {
		return datumZaniku;
	}

	public void setDatumZaniku(Date datumZaniku) {
		this.datumZaniku = datumZaniku;
	}

	public Integer getPriznakKontextu() {
		return priznakKontextu;
	}

	public void setPriznakKontextu(Integer priznakKontextu) {
		this.priznakKontextu = priznakKontextu;
	}

	public Long getRizeniIdVzniku() {
		return rizeniIdVzniku;
	}

	public void setRizeniIdVzniku(Long rizeniIdVzniku) {
		this.rizeniIdVzniku = rizeniIdVzniku;
	}

	public Long getRizeniIdZaniku() {
		return rizeniIdZaniku;
	}

	public void setRizeniIdZaniku(Long rizeniIdZaniku) {
		this.rizeniIdZaniku = rizeniIdZaniku;
	}

	public Long getIdJe1PartnerBsm() {
		return idJe1PartnerBsm;
	}

	public void setIdJe1PartnerBsm(Long idJe1PartnerBsm) {
		this.idJe1PartnerBsm = idJe1PartnerBsm;
	}

	public Long getIdJe2PartnerBsm() {
		return idJe2PartnerBsm;
	}

	public void setIdJe2PartnerBsm(Long idJe2PartnerBsm) {
		this.idJe2PartnerBsm = idJe2PartnerBsm;
	}

	public Long getIdZdroj() {
		return idZdroj;
	}

	public void setIdZdroj(Long idZdroj) {
		this.idZdroj = idZdroj;
	}

	public String getOpsubType() {
		return opsubType;
	}

	public void setOpsubType(String opsubType) {
		this.opsubType = opsubType;
	}

	public Integer getCharosKod() {
		return charosKod;
	}

	public void setCharosKod(Integer charosKod) {
		this.charosKod = charosKod;
	}

	public Integer getIco() {
		return ico;
	}

	public void setIco(Integer ico) {
		this.ico = ico;
	}

	public Integer getDoplnekIco() {
		return doplnekIco;
	}

	public void setDoplnekIco(Integer doplnekIco) {
		this.doplnekIco = doplnekIco;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getNazevU() {
		return nazevU;
	}

	public void setNazevU(String nazevU) {
		this.nazevU = nazevU;
	}

	public String getRodneCislo() {
		return rodneCislo;
	}

	public void setRodneCislo(String rodneCislo) {
		this.rodneCislo = rodneCislo;
	}

	public String getTitulPredJmenem() {
		return titulPredJmenem;
	}

	public void setTitulPredJmenem(String titulPredJmenem) {
		this.titulPredJmenem = titulPredJmenem;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public String getJmenoU() {
		return jmenoU;
	}

	public void setJmenoU(String jmenoU) {
		this.jmenoU = jmenoU;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getPrijmeniU() {
		return prijmeniU;
	}

	public void setPrijmeniU(String prijmeniU) {
		this.prijmeniU = prijmeniU;
	}

	public String getTitulZaJmenem() {
		return titulZaJmenem;
	}

	public void setTitulZaJmenem(String titulZaJmenem) {
		this.titulZaJmenem = titulZaJmenem;
	}

	public Integer getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(Integer cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
	}

	public String getCisloOrientacni() {
		return cisloOrientacni;
	}

	public void setCisloOrientacni(String cisloOrientacni) {
		this.cisloOrientacni = cisloOrientacni;
	}

	public String getNazevUlice() {
		return nazevUlice;
	}

	public void setNazevUlice(String nazevUlice) {
		this.nazevUlice = nazevUlice;
	}

	public String getCastObce() {
		return castObce;
	}

	public void setCastObce(String castObce) {
		this.castObce = castObce;
	}

	public String getObec() {
		return obec;
	}

	public void setObec(String obec) {
		this.obec = obec;
	}

	public String getOkres() {
		return okres;
	}

	public void setOkres(String okres) {
		this.okres = okres;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Integer getPsc() {
		return psc;
	}

	public void setPsc(Integer psc) {
		this.psc = psc;
	}

	public String getMestskaCast() {
		return mestskaCast;
	}

	public void setMestskaCast(String mestskaCast) {
		this.mestskaCast = mestskaCast;
	}

	public String getCpCe() {
		return cpCe;
	}

	public void setCpCe(String cpCe) {
		this.cpCe = cpCe;
	}

	public Date getDatumVzniku2() {
		return datumVzniku2;
	}

	public void setDatumVzniku2(Date datumVzniku2) {
		this.datumVzniku2 = datumVzniku2;
	}

	public Long getRizeniIdVzniku2() {
		return rizeniIdVzniku2;
	}

	public void setRizeniIdVzniku2(Long rizeniIdVzniku2) {
		this.rizeniIdVzniku2 = rizeniIdVzniku2;
	}

	public Integer getKodAdrm() {
		return kodAdrm;
	}

	public void setKodAdrm(Integer kodAdrm) {
		this.kodAdrm = kodAdrm;
	}

	public Long getIdNadrizenePo() {
		return idNadrizenePo;
	}

	public void setIdNadrizenePo(Long idNadrizenePo) {
		this.idNadrizenePo = idNadrizenePo;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(id) + ","
				+ VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(priznakKontextu) + ","
				+ VfkUtil.formatValue(rizeniIdVzniku) + ","
				+ VfkUtil.formatValue(rizeniIdZaniku) + ","
				+ VfkUtil.formatValue(idJe1PartnerBsm) + ","
				+ VfkUtil.formatValue(idJe2PartnerBsm) + ","
				+ VfkUtil.formatValue(idZdroj) + ","
				+ VfkUtil.formatValue(opsubType) + ","
				+ VfkUtil.formatValue(charosKod) + ","
				+ VfkUtil.formatValue(ico) + ","
				+ VfkUtil.formatValue(doplnekIco) + ","
				+ VfkUtil.formatValue(nazev) + ","
				+ VfkUtil.formatValue(nazevU) + ","
				+ VfkUtil.formatValue(rodneCislo) + ","
				+ VfkUtil.formatValue(titulPredJmenem) + ","
				+ VfkUtil.formatValue(jmeno) + ","
				+ VfkUtil.formatValue(jmenoU) + ","
				+ VfkUtil.formatValue(prijmeni) + ","
				+ VfkUtil.formatValue(prijmeniU) + ","
				+ VfkUtil.formatValue(titulZaJmenem) + ","
				+ VfkUtil.formatValue(cisloDomovni) + ","
				+ VfkUtil.formatValue(cisloOrientacni) + ","
				+ VfkUtil.formatValue(nazevUlice) + ","
				+ VfkUtil.formatValue(castObce) + ","
				+ VfkUtil.formatValue(obec) + "," 
				+ VfkUtil.formatValue(okres) + "," 
				+ VfkUtil.formatValue(stat) + ","
				+ VfkUtil.formatValue(psc) + ","
				+ VfkUtil.formatValue(mestskaCast) + ","
				+ VfkUtil.formatValue(cpCe) + ","
				+ VfkUtil.formatValue(datumVzniku2) + ","
				+ VfkUtil.formatValue(rizeniIdVzniku2) + ","
				+ VfkUtil.formatValue(kodAdrm) + ","
				+ VfkUtil.formatValue(idNadrizenePo)
				+ VfkUtil.getTerminator();
	}

}
