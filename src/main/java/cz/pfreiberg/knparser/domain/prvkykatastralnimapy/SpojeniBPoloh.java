package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

/**
 * 
 * Třída reprezentující
 * "Spojení bodů polohopisu – definuje polohopisné liniové prvky".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class SpojeniBPoloh {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long bpId;
	private Long poradoveCisloBodu;
	private Long obId;
	private Long hpId;
	private Long dpmId;
	private String parametrySpojeni;
	private Long zvbId;

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

	public Long getBpId() {
		return bpId;
	}

	public void setBpId(Long bpId) {
		this.bpId = bpId;
	}

	public Long getPoradoveCisloBodu() {
		return poradoveCisloBodu;
	}

	public void setPoradoveCisloBodu(Long poradoveCisloBodu) {
		this.poradoveCisloBodu = poradoveCisloBodu;
	}

	public Long getObId() {
		return obId;
	}

	public void setObId(Long obId) {
		this.obId = obId;
	}

	public Long getHpId() {
		return hpId;
	}

	public void setHpId(Long hpId) {
		this.hpId = hpId;
	}

	public Long getDpmId() {
		return dpmId;
	}

	public void setDpmId(Long dpmId) {
		this.dpmId = dpmId;
	}

	public String getParametrySpojeni() {
		return parametrySpojeni;
	}

	public void setParametrySpojeni(String parametrySpojeni) {
		this.parametrySpojeni = parametrySpojeni;
	}

	public Long getZvbId() {
		return zvbId;
	}

	public void setZvbId(Long zvbId) {
		this.zvbId = zvbId;
	}

}
