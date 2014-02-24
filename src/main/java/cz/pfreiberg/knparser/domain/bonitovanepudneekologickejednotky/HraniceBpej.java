package cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky;

import java.util.Date;

import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Hranice BPEJ".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class HraniceBpej implements DomainWithDate {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long typppdKod;
	private String bpejKodHranice1;
	private String bpejKodHranice2;
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

	public Long getTypppdKod() {
		return typppdKod;
	}

	public void setTypppdKod(Long typppdKod) {
		this.typppdKod = typppdKod;
	}

	public String getBpejKodHranice1() {
		return bpejKodHranice1;
	}

	public void setBpejKodHranice1(String bpejKodHranice1) {
		this.bpejKodHranice1 = bpejKodHranice1;
	}

	public String getBpejKodHranice2() {
		return bpejKodHranice2;
	}

	public void setBpejKodHranice2(String bpejKodHranice2) {
		this.bpejKodHranice2 = bpejKodHranice2;
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
				+ VfkUtil.formatValue(bpejKodHranice1) + ","
				+ VfkUtil.formatValue(bpejKodHranice2) + ","
				+ VfkUtil.formatValue(katuzeKod) 
				+ VfkUtil.getTerminator();
	}

}
