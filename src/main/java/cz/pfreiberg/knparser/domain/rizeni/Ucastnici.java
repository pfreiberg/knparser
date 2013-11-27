package cz.pfreiberg.knparser.domain.rizeni;

/**
 * 
 * Třída reprezentující "Účastníci řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Ucastnici {

	private Long id;
	private Long rizeniId;
	private Integer druhUcastnika;
	private String jmeno;
	private String jmenoU;
	private String prijmeni;
	private String prijmeniU;
	private String titulPredJmenem;
	private String titulZaJmenem;
	private String rc;
	private String rodnePrijmeni;
	private Integer rodinnyStav;
	private String obchodniJmeno;
	private String obchodniJmenoU;
	private String dic;
	private Integer ico;
	private Integer doplnekIco;
	private String overenPodpis;
	private Integer overenProtiRs;
	private Integer overenProtiOs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	public Integer getDruhUcastnika() {
		return druhUcastnika;
	}

	public void setDruhUcastnika(Integer druhUcastnika) {
		this.druhUcastnika = druhUcastnika;
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

	public String getTitulPredJmenem() {
		return titulPredJmenem;
	}

	public void setTitulPredJmenem(String titulPredJmenem) {
		this.titulPredJmenem = titulPredJmenem;
	}

	public String getTitulZaJmenem() {
		return titulZaJmenem;
	}

	public void setTitulZaJmenem(String titulZaJmenem) {
		this.titulZaJmenem = titulZaJmenem;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getRodnePrijmeni() {
		return rodnePrijmeni;
	}

	public void setRodnePrijmeni(String rodnePrijmeni) {
		this.rodnePrijmeni = rodnePrijmeni;
	}

	public Integer getRodinnyStav() {
		return rodinnyStav;
	}

	public void setRodinnyStav(Integer rodinnyStav) {
		this.rodinnyStav = rodinnyStav;
	}

	public String getObchodniJmeno() {
		return obchodniJmeno;
	}

	public void setObchodniJmeno(String obchodniJmeno) {
		this.obchodniJmeno = obchodniJmeno;
	}

	public String getObchodniJmenoU() {
		return obchodniJmenoU;
	}

	public void setObchodniJmenoU(String obchodniJmenoU) {
		this.obchodniJmenoU = obchodniJmenoU;
	}

	public String getDic() {
		return dic;
	}

	public void setDic(String dic) {
		this.dic = dic;
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

	public String getOverenPodpis() {
		return overenPodpis;
	}

	public void setOverenPodpis(String overenPodpis) {
		this.overenPodpis = overenPodpis;
	}

	public Integer getOverenProtiRs() {
		return overenProtiRs;
	}

	public void setOverenProtiRs(Integer overenProtiRs) {
		this.overenProtiRs = overenProtiRs;
	}

	public Integer getOverenProtiOs() {
		return overenProtiOs;
	}

	public void setOverenProtiOs(Integer overenProtiOs) {
		this.overenProtiOs = overenProtiOs;
	}

}
