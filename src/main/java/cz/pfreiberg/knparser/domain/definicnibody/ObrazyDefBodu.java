package cz.pfreiberg.knparser.domain.definicnibody;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Obrazy definičních bodů".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ObrazyDefBodu {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long parId;
	private Long budId;
	private Integer typbudKod;
	private Integer cisloDomovni;
	private Double souradniceY;
	private Double souradniceX;

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

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public Long getBudId() {
		return budId;
	}

	public void setBudId(Long budId) {
		this.budId = budId;
	}

	public Integer getTypbudKod() {
		return typbudKod;
	}

	public void setTypbudKod(Integer typbudKod) {
		this.typbudKod = typbudKod;
	}

	public Integer getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(Integer cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
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

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(id) + ","
				+ VfkUtil.formatValue(stavDat) + ","
				+ VfkUtil.formatValue(datumVzniku) + ","
				+ VfkUtil.formatValue(datumZaniku) + ","
				+ VfkUtil.formatValue(priznakKontextu) + ","
				+ VfkUtil.formatValue(parId) + "," 
				+ VfkUtil.formatValue(budId) + "," 
				+ VfkUtil.formatValue(typbudKod) + ","
				+ VfkUtil.formatValue(cisloDomovni) + ","
				+ VfkUtil.formatValue(souradniceY) + ","
				+ VfkUtil.formatValue(souradniceX) 
				+ VfkUtil.getTerminator();
	}

}
