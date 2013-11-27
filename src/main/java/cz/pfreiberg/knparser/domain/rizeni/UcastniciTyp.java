package cz.pfreiberg.knparser.domain.rizeni;

/**
 * 
 * Třída reprezentující "Vazba Účastnící – Typy účastníků řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class UcastniciTyp {

	private Long ucastId;
	private String typucaKod;

	public Long getUcastId() {
		return ucastId;
	}

	public void setUcastId(Long ucastId) {
		this.ucastId = ucastId;
	}

	public String getTypucaKod() {
		return typucaKod;
	}

	public void setTypucaKod(String typucaKod) {
		this.typucaKod = typucaKod;
	}

}
