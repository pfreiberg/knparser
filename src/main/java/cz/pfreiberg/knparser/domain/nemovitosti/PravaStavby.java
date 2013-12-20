package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Práva stavby".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class PravaStavby {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Date datumPrijeti;
	private Long telId;
	private Date datumUkonceni;

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

	public Date getDatumPrijeti() {
		return datumPrijeti;
	}

	public void setDatumPrijeti(Date datumPrijeti) {
		this.datumPrijeti = datumPrijeti;
	}

	public Long getTelId() {
		return telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
	}

	public Date getDatumUkonceni() {
		return datumUkonceni;
	}

	public void setDatumUkonceni(Date datumUkonceni) {
		this.datumUkonceni = datumUkonceni;
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
				+ VfkUtil.formatValue(datumPrijeti) + ","
				+ VfkUtil.formatValue(telId) + ","
				+ VfkUtil.formatValue(datumUkonceni) + VfkUtil.getTerminator();
	}

}
