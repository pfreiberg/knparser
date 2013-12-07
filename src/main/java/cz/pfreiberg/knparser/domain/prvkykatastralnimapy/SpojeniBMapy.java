package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující
 * "Spojení bodů mapy – definuje nepolohopisné liniové prvky".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class SpojeniBMapy {

	private Date datumVzniku;
	private Date datumZaniku;
	private Long poradoveCisloBodu;
	private Double souradniceY;
	private Double souradniceX;
	private Long opId;
	private Long dmpId;
	private Long hbpejId;
	private String parametrySpojeni;
	private Integer priznakKontextu;

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

	public Long getOpId() {
		return opId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public Long getDmpId() {
		return dmpId;
	}

	public void setDmpId(Long dmpId) {
		this.dmpId = dmpId;
	}

	public Long getHbpejId() {
		return hbpejId;
	}

	public void setHbpejId(Long hbpejId) {
		this.hbpejId = hbpejId;
	}

	public String getParametrySpojeni() {
		return parametrySpojeni;
	}

	public void setParametrySpojeni(String parametrySpojeni) {
		this.parametrySpojeni = parametrySpojeni;
	}

	public Integer getPriznakKontextu() {
		return priznakKontextu;
	}

	public void setPriznakKontextu(Integer priznakKontextu) {
		this.priznakKontextu = priznakKontextu;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(poradoveCisloBodu) + ","
				+ VfkUtil.formatValue(souradniceY) + ","
				+ VfkUtil.formatValue(souradniceX) + ","
				+ VfkUtil.formatValue(opId) + "," 
				+ VfkUtil.formatValue(dmpId) + "," 
				+ VfkUtil.formatValue(hbpejId) + ","
				+ VfkUtil.formatValue(parametrySpojeni) + "," 
				+ VfkUtil.formatValue(priznakKontextu)
				+ VfkUtil.getTerminator();
	}

}
