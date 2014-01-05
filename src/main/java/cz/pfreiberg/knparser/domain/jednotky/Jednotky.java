package cz.pfreiberg.knparser.domain.jednotky;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Jednotky".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Jednotky {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long budId;
	private Integer typjedKod;
	private Integer cisloJednotky;
	private Double cenaNemovitosti;
	private Integer zpvyjeKod;
	private Long telId;
	private Long podilCitatel;
	private Long podilJmenovatel;
	private String popis;

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

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public Integer getTypjedKod() {
		return typjedKod;
	}

	public void setTypjedKod(Integer typjedKod) {
		this.typjedKod = typjedKod;
	}

	public Integer getCisloJednotky() {
		return cisloJednotky;
	}

	public void setCisloJednotky(Integer cisloJednotky) {
		this.cisloJednotky = cisloJednotky;
	}

	public Double getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(Double cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	public Integer getZpvyjeKod() {
		return zpvyjeKod;
	}

	public void setZpvyjeKod(Integer zpvyjeKod) {
		this.zpvyjeKod = zpvyjeKod;
	}

	public Long getTelId() {
		return telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
	}

	public Long getPodilCitatel() {
		return podilCitatel;
	}

	public void setPodilCitatel(Long podilCitatel) {
		this.podilCitatel = podilCitatel;
	}

	public Long getPodilJmenovatel() {
		return podilJmenovatel;
	}

	public void setPodilJmenovatel(Long podilJmenovatel) {
		this.podilJmenovatel = podilJmenovatel;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
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
				+ VfkUtil.formatValue(budId) + ","
				+ VfkUtil.formatValue(typjedKod) + ","
				+ VfkUtil.formatValue(cisloJednotky) + ","
				+ VfkUtil.formatValue(cenaNemovitosti) + ","
				+ VfkUtil.formatValue(zpvyjeKod) + ","
				+ VfkUtil.formatValue(telId) + ","
				+ VfkUtil.formatValue(podilCitatel) + ","
				+ VfkUtil.formatValue(podilJmenovatel) + ","
				+ VfkUtil.formatValue(popis) + VfkUtil.getTerminator();
	}

}
