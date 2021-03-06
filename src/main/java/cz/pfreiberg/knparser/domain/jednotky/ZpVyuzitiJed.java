package cz.pfreiberg.knparser.domain.jednotky;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * Třída reprezentující "Způsob využití jednotek".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class ZpVyuzitiJed {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private Date platnostDo;
	private String zkratka;
	private Integer doplKod;

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

	public Date getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(Date platnostOd) {
		this.platnostOd = platnostOd;
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

	public Integer getDoplKod() {
		return doplKod;
	}

	public void setDoplKod(Integer doplKod) {
		this.doplKod = doplKod;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + "," 
				+ VfkUtil.formatValue(nazev) + "," 
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(platnostDo) + ","
				+ VfkUtil.formatValue(zkratka) + ","
				+ VfkUtil.formatValue(doplKod) 
				+ VfkUtil.getTerminator();
	}

}
