package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující
 * "Výběr způsobu ochrany k nemovitostem na základě exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RZpochr {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Integer zpochrKod;
	private Long parId;
	private Long budId;
	private Long jedId;

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

	public Integer getZpochrKod() {
		return zpochrKod;
	}

	public void setZpochrKod(Integer zpochrKod) {
		this.zpochrKod = zpochrKod;
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public Long getJedId() {
		return jedId;
	}

	public void setJedId(Long jedId) {
		this.jedId = jedId;
	}

	@Override
	public String toString() {
		return "RZpochr [id=" + id + ", stavDat=" + stavDat + ", datumVzniku="
				+ datumVzniku + ", datumZaniku=" + datumZaniku
				+ ", priznakKontextu=" + priznakKontextu + ", rizeniIdVzniku="
				+ rizeniIdVzniku + ", rizeniIdZaniku=" + rizeniIdZaniku
				+ ", zpochrKod=" + zpochrKod + ", parId=" + parId + ", budId="
				+ budId + ", jedId=" + jedId + "]";
	}

}
