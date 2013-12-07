package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Prvky orientační mapy".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class PrvkyOMapy {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Integer typppdKod;
	private String text;
	private Double velikost;
	private Double uhel;
	private Integer vztaznyBod;
	private Integer katuzeKod;

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

	public Integer getVztaznyBod() {
		return vztaznyBod;
	}

	public void setVztaznyBod(Integer vztaznyBod) {
		this.vztaznyBod = vztaznyBod;
	}

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
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
				+ VfkUtil.formatValue(text) + ","
				+ VfkUtil.formatValue(velikost) + ","
				+ VfkUtil.formatValue(uhel) + ","
				+ VfkUtil.formatValue(vztaznyBod) + ","
				+ VfkUtil.formatValue(katuzeKod)
				+ VfkUtil.getTerminator();
	}

}
