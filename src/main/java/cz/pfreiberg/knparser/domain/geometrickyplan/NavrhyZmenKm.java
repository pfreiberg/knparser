package cz.pfreiberg.knparser.domain.geometrickyplan;

/**
 * 
 * Třída reprezentující "Hlavičky geometrických plánů a ostatních změn KM.".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class NavrhyZmenKm {

	private Long id;
	private Integer stavNz;
	private String nzType;
	private String porizeniDatNz;
	private Long rizeniId;
	private String cisloPlanku;
	private String vyhotovil;
	private String oznaceniMapovehoListu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStavNz() {
		return stavNz;
	}

	public void setStavNz(Integer stavNz) {
		this.stavNz = stavNz;
	}

	public String getNzType() {
		return nzType;
	}

	public void setNzType(String nzType) {
		this.nzType = nzType;
	}

	public String getPorizeniDatNz() {
		return porizeniDatNz;
	}

	public void setPorizeniDatNz(String porizeniDatNz) {
		this.porizeniDatNz = porizeniDatNz;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	public String getCisloPlanku() {
		return cisloPlanku;
	}

	public void setCisloPlanku(String cisloPlanku) {
		this.cisloPlanku = cisloPlanku;
	}

	public String getVyhotovil() {
		return vyhotovil;
	}

	public void setVyhotovil(String vyhotovil) {
		this.vyhotovil = vyhotovil;
	}

	public String getOznaceniMapovehoListu() {
		return oznaceniMapovehoListu;
	}

	public void setOznaceniMapovehoListu(String oznaceniMapovehoListu) {
		this.oznaceniMapovehoListu = oznaceniMapovehoListu;
	}

}
