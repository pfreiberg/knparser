package cz.pfreiberg.knparser.domain.jinepravnivztahy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Číselník typů právních vztahů".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TPravnichVzt {
	private String kod;
	private Integer tprKod;
	private String nazev;
	private String vlastnictvi;
	private String proOs;
	private String proNemovitost;
	private String kNemovitosti;
	private Date platnostOd;
	private String sekce;
	private Date platnostDo;
	private Integer vlvztah;
	private String kOs;
	private String podilVeritele;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Integer getTprKod() {
		return tprKod;
	}

	public void setTprKod(Integer tprKod) {
		this.tprKod = tprKod;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getVlastnictvi() {
		return vlastnictvi;
	}

	public void setVlastnictvi(String vlastnictvi) {
		this.vlastnictvi = vlastnictvi;
	}

	public String getProOs() {
		return proOs;
	}

	public void setProOs(String proOs) {
		this.proOs = proOs;
	}

	public String getProNemovitost() {
		return proNemovitost;
	}

	public void setProNemovitost(String proNemovitost) {
		this.proNemovitost = proNemovitost;
	}

	public String getkNemovitosti() {
		return kNemovitosti;
	}

	public void setkNemovitosti(String kNemovitosti) {
		this.kNemovitosti = kNemovitosti;
	}

	public Date getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(Date platnostOd) {
		this.platnostOd = platnostOd;
	}

	public String getSekce() {
		return sekce;
	}

	public void setSekce(String sekce) {
		this.sekce = sekce;
	}

	public Date getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(Date platnostDo) {
		this.platnostDo = platnostDo;
	}

	public Integer getVlvztah() {
		return vlvztah;
	}

	public void setVlvztah(Integer vlvztah) {
		this.vlvztah = vlvztah;
	}

	public String getkOs() {
		return kOs;
	}

	public void setkOs(String kOs) {
		this.kOs = kOs;
	}

	public String getPodilVeritele() {
		return podilVeritele;
	}

	public void setPodilVeritele(String podilVeritele) {
		this.podilVeritele = podilVeritele;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + ","
				+ VfkUtil.formatValue(tprKod) + ","
				+ VfkUtil.formatValue(nazev) + ","
				+ VfkUtil.formatValue(vlastnictvi) + ","
				+ VfkUtil.formatValue(proOs) + ","
				+ VfkUtil.formatValue(proNemovitost) + ","
				+ VfkUtil.formatValue(kNemovitosti) + ","
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(sekce) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(vlvztah) + "," 
				+ VfkUtil.formatValue(kOs) + "," 
				+ VfkUtil.formatValue(podilVeritele)
				+ VfkUtil.getTerminator();
	}

}
