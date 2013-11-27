package cz.pfreiberg.knparser.domain.jednotky;

import java.util.Date;

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
	private Integer ciselnikJednotky;
	private Float cenaNemovitosti;
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

	public Integer getCiselnikJednotky() {
		return ciselnikJednotky;
	}

	public void setCiselnikJednotky(Integer ciselnikJednotky) {
		this.ciselnikJednotky = ciselnikJednotky;
	}

	public Float getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(Float cenaNemovitosti) {
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

}
