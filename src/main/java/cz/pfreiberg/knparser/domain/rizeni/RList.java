package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující
 * "Přiřazení listin k nemovitostem, vlastnictví a jiným právním vztahům".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RList implements DomainWithDate {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long listinId;
	private Long parId;
	private Long budId;
	private Long jedId;
	private Long opsubId;
	private Long jpvId;
	private Date datumVzniku2;
	private Long rizeniIdVzniku2;
	private Long psId;

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

	public Long getListinId() {
		return listinId;
	}

	public void setListinId(Long listinId) {
		this.listinId = listinId;
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

	public Long getJedId() {
		return jedId;
	}

	public void setJedId(Long jedId) {
		this.jedId = jedId;
	}

	public Long getOpsubId() {
		return opsubId;
	}

	public void setOpsubId(Long opsubId) {
		this.opsubId = opsubId;
	}

	public Long getJpvId() {
		return jpvId;
	}

	public void setJpvId(Long jpvId) {
		this.jpvId = jpvId;
	}

	public Date getDatumVzniku2() {
		return datumVzniku2;
	}

	public void setDatumVzniku2(Date datumVzniku2) {
		this.datumVzniku2 = datumVzniku2;
	}

	public Long getRizeniIdVzniku2() {
		return rizeniIdVzniku2;
	}

	public void setRizeniIdVzniku2(Long rizeniIdVzniku2) {
		this.rizeniIdVzniku2 = rizeniIdVzniku2;
	}

	public Long getPsId() {
		return psId;
	}

	public void setPsId(Long psId) {
		this.psId = psId;
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
				+ VfkUtil.formatValue(listinId) + ","
				+ VfkUtil.formatValue(parId) + "," 
				+ VfkUtil.formatValue(budId) + ","
				+ VfkUtil.formatValue(jedId) + ","
				+ VfkUtil.formatValue(opsubId) + ","
				+ VfkUtil.formatValue(jpvId) + "," 
				+ VfkUtil.formatValue(datumVzniku2) + ","
				+ VfkUtil.formatValue(rizeniIdVzniku2) + ","
				+ VfkUtil.formatValue(psId)
				+ VfkUtil.getTerminator();
	}

}
