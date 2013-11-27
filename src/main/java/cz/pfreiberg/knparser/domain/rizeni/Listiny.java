package cz.pfreiberg.knparser.domain.rizeni;

import java.util.Date;

/**
 * 
 * Třída reprezentující "Listiny".
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Listiny {

	private Long id;
	private Integer typlistKod;
	private String popis;
	private String obsah;
	private Integer stran;
	private Date datumVyhotoveni;
	private String zhotovitel;
	private String poradoveCisloZhotovitele;
	private Date rokZhotovitele;
	private String doplneniZhotovitele;
	private String zkratka;
	private Long rizeniId;
	private String zmenaPravVztahu;
	private Date datumPravMoci;
	private Date datumVykonatelnosti;
	private Date datumHistOd;
	private Date datumHistDo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTyplistKod() {
		return typlistKod;
	}

	public void setTyplistKod(Integer typlistKod) {
		this.typlistKod = typlistKod;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public String getObsah() {
		return obsah;
	}

	public void setObsah(String obsah) {
		this.obsah = obsah;
	}

	public Integer getStran() {
		return stran;
	}

	public void setStran(Integer stran) {
		this.stran = stran;
	}

	public Date getDatumVyhotoveni() {
		return datumVyhotoveni;
	}

	public void setDatumVyhotoveni(Date datumVyhotoveni) {
		this.datumVyhotoveni = datumVyhotoveni;
	}

	public String getZhotovitel() {
		return zhotovitel;
	}

	public void setZhotovitel(String zhotovitel) {
		this.zhotovitel = zhotovitel;
	}

	public String getPoradoveCisloZhotovitele() {
		return poradoveCisloZhotovitele;
	}

	public void setPoradoveCisloZhotovitele(String poradoveCisloZhotovitele) {
		this.poradoveCisloZhotovitele = poradoveCisloZhotovitele;
	}

	public Date getRokZhotovitele() {
		return rokZhotovitele;
	}

	public void setRokZhotovitele(Date rokZhotovitele) {
		this.rokZhotovitele = rokZhotovitele;
	}

	public String getDoplneniZhotovitele() {
		return doplneniZhotovitele;
	}

	public void setDoplneniZhotovitele(String doplneniZhotovitele) {
		this.doplneniZhotovitele = doplneniZhotovitele;
	}

	public String getZkratka() {
		return zkratka;
	}

	public void setZkratka(String zkratka) {
		this.zkratka = zkratka;
	}

	public Long getRizeniId() {
		return rizeniId;
	}

	public void setRizeniId(Long rizeniId) {
		this.rizeniId = rizeniId;
	}

	public String getZmenaPravVztahu() {
		return zmenaPravVztahu;
	}

	public void setZmenaPravVztahu(String zmenaPravVztahu) {
		this.zmenaPravVztahu = zmenaPravVztahu;
	}

	public Date getDatumPravMoci() {
		return datumPravMoci;
	}

	public void setDatumPravMoci(Date datumPravMoci) {
		this.datumPravMoci = datumPravMoci;
	}

	public Date getDatumVykonatelnosti() {
		return datumVykonatelnosti;
	}

	public void setDatumVykonatelnosti(Date datumVykonatelnosti) {
		this.datumVykonatelnosti = datumVykonatelnosti;
	}

	public Date getDatumHistOd() {
		return datumHistOd;
	}

	public void setDatumHistOd(Date datumHistOd) {
		this.datumHistOd = datumHistOd;
	}

	public Date getDatumHistDo() {
		return datumHistDo;
	}

	public void setDatumHistDo(Date datumHistDo) {
		this.datumHistDo = datumHistDo;
	}

}
