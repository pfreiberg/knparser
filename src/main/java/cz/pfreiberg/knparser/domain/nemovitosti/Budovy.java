package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Budovy".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Budovy {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Integer typBudKod;
	private Integer caoObceKod;
	private Integer cisloDomovni;
	private Integer cenaNemovitosti;
	private Integer zpvybuKod;
	private Long telId;

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

	public Integer getTypBudKod() {
		return typBudKod;
	}

	public void setTypBudKod(Integer typBudKod) {
		this.typBudKod = typBudKod;
	}

	public Integer getCaoObceKod() {
		return caoObceKod;
	}

	public void setCaoObceKod(Integer caoObceKod) {
		this.caoObceKod = caoObceKod;
	}

	public Integer getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(Integer cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
	}

	public Integer getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(Integer cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	public Integer getZpvybuKod() {
		return zpvybuKod;
	}

	public void setZpvybuKod(Integer zpvybuKod) {
		this.zpvybuKod = zpvybuKod;
	}

	public Long getTelId() {
		return telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
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
				+ VfkUtil.formatValue(typBudKod) + ","
				+ VfkUtil.formatValue(caoObceKod) + ","
				+ VfkUtil.formatValue(cisloDomovni) + ","
				+ VfkUtil.formatValue(cenaNemovitosti) + ","
				+ VfkUtil.formatValue(zpvybuKod) + ","
				+ VfkUtil.formatValue(telId) 
				+ VfkUtil.getTerminator();
	}

}
