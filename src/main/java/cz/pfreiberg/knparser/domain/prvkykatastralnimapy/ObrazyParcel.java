package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

/**
 * 
 * Třída reprezentující
 * "Obrazy parcel (parcelní číslo, značka druhu pozemku,..)".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ObrazyParcel {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Double rizeniIdVzniku;
	private Double rizeniIdZaniku;
	private Integer typppdKod;
	private Double souradniceY;
	private Double souradniceX;
	private String text;
	private Double velikost;
	private Double uhel;
	private Long parId;
	private String oparType;
	private Integer vztaznyBod;

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

	public Double getRizeniIdVzniku() {
		return rizeniIdVzniku;
	}

	public void setRizeniIdVzniku(Double rizeniIdVzniku) {
		this.rizeniIdVzniku = rizeniIdVzniku;
	}

	public Double getRizeniIdZaniku() {
		return rizeniIdZaniku;
	}

	public void setRizeniIdZaniku(Double rizeniIdZaniku) {
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public String getOparType() {
		return oparType;
	}

	public void setOparType(String oparType) {
		this.oparType = oparType;
	}

	public Integer getVztaznyBod() {
		return vztaznyBod;
	}

	public void setVztaznyBod(Integer vztaznyBod) {
		this.vztaznyBod = vztaznyBod;
	}

}