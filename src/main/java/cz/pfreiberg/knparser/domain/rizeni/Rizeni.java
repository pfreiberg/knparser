package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Řízení (vklad, záznam)".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Rizeni {

	private Long id;
	private String typrizKod;
	private Integer poradoveCislo;
	private Date rok;
	private String stav;
	private Integer funkceKod;
	private Integer typopeKod;
	private Integer funkceKodVyznamna;
	private Integer typopeKodVyznamna;
	private String uzisysUsername;
	private String uzirolNazev;
	private String osvobozeno;
	private Integer hodnotaKolku;
	private Date datum;
	private Date datum2;
	private String popis;
	private Date datumUzavreni;
	private Integer praresKod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTyprizKod() {
		return typrizKod;
	}

	public void setTyprizKod(String typrizKod) {
		this.typrizKod = typrizKod;
	}

	public Integer getPoradoveCislo() {
		return poradoveCislo;
	}

	public void setPoradoveCislo(Integer poradoveCislo) {
		this.poradoveCislo = poradoveCislo;
	}

	public Date getRok() {
		return rok;
	}

	public void setRok(Date rok) {
		this.rok = rok;
	}

	public String getStav() {
		return stav;
	}

	public void setStav(String stav) {
		this.stav = stav;
	}

	public Integer getFunkceKod() {
		return funkceKod;
	}

	public void setFunkceKod(Integer funkceKod) {
		this.funkceKod = funkceKod;
	}

	public Integer getTypopeKod() {
		return typopeKod;
	}

	public void setTypopeKod(Integer typopeKod) {
		this.typopeKod = typopeKod;
	}

	public Integer getFunkceKodVyznamna() {
		return funkceKodVyznamna;
	}

	public void setFunkceKodVyznamna(Integer funkceKodVyznamna) {
		this.funkceKodVyznamna = funkceKodVyznamna;
	}

	public Integer getTypopeKodVyznamna() {
		return typopeKodVyznamna;
	}

	public void setTypopeKodVyznamna(Integer typopeKodVyznamna) {
		this.typopeKodVyznamna = typopeKodVyznamna;
	}

	public String getUzisysUsername() {
		return uzisysUsername;
	}

	public void setUzisysUsername(String uzisysUsername) {
		this.uzisysUsername = uzisysUsername;
	}

	public String getUzirolNazev() {
		return uzirolNazev;
	}

	public void setUzirolNazev(String uzirolNazev) {
		this.uzirolNazev = uzirolNazev;
	}

	public String getOsvobozeno() {
		return osvobozeno;
	}

	public void setOsvobozeno(String osvobozeno) {
		this.osvobozeno = osvobozeno;
	}

	public Integer getHodnotaKolku() {
		return hodnotaKolku;
	}

	public void setHodnotaKolku(Integer hodnotaKolku) {
		this.hodnotaKolku = hodnotaKolku;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getDatum2() {
		return datum2;
	}

	public void setDatum2(Date datum2) {
		this.datum2 = datum2;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public Date getDatumUzavreni() {
		return datumUzavreni;
	}

	public void setDatumUzavreni(Date datumUzavreni) {
		this.datumUzavreni = datumUzavreni;
	}

	public Integer getPraresKod() {
		return praresKod;
	}

	public void setPraresKod(Integer praresKod) {
		this.praresKod = praresKod;
	}

}
