package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Výběr způsobu ochrany k nemovitostem na základě exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class RZpochr {

	private long id;
	private int stavDat;
	private DateTime datumVzniku;
	private DateTime datumZaniku;
	private int priznakKontextu;
	private long rizeniIdVzniku;
	private long rizeniIdZaniku;
	private int zpochrKod;
	private long parId;
	private long budId;
	private long jedId;

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

	public int getZpochrKod() {
		return zpochrKod;
	}

	public void setZpochrKod(int zpochrKod) {
		this.zpochrKod = zpochrKod;
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

	public long getJedId() {
		return jedId;
	}

	public void setJedId(long jedId) {
		this.jedId = jedId;
	}

}
