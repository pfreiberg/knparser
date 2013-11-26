package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Budovy".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Budovy {

	private long id;
	private int stavDat;
	private DateTime datumVzniku;
	private DateTime datumZaniku;
	private int priznakKontextu;
	private long rizeniIdVzniku;
	private long rizeniIdZaniku;
	private int typBudKod;
	private int caoObceKod;
	private int cisloDomovni;
	private int cenaNemovitosti;
	private int zpvybuKod;
	private int telId;

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

	public int getTypBudKod() {
		return typBudKod;
	}

	public void setTypBudKod(int typBudKod) {
		this.typBudKod = typBudKod;
	}

	public int getCaoObceKod() {
		return caoObceKod;
	}

	public void setCaoObceKod(int caoObceKod) {
		this.caoObceKod = caoObceKod;
	}

	public int getCisloDomovni() {
		return cisloDomovni;
	}

	public void setCisloDomovni(int cisloDomovni) {
		this.cisloDomovni = cisloDomovni;
	}

	public int getCenaNemovitosti() {
		return cenaNemovitosti;
	}

	public void setCenaNemovitosti(int cenaNemovitosti) {
		this.cenaNemovitosti = cenaNemovitosti;
	}

	public int getZpvybuKod() {
		return zpvybuKod;
	}

	public void setZpvybuKod(int zpvybuKod) {
		this.zpvybuKod = zpvybuKod;
	}

	public int getTelId() {
		return telId;
	}

	public void setTelId(int telId) {
		this.telId = telId;
	}

}
