package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Parcely".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Parcely {

	private Long id;
	private Integer stavDat;
	private Date datumVzniku;
	private Date datumZaniku;
	private Integer priznakKontextu;
	private Long rizeniIdVzniku;
	private Long rizeniIdZaniku;
	private Long pknId;
	private String parType;
	private Integer katuzeKod;
	private Integer katuzeKodPuv;
	private Integer druhCislovaniPar;
	private Integer kmenoveCisloPar;
	private Integer zdpazeKod;
	private Integer poddeleniCislaPar;
	private Integer dilParcely;
	private Long maplisKod;
	private Integer zpurvyKod;
	private Integer drupozKod;
	private Integer zpvypaKod;
	private Integer typParcely;
	private Integer vymeraParcely;
	private Double cenaNemovitosti;
	private String definiciniBodPar;
	private Long telId;
	private Long parId;
	private Long budId;
	private String identBud;
	private String soucasti;

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

	public Long getPknId() {
		return pknId;
	}

	public void setPknId(Long pknId) {
		this.pknId = pknId;
	}

	public String getParType() {
		return parType;
	}

	public void setParType(String parType) {
		this.parType = parType;
	}

	public Integer getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(Integer katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public Integer getKatuzeKodPuv() {
		return katuzeKodPuv;
	}

	public void setKatuzeKodPuv(Integer katuzeKodPuv) {
		this.katuzeKodPuv = katuzeKodPuv;
	}

	public Integer getDruhCislovaniPar() {
		return druhCislovaniPar;
	}

	public void setDruhCislovaniPar(Integer druhCislovaniPar) {
		this.druhCislovaniPar = druhCislovaniPar;
	}

	public Integer getKmenoveCisloPar() {
		return kmenoveCisloPar;
	}

	public void setKmenoveCisloPar(Integer kmenoveCisloPar) {
		this.kmenoveCisloPar = kmenoveCisloPar;
	}

	public Integer getZdpazeKod() {
		return zdpazeKod;
	}

	public void setZdpazeKod(Integer zdpazeKod) {
		this.zdpazeKod = zdpazeKod;
	}

	public Integer getPoddeleniCislaPar() {
		return poddeleniCislaPar;
	}

	public void setPoddeleniCislaPar(Integer poddeleniCislaPar) {
		this.poddeleniCislaPar = poddeleniCislaPar;
	}

	public Integer getDilParcely() {
		return dilParcely;
	}

	public void setDilParcely(Integer dilParcely) {
		this.dilParcely = dilParcely;
	}

	public Long getMaplisKod() {
		return maplisKod;
	}

	public void setMaplisKod(Long maplisKod) {
		this.maplisKod = maplisKod;
	}

	public Integer getZpurvyKod() {
		return zpurvyKod;
	}

	public void setZpurvyKod(Integer zpurvyKod) {
		this.zpurvyKod = zpurvyKod;
	}

	public Integer getDrupozKod() {
		return drupozKod;
	}

	public void setDrupozKod(Integer drupozKod) {
		this.drupozKod = drupozKod;
	}

	public Integer getZpvypaKod() {
		return zpvypaKod;
	}

	public void setZpvypaKod(Integer zpvypaKod) {
		this.zpvypaKod = zpvypaKod;
	}

	public Integer getTypParcely() {
		return typParcely;
	}

	public void setTypParcely(Integer typParcely) {
		this.typParcely = typParcely;
	}

	public Integer getVymeraParcely() {
		return vymeraParcely;
	}

	public void setVymeraParcely(Integer vymeraParcely) {
		this.vymeraParcely = vymeraParcely;
	}

	public Double getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(Double cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	public String getDefiniciniBodPar() {
		return definiciniBodPar;
	}

	public void setDefiniciniBodPar(String definiciniBodPar) {
		this.definiciniBodPar = definiciniBodPar;
	}

	public Long getTelId() {
		return telId;
	}

	public void setTelId(Long telId) {
		this.telId = telId;
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

	public String getIdentBud() {
		return identBud;
	}

	public void setIdentBud(String identBud) {
		this.identBud = identBud;
	}
	

	public String getSoucasti() {
		return soucasti;
	}

	public void setSoucasti(String soucasti) {
		this.soucasti = soucasti;
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
				+ VfkUtil.formatValue(pknId) + ","
				+ VfkUtil.formatValue(parType) + ","
				+ VfkUtil.formatValue(katuzeKod) + ","
				+ VfkUtil.formatValue(katuzeKodPuv) + ","
				+ VfkUtil.formatValue(druhCislovaniPar) + ","
				+ VfkUtil.formatValue(kmenoveCisloPar) + ","
				+ VfkUtil.formatValue(zdpazeKod) + ","
				+ VfkUtil.formatValue(poddeleniCislaPar) + ","
				+ VfkUtil.formatValue(dilParcely) + ","
				+ VfkUtil.formatValue(maplisKod) + ","
				+ VfkUtil.formatValue(zpurvyKod) + ","
				+ VfkUtil.formatValue(drupozKod) + ","
				+ VfkUtil.formatValue(zpvypaKod) + ","
				+ VfkUtil.formatValue(typParcely) + ","
				+ VfkUtil.formatValue(vymeraParcely) + ","
				+ VfkUtil.formatValue(cenaNemovitosti) + ","
				+ VfkUtil.formatValue(definiciniBodPar) + ","
				+ VfkUtil.formatValue(telId) + "," 
				+ VfkUtil.formatValue(parId) + ","
				+ VfkUtil.formatValue(budId) + ","
				+ VfkUtil.formatValue(identBud) + ","
				+ VfkUtil.formatValue(soucasti)
				+ VfkUtil.getTerminator();
	}

}
