package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující
 * "Spojení prvků orientační mapy – definuje liniové prvky.".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class SpojeniPoMapy {

	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long poradoveCisloBodu;
	private Double souradniceY;
	private Double souradniceX;
	private Long pomId;
	private String parametrySpojeni;

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

	public Long getPoradoveCisloBodu() {
		return poradoveCisloBodu;
	}

	public void setPoradoveCisloBodu(Long poradoveCisloBodu) {
		this.poradoveCisloBodu = poradoveCisloBodu;
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

	public Long getPomId() {
		return pomId;
	}

	public void setPomId(Long pomId) {
		this.pomId = pomId;
	}

	public String getParametrySpojeni() {
		return parametrySpojeni;
	}

	public void setParametrySpojeni(String parametrySpojeni) {
		this.parametrySpojeni = parametrySpojeni;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(priznakKontextu) + ","
				+ VfkUtil.formatValue(poradoveCisloBodu) + ","
				+ VfkUtil.formatValue(souradniceY) + ","
				+ VfkUtil.formatValue(souradniceX) + ","
				+ VfkUtil.formatValue(pomId) + ","
				+ VfkUtil.formatValue(parametrySpojeni)
				+ VfkUtil.getTerminator();
	}

}
