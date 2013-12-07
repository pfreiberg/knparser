package cz.pfreiberg.knparser.domain.prvkykatastralnimapy;

import java.util.Date;

import cz.pfreiberg.knparser.util.VfkUtil;

/**
 * 
 * Třída reprezentující "Číselník typů prvků prostorových dat".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class TPrvkuPDat {

	private Integer kod;
	private String polohopis;
	private String editovatelny;
	private Date platnostOd;
	private String vyznam;
	private String krivka;
	private Integer typPrvku;
	private Date platnostDo;

	public Integer getKod() {
		return kod;
	}

	public void setKod(Integer kod) {
		this.kod = kod;
	}

	public String getPolohopis() {
		return polohopis;
	}

	public void setPolohopis(String polohopis) {
		this.polohopis = polohopis;
	}

	public String getEditovatelny() {
		return editovatelny;
	}

	public void setEditovatelny(String editovatelny) {
		this.editovatelny = editovatelny;
	}

	public Date getPlatnostOd() {
		return platnostOd;
	}

	public void setPlatnostOd(Date platnostOd) {
		this.platnostOd = platnostOd;
	}

	public String getVyznam() {
		return vyznam;
	}

	public void setVyznam(String vyznam) {
		this.vyznam = vyznam;
	}

	public String getKrivka() {
		return krivka;
	}

	public void setKrivka(String krivka) {
		this.krivka = krivka;
	}

	public Integer getTypPrvku() {
		return typPrvku;
	}

	public void setTypPrvku(Integer typPrvku) {
		this.typPrvku = typPrvku;
	}

	public Date getPlatnostDo() {
		return platnostDo;
	}

	public void setPlatnostDo(Date platnostDo) {
		this.platnostDo = platnostDo;
	}

	@Override
	public String toString() {
		return "" + VfkUtil.formatValue(kod) + ","
				+ VfkUtil.formatValue(polohopis) + ","
				+ VfkUtil.formatValue(editovatelny) + "," 
				+ VfkUtil.formatValue(platnostOd) + ","
				+ VfkUtil.formatValue(vyznam) + ","
				+ VfkUtil.formatValue(krivka) + ","
				+ VfkUtil.formatValue(typPrvku) + ","
				+ VfkUtil.formatValue(platnostDo)
				+ VfkUtil.getTerminator();
	}

}
