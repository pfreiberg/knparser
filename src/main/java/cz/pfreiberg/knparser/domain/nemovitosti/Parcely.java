package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Parcely".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Parcely {

	private long id;
	private int stavDat;
	private DateTime datumVzniku;
	private DateTime datumZaniku;
	private int priznakKontextu;
	private long rizeniIdVzniku;
	private long rizeniIdZaniku;
	private long pknId;
	private String partType;
	private int katuzeKod;
	private int katuzeKodPuv;
	private int druhCislovaniPar;
	private int kmenoveCisloPar;
	private int zdpazeKod;
	private int poddeleniCislaPar;
	private int dilParcely;
	private long maplisKod;
	private int zpurvyKod;
	private int drupozKod;
	private int zpvypaKod;
	private int typParcely;
	private int vymeraParcely;
	private float cenaNemovitosti;
	private String definiciniBodPar;
	private long telId;
	private long parId;
	private long budId;
	private String identBud;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStavDat() {
		return stavDat;
	}

	public void setStavDat(int stavDat) {
		this.stavDat = stavDat;
	}

	public DateTime getDatumVzniku() {
		return datumVzniku;
	}

	public void setDatumVzniku(DateTime datumVzniku) {
		this.datumVzniku = datumVzniku;
	}

	public DateTime getDatumZaniku() {
		return datumZaniku;
	}

	public void setDatumZaniku(DateTime datumZaniku) {
		this.datumZaniku = datumZaniku;
	}

	public int getPriznakKontextu() {
		return priznakKontextu;
	}

	public void setPriznakKontextu(int priznakKontextu) {
		this.priznakKontextu = priznakKontextu;
	}

	public long getRizeniIdVzniku() {
		return rizeniIdVzniku;
	}

	public void setRizeniIdVzniku(long rizeniIdVzniku) {
		this.rizeniIdVzniku = rizeniIdVzniku;
	}

	public long getRizeniIdZaniku() {
		return rizeniIdZaniku;
	}

	public void setRizeniIdZaniku(long rizeniIdZaniku) {
		this.rizeniIdZaniku = rizeniIdZaniku;
	}

	public long getPknId() {
		return pknId;
	}

	public void setPknId(long pknId) {
		this.pknId = pknId;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public int getKatuzeKod() {
		return katuzeKod;
	}

	public void setKatuzeKod(int katuzeKod) {
		this.katuzeKod = katuzeKod;
	}

	public int getKatuzeKodPuv() {
		return katuzeKodPuv;
	}

	public void setKatuzeKodPuv(int katuzeKodPuv) {
		this.katuzeKodPuv = katuzeKodPuv;
	}

	public int getDruhCislovaniPar() {
		return druhCislovaniPar;
	}

	public void setDruhCislovaniPar(int druhCislovaniPar) {
		this.druhCislovaniPar = druhCislovaniPar;
	}

	public int getKmenoveCisloPar() {
		return kmenoveCisloPar;
	}

	public void setKmenoveCisloPar(int kmenoveCisloPar) {
		this.kmenoveCisloPar = kmenoveCisloPar;
	}

	public int getZdpazeKod() {
		return zdpazeKod;
	}

	public void setZdpazeKod(int zdpazeKod) {
		this.zdpazeKod = zdpazeKod;
	}

	public int getPoddeleniCislaPar() {
		return poddeleniCislaPar;
	}

	public void setPoddeleniCislaPar(int poddeleniCislaPar) {
		this.poddeleniCislaPar = poddeleniCislaPar;
	}

	public int getDilParcely() {
		return dilParcely;
	}

	public void setDilParcely(int dilParcely) {
		this.dilParcely = dilParcely;
	}

	public long getMaplisKod() {
		return maplisKod;
	}

	public void setMaplisKod(long maplisKod) {
		this.maplisKod = maplisKod;
	}

	public int getZpurvyKod() {
		return zpurvyKod;
	}

	public void setZpurvyKod(int zpurvyKod) {
		this.zpurvyKod = zpurvyKod;
	}

	public int getDrupozKod() {
		return drupozKod;
	}

	public void setDrupozKod(int drupozKod) {
		this.drupozKod = drupozKod;
	}

	public int getZpvypaKod() {
		return zpvypaKod;
	}

	public void setZpvypaKod(int zpvypaKod) {
		this.zpvypaKod = zpvypaKod;
	}

	public int getTypParcely() {
		return typParcely;
	}

	public void setTypParcely(int typParcely) {
		this.typParcely = typParcely;
	}

	public int getVymeraParcely() {
		return vymeraParcely;
	}

	public void setVymeraParcely(int vymeraParcely) {
		this.vymeraParcely = vymeraParcely;
	}

	public float getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(float cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	public String getDefiniciniBodPar() {
		return definiciniBodPar;
	}

	public void setDefiniciniBodPar(String definiciniBodPar) {
		this.definiciniBodPar = definiciniBodPar;
	}

	public long getTelId() {
		return telId;
	}

	public void setTelId(long telId) {
		this.telId = telId;
	}

	public long getParId() {
		return parId;
	}

	public void setParId(long parId) {
		this.parId = parId;
	}

	public long getBudId() {
		return budId;
	}

	public void setBudId(long budId) {
		this.budId = budId;
	}

	public String getIdentBud() {
		return identBud;
	}

	public void setIdentBud(String identBud) {
		this.identBud = identBud;
	}

}
