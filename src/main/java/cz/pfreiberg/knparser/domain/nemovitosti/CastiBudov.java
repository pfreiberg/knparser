package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Části budov".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class CastiBudov {

	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long budId;
	private Integer typBudKod;
	private Integer cisloDomovni;
	private Double cenaNemovitosti;

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

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public Integer getTypBudKod() {
		return typBudKod;
	}

	public void setTypBudKod(Integer typBudKod) {
		this.typBudKod = typBudKod;
	}

	public Integer getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(Integer cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
	}

	public Double getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(Double cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	@Override
	public String toString() {
		return "CastiBudov [stavDat=" + stavDat + ", datumVzniku="
				+ datumVzniku + ", datumZaniku=" + datumZaniku
				+ ", priznakKontextu=" + priznakKontextu + ", rizeniIdVzniku="
				+ rizeniIdVzniku + ", rizeniIdZaniku=" + rizeniIdZaniku
				+ ", budId=" + budId + ", typBudKod=" + typBudKod
				+ ", cisloDomovni=" + cisloDomovni + ", cenaNemovitosti="
				+ cenaNemovitosti + "]";
	}

}
