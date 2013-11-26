package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Číselník druhů pozemku".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class DPozemku {

	private int kod;
	private String nazev;
	private String zemedelskaKultura;
	private DateTime platnostOd;
	private int typppdKod;
	private DateTime platnostDo;
	private String zkratka;
	private String stavebniParcela;

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getZemedelskaKultura() {
		return zemedelskaKultura;
	}

	public void setZemedelskaKultura(String zemedelskaKultura) {
		this.zemedelskaKultura = zemedelskaKultura;
	}

	public DateTime getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(DateTime platnostOd) {
		this.platnostOd = platnostOd;
	}

	public int getTypppdKod() {
		return typppdKod;
	}

	public void setTypppdKod(int typppdKod) {
		this.typppdKod = typppdKod;
	}

	public DateTime getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(DateTime platnostDo) {
		this.platnostDo = platnostDo;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

	public String getStavebniParcela() {
		return stavebniParcela;
	}

	public void setStavebniParcela(String stavebniParcela) {
		this.stavebniParcela = stavebniParcela;
	}

}
