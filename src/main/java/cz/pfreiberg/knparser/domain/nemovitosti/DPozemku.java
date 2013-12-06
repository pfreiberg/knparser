package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Číselník druhů pozemku".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class DPozemku {

	private Integer kod;
	private String nazev;
	private String zemedelskaKultura;
	private Date platnostOd;
	private Integer typppdKod;
	private Date platnostDo;
	private String zkratka;
	private String stavebniParcela;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
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

	public Date getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(Date platnostOd) {
		this.platnostOd = platnostOd;
	}

	public Integer getTypppdKod() {
		return typppdKod;
	}

	public void setTypppdKod(Integer typppdKod) {
		this.typppdKod = typppdKod;
	}

	public Date getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(Date platnostDo) {
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

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(zemedelskaKultura) + ","
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(typppdKod) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(zkratka) + ","
				+ VfkUtil.formatValue(stavebniParcela)
				+ VfkUtil.getTerminator();
	}

}
