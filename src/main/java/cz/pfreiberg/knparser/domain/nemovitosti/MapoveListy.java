package cz.pfreiberg.knparser.domain.nemovitosti;

import org.joda.time.DateTime;

/**
 * Třída reprezentující "Číselník mapových listů dle exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class MapoveListy {

	private long id;
	private String oznaceniMapovehoListu;
	private DateTime platnostOd;
	private DateTime platnostDo;
	private String mapa;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOznaceniMapovehoListu() {
		return oznaceniMapovehoListu;
	}

	public void setOznaceniMapovehoListu(String oznaceniMapovehoListu) {
		this.oznaceniMapovehoListu = oznaceniMapovehoListu;
	}

	public DateTime getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(DateTime platnostOd) {
		this.platnostOd = platnostOd;
	}

	public DateTime getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(DateTime platnostDo) {
		this.platnostDo = platnostDo;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

}
