package cz.pfreiberg.knparser.domain.jinepravnivztahy;

import java.util.Date;

/**
 * Třída reprezentující "Jiné právní vztahy".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class JinePravVztahy {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long parIdPro;
	private Long budIdPro;
	private Long jedIdPro;
	private Long parIdK;
	private Long budIdK;
	private Long jedIdK;
	private String typravKod;
	private String popisPravnihoVztahu;
	private Long telId;
	private Long opsubIdPro;
	private Long opsubIdK;
	private String podilPohledavka;
	private Long hjpvId;
	private Date datumVzniku2;
	private Long rizeniIdVzniku2;
	private Long opsubId2Pro;

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

	public Long getParIdPro() {
		return parIdPro;
	}

	public void setParIdPro(Long parIdPro) {
		this.parIdPro = parIdPro;
	}

	public Long getBudIdPro() {
		return budIdPro;
	}

	public void setBudIdPro(Long budIdPro) {
		this.budIdPro = budIdPro;
	}

	public Long getJedIdPro() {
		return jedIdPro;
	}

	public void setJedIdPro(Long jedIdPro) {
		this.jedIdPro = jedIdPro;
	}

	public Long getParIdK() {
		return parIdK;
	}

	public void setParIdK(Long parIdK) {
		this.parIdK = parIdK;
	}

	public Long getBudIdK() {
		return budIdK;
	}

	public void setBudIdK(Long budIdK) {
		this.budIdK = budIdK;
	}

	public Long getJedIdK() {
		return jedIdK;
	}

	public void setJedIdK(Long jedIdK) {
		this.jedIdK = jedIdK;
	}

	public String getTypravKod() {
		return typravKod;
	}

	public void setTypravKod(String typravKod) {
		this.typravKod = typravKod;
	}

	public String getPopisPravnihoVztahu() {
		return popisPravnihoVztahu;
	}

	public void setPopisPravnihoVztahu(String popisPravnihoVztahu) {
		this.popisPravnihoVztahu = popisPravnihoVztahu;
	}

	public Long getTelId() {
		return telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
	}

	public Long getOpsubIdPro() {
		return opsubIdPro;
	}

	public void setOpsubIdPro(Long opsubIdPro) {
		this.opsubIdPro = opsubIdPro;
	}

	public Long getOpsubIdK() {
		return opsubIdK;
	}

	public void setOpsubIdK(Long opsubIdK) {
		this.opsubIdK = opsubIdK;
	}

	public String getPodilPohledavka() {
		return podilPohledavka;
	}

	public void setPodilPohledavka(String podilPohledavka) {
		this.podilPohledavka = podilPohledavka;
	}

	public Long getHjpvId() {
		return hjpvId;
	}

	public void setHjpvId(Long hjpvId) {
		this.hjpvId = hjpvId;
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

	public Long getOpsubId2Pro() {
		return opsubId2Pro;
	}

	public void setOpsubId2Pro(Long opsubId2Pro) {
		this.opsubId2Pro = opsubId2Pro;
	}

}
