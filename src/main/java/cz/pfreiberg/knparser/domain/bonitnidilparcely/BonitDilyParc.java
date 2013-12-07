package cz.pfreiberg.knparser.domain.bonitnidilparcely;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Bonitní díly parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class BonitDilyParc {

	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long parId;
	private String bpejKod;
	private Integer vymera;

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

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public String getBpejKod() {
		return bpejKod;
	}

	public void setBpejKod(String bpejKod) {
		this.bpejKod = bpejKod;
	}

	public Integer getVymera() {
		return vymera;
	}

	public void setVymera(Integer vymera) {
		this.vymera = vymera;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(priznakKontextu) + ","
				+ VfkUtil.formatValue(rizeniIdVzniku) + ","
				+ VfkUtil.formatValue(rizeniIdZaniku) + ","
				+ VfkUtil.formatValue(parId) + ","
				+ VfkUtil.formatValue(bpejKod) + ","
				+ VfkUtil.formatValue(vymera) 
				+ VfkUtil.getTerminator();
	}

}
