package cz.pfreiberg.knparser.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class Vfk {

	private String verze;
	private Date vytvoreno;
	private String puvod;
	private String codepage;
	private List<String> skupina;
	private String jmeno;
	private List<Date> platnost;
	private int zmeny;
	private int navrhy;
	private int polyg;

	private List<Parcely> parcely;
	
	public Vfk()
	{
		skupina = new ArrayList<String>();
		platnost = new ArrayList<Date>();
		parcely = new ArrayList<Parcely>();
	}

	public String getVerze() {
		return verze;
	}

	public void setVerze(String verze) {
		this.verze = verze;
	}

	public Date getVytvoreno() {
		return vytvoreno;
	}

	public void setVytvoreno(Date vytvoreno) {
		this.vytvoreno = vytvoreno;
	}

	public String getPuvod() {
		return puvod;
	}

	public void setPuvod(String puvod) {
		this.puvod = puvod;
	}

	public String getCodepage() {
		return codepage;
	}

	public void setCodepage(String codepage) {
		this.codepage = codepage;
	}

	public List<String> getSkupina() {
		return skupina;
	}

	public void setSkupina(List<String> skupina) {
		this.skupina = skupina;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public List<Date> getPlatnost() {
		return platnost;
	}

	public void setPlatnost(List<Date> platnost) {
		this.platnost = platnost;
	}

	public int getZmeny() {
		return zmeny;
	}

	public void setZmeny(int zmeny) {
		this.zmeny = zmeny;
	}

	public int getNavrhy() {
		return navrhy;
	}

	public void setNavrhy(int navrhy) {
		this.navrhy = navrhy;
	}

	public int getPolyg() {
		return polyg;
	}

	public void setPolyg(int polyg) {
		this.polyg = polyg;
	}

	public List<Parcely> getParcely() {
		return parcely;
	}

	public void setParcely(List<Parcely> parcely) {
		this.parcely = parcely;
	}

}
