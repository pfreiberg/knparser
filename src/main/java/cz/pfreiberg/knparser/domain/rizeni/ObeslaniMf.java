package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Obeslání účastníků řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ObeslaniMf {

	private Long obeslaniId;
	private Integer zpusobObeslani;
	private Integer typopeKod;
	private Long ucastId;
	private Integer stavObeslani;
	private Date datumPrijetiDorucenky;
	private Long opsubId;

	public Long getObeslaniId() {
		return obeslaniId;
	}

	public void setObeslaniId(Long obeslaniId) {
		this.obeslaniId = obeslaniId;
	}

	public Integer getZpusobObeslani() {
		return zpusobObeslani;
	}

	public void setZpusobObeslani(Integer zpusobObeslani) {
		this.zpusobObeslani = zpusobObeslani;
	}

	public Integer getTypopeKod() {
		return typopeKod;
	}

	public void setTypopeKod(Integer typopeKod) {
		this.typopeKod = typopeKod;
	}

	public Long getUcastId() {
		return ucastId;
	}

	public void setUcastId(Long ucastId) {
		this.ucastId = ucastId;
	}

	public Integer getStavObeslani() {
		return stavObeslani;
	}

	public void setStavObeslani(Integer stavObeslani) {
		this.stavObeslani = stavObeslani;
	}

	public Date getDatumPrijetiDorucenky() {
		return datumPrijetiDorucenky;
	}

	public void setDatumPrijetiDorucenky(Date datumPrijetiDorucenky) {
		this.datumPrijetiDorucenky = datumPrijetiDorucenky;
	}

	public Long getOpsubId() {
		return opsubId;
	}

	public void setOpsubId(Long opsubId) {
		this.opsubId = opsubId;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(obeslaniId) + ","
				+ VfkUtil.formatValue(zpusobObeslani) + ","
				+ VfkUtil.formatValue(typopeKod) + ","
				+ VfkUtil.formatValue(ucastId) + ","
				+ VfkUtil.formatValue(stavObeslani) + ","
				+ VfkUtil.formatValue(datumPrijetiDorucenky) + ","
				+ VfkUtil.formatValue(opsubId)
				+ VfkUtil.getTerminator();
	}

}
