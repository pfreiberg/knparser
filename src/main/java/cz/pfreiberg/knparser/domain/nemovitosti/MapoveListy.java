package cz.pfreiberg.knparser.domain.nemovitosti;

import java.util.Date;

/**
 * Třída reprezentující "Číselník mapových listů dle exportovaných parcel".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class MapoveListy {

	private Long id;
	private String oznaceniMapovehoListu;
	private Date platnostOd;
	private Date platnostDo;
	private String mapa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznaceniMapovehoListu() {
		return oznaceniMapovehoListu;
	}

	public void setOznaceniMapovehoListu(String oznaceniMapovehoListu) {
		this.oznaceniMapovehoListu = oznaceniMapovehoListu;
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

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	@Override
	public String toString() {
		return "MapoveListy [id=" + id + ", oznaceniMapovehoListu="
				+ oznaceniMapovehoListu + ", platnostOd=" + platnostOd
				+ ", platnostDo=" + platnostDo + ", mapa=" + mapa + "]";
	}

}
