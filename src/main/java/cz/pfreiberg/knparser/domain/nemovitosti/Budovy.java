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
	private Integer typbudKod;
	private Integer caobceKod;
	private Integer cisloDomovni;
	private Integer cenaNemovitosti;
	private Integer zpvybuKod;
	private Long telId;
	private String docasnaStavba;
	private String jeSoucasti;
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

	public Integer getTypbudKod() {
		return typbudKod;
	}

	public void setTypbudKod(Integer typbudKod) {
		this.typbudKod = typbudKod;
	}

	public Integer getCaobceKod() {
		return caobceKod;
	}

	public void setCaobceKod(Integer caobceKod) {
		this.caobceKod = caobceKod;
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
	
	public String getDocasnaStavba() {
		return docasnaStavba;
	}

	public void setDocasnaStavba(String docasnaStavba) {
		this.docasnaStavba = docasnaStavba;
	}

	public String getJeSoucasti() {
		return jeSoucasti;
	}

	public void setJeSoucasti(String jeSoucasti) {
		this.jeSoucasti = jeSoucasti;
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
				+ VfkUtil.formatValue(typbudKod) + ","
				+ VfkUtil.formatValue(caobceKod) + ","
				+ VfkUtil.formatValue(cisloDomovni) + ","
				+ VfkUtil.formatValue(cenaNemovitosti) + ","
				+ VfkUtil.formatValue(zpvybuKod) + ","
				+ VfkUtil.formatValue(telId) + ","
				+ VfkUtil.formatValue(docasnaStavba) + ","
				+ VfkUtil.formatValue(jeSoucasti) + ","
				+ VfkUtil.formatValue(psId)
				+ VfkUtil.getTerminator();
	}

}
