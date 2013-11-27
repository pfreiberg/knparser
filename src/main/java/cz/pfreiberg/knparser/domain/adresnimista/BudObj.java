package cz.pfreiberg.knparser.domain.adresnimista;

/**
 * 
 * Třída reprezentující "Odkazy objektů na adresy".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class BudObj {

	private Integer cisdomHod;
	private Long idKn;
	private Integer cbKn;
	private Integer idUa;

	public Integer getCisdomHod() {
		return cisdomHod;
	}

	public void setCisdomHod(Integer cisdomHod) {
		this.cisdomHod = cisdomHod;
	}

	public Long getIdKn() {
		return idKn;
	}

	public void setIdKn(Long idKn) {
		this.idKn = idKn;
	}

	public Integer getCbKn() {
		return cbKn;
	}

	public void setCbKn(Integer cbKn) {
		this.cbKn = cbKn;
	}

	public Integer getIdUa() {
		return idUa;
	}

	public void setIdUa(Integer idUa) {
		this.idUa = idUa;
	}

}
