package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Obrazy budov (obvod budovy, značka druhu budovy)".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ObrazyBudov {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Integer typppdKod;
	private Double souradniceY;
	private Double souradniceX;
	private Double velikost;
	private Double uhel;
	private Long budId;
	private String obrbudType;

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

	public Double getSouradniceY() {
		return souradniceY;
	}

	public void setSouradniceY(Double souradniceY) {
		this.souradniceY = souradniceY;
	}

	public Double getSouradniceX() {
		return souradniceX;
	}

	public void setSouradniceX(Double souradniceX) {
		this.souradniceX = souradniceX;
	}

	public Double getVelikost() {
		return velikost;
	}

	public void setVelikost(Double velikost) {
		this.velikost = velikost;
	}

	public Double getUhel() {
		return uhel;
	}

	public void setUhel(Double uhel) {
		this.uhel = uhel;
	}

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public String getObrbudType() {
		return obrbudType;
	}

	public void setObrbudType(String obrbudType) {
		this.obrbudType = obrbudType;
	}

}
