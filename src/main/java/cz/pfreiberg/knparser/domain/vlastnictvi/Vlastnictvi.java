package cz.pfreiberg.knparser.domain.vlastnictvi;

import java.util.Date;

/**
 * Třída reprezentující "Vlastnictví".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Vlastnictvi {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long opsubId;
	private String typravKod;
	private Long telId;
	private Long podilCitatel;
	private Long podilJmenovatel;
	private Date datumVzniku2;
	private Long rizeniIdVzniku2;

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

	public Long getOpsubId() {
		return opsubId;
	}

	public void setOpsubId(Long opsubId) {
		this.opsubId = opsubId;
	}

	public String getTypravKod() {
		return typravKod;
	}

	public void setTypravKod(String typravKod) {
		this.typravKod = typravKod;
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

}
