package cz.pfreiberg.knparser.domain.jinepravnivztahy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Vazba JPV k jinému věcnému právu".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RJpv {

	private Long id;
	private Long verze;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long hjpvId1;
	private Long hjpvId2;
	private Integer typvazbyJpv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVerze() {
		return verze;
	}

	public void setVerze(Long verze) {
		this.verze = verze;
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

	public Long getHjpvId1() {
		return hjpvId1;
	}

	public void setHjpvId1(Long hjpvId1) {
		this.hjpvId1 = hjpvId1;
	}

	public Long getHjpvId2() {
		return hjpvId2;
	}

	public void setHjpvId2(Long hjpvId2) {
		this.hjpvId2 = hjpvId2;
	}

	public Integer getTypvazbyJpv() {
		return typvazbyJpv;
	}

	public void setTypvazbyJpv(Integer typvazbyJpv) {
		this.typvazbyJpv = typvazbyJpv;
	}
	
	public String toString() {
		return "" + VfkUtil.formatValue(id) + ","
				+ VfkUtil.formatValue(verze) + ","
				+ VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(rizeniIdVzniku) + ","
				+ VfkUtil.formatValue(rizeniIdZaniku) + ","
				+ VfkUtil.formatValue(hjpvId1) + ","
				+ VfkUtil.formatValue(hjpvId2) + ","
				+ VfkUtil.formatValue(typvazbyJpv)
				+ VfkUtil.getTerminator();
	}

}
