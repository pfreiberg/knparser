package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Hranice parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class HraniceParcel {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Integer typppdKod;
	private Long parId1;
	private Long parId2;

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

	public Integer getTypppdKod() {
		return typppdKod;
	}

	public void setTypppdKod(Integer typppdKod) {
		this.typppdKod = typppdKod;
	}

	public Long getParId1() {
		return parId1;
	}

	public void setParId1(Long parId1) {
		this.parId1 = parId1;
	}

	public Long getParId2() {
		return parId2;
	}

	public void setParId2(Long parId2) {
		this.parId2 = parId2;
	}

}
