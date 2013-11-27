package cz.pfreiberg.knparser.domain.rizeni;

/**
 * 
 * Třída reprezentující "Předměty řízení".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class PredmetyRizeni {

	private Long rizeniId;
	private Integer typpreKod;

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	public Integer getTyppreKod() {
		return typpreKod;
	}

	public void setTyppreKod(Integer typpreKod) {
		this.typpreKod = typpreKod;
	}

}
