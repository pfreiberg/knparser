package cz.pfreiberg.knparser.domain.bpej;

import java.util.Date;

import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Označení BPEJ".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class OznaceniBpej implements DomainWithDate {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long typppdKod;
	private Double souradniceY;
	private Double souradniceX;
	private String text;
	private Double velikost;
	private Double uhel;
	private String bpejKod;
	private Integer katuzeKod;
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

	public Long getTypppdKod() {
		return typppdKod;
	}

	public void setTypppdKod(Long typppdKod) {
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

	public String getBpejKod() {
		return bpejKod;
	}

	public void setBpejKod(String bpejKod) {
		this.bpejKod = bpejKod;
	}

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getVztaznyBod() {
		return vztaznyBod;
	}

	public void setVztaznyBod(Integer vztaznyBod) {
		this.vztaznyBod = vztaznyBod;
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
				+ VfkUtil.formatValue(typppdKod) + ","
				+ VfkUtil.formatValue(souradniceY) + ","
				+ VfkUtil.formatValue(souradniceX) + ","
				+ VfkUtil.formatValue(text) + ","
				+ VfkUtil.formatValue(velikost) + ","
				+ VfkUtil.formatValue(uhel) + ","
				+ VfkUtil.formatValue(bpejKod) + ","
				+ VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(vztaznyBod) 
				+ VfkUtil.getTerminator();
	}

}