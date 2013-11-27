package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Číselník typů listin".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TListin {

	private Integer kod;
	private String nazev;
	private Date platnostOd;
	private String popis;
	private String platnostDo;
	private Integer druhList;

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

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public String getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(String platnostDo) {
		this.platnostDo = platnostDo;
	}

	public Integer getDruhList() {
		return druhList;
	}

	public void setDruhList(Integer druhList) {
		this.druhList = druhList;
	}

}
