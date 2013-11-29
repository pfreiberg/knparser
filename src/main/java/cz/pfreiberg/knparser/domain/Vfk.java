package cz.pfreiberg.knparser.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Budovy;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiObci;
import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.domain.nemovitosti.Kraje;
import cz.pfreiberg.knparser.domain.nemovitosti.MapoveListy;
import cz.pfreiberg.knparser.domain.nemovitosti.NoveKraje;
import cz.pfreiberg.knparser.domain.nemovitosti.Obce;
import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;

public class Vfk {

	private String verze;
	private Date vytvoreno;
	private String puvod;
	private String codepage;
	private List<String> skupina;
	private String jmeno;
	private List<Date> platnost;
	private int zmeny;
	private int navrhy;
	private int polyg;

	private List<Budovy> budovy;
	private List<CastiBudov> castiBudov;
	private List<CastiObci> castiObci;
	private List<DPozemku> dPozemku;
	private List<KatastrUzemi> katastrUzemi;
	private List<Kraje> kraje;
	private List<MapoveListy> mapoveListy;
	private List<NoveKraje> noveKraje;
	private List<Obce> obce;
	private List<Okresy> okresy;
	private List<Parcely> parcely;
	private List<RZpochr> rZpochr;
	private List<TBudov> tBudov;
	private List<ZdrojeParcelZe> zdrojeParcelZe;
	private List<ZpOchranyNem> zpOchranyNem;
	private List<ZpUrceniVymery> zpUrceniVymery;
	private List<ZpVyuzitiBud> zpVyuzitiBud;
	private List<ZpVyuzitiPoz> zpVyuzitiPoz;

	public Vfk() {
		skupina = new ArrayList<String>();
		platnost = new ArrayList<Date>();

		budovy = new ArrayList<Budovy>();
		castiBudov = new ArrayList<CastiBudov>();
		castiObci = new ArrayList<CastiObci>();
		dPozemku = new ArrayList<DPozemku>();
		katastrUzemi = new ArrayList<KatastrUzemi>();
		kraje = new ArrayList<Kraje>();
		mapoveListy = new ArrayList<MapoveListy>();
		noveKraje = new ArrayList<NoveKraje>();
		obce = new ArrayList<Obce>();
		okresy = new ArrayList<Okresy>();
		parcely = new ArrayList<Parcely>();
		rZpochr = new ArrayList<RZpochr>();
		tBudov = new ArrayList<TBudov>();
		zdrojeParcelZe = new ArrayList<ZdrojeParcelZe>();
		zpOchranyNem = new ArrayList<ZpOchranyNem>();
		zpUrceniVymery = new ArrayList<ZpUrceniVymery>();
		zpVyuzitiBud = new ArrayList<ZpVyuzitiBud>();
		zpVyuzitiPoz = new ArrayList<ZpVyuzitiPoz>();
	}

	public String getVerze() {
		return verze;
	}

	public void setVerze(String verze) {
		this.verze = verze;
	}

	public Date getVytvoreno() {
		return vytvoreno;
	}

	public void setVytvoreno(Date vytvoreno) {
		this.vytvoreno = vytvoreno;
	}

	public String getPuvod() {
		return puvod;
	}

	public void setPuvod(String puvod) {
		this.puvod = puvod;
	}

	public String getCodepage() {
		return codepage;
	}

	public void setCodepage(String codepage) {
		this.codepage = codepage;
	}

	public List<String> getSkupina() {
		return skupina;
	}

	public void setSkupina(List<String> skupina) {
		this.skupina = skupina;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public List<Date> getPlatnost() {
		return platnost;
	}

	public void setPlatnost(List<Date> platnost) {
		this.platnost = platnost;
	}

	public int getZmeny() {
		return zmeny;
	}

	public void setZmeny(int zmeny) {
		this.zmeny = zmeny;
	}

	public int getNavrhy() {
		return navrhy;
	}

	public void setNavrhy(int navrhy) {
		this.navrhy = navrhy;
	}

	public int getPolyg() {
		return polyg;
	}

	public void setPolyg(int polyg) {
		this.polyg = polyg;
	}

	public List<Parcely> getParcely() {
		return parcely;
	}

	public void setParcely(List<Parcely> parcely) {
		this.parcely = parcely;
	}

	public List<Budovy> getBudovy() {
		return budovy;
	}

	public void setBudovy(List<Budovy> budovy) {
		this.budovy = budovy;
	}

	public List<CastiBudov> getCastiBudov() {
		return castiBudov;
	}

	public void setCastiBudov(List<CastiBudov> castiBudov) {
		this.castiBudov = castiBudov;
	}

	public List<CastiObci> getCastiObci() {
		return castiObci;
	}

	public void setCastiObci(List<CastiObci> castiObci) {
		this.castiObci = castiObci;
	}

	public List<DPozemku> getDPozemku() {
		return dPozemku;
	}

	public void setDPozemku(List<DPozemku> dPozemku) {
		this.dPozemku = dPozemku;
	}

	public List<KatastrUzemi> getKatastrUzemi() {
		return katastrUzemi;
	}

	public void setKatastrUzemi(List<KatastrUzemi> katastrUzemi) {
		this.katastrUzemi = katastrUzemi;
	}

	public List<Kraje> getKraje() {
		return kraje;
	}

	public void setKraje(List<Kraje> kraje) {
		this.kraje = kraje;
	}

	public List<MapoveListy> getMapoveListy() {
		return mapoveListy;
	}

	public void setMapoveListy(List<MapoveListy> mapoveListy) {
		this.mapoveListy = mapoveListy;
	}

	public List<NoveKraje> getNoveKraje() {
		return noveKraje;
	}

	public void setNoveKraje(List<NoveKraje> noveKraje) {
		this.noveKraje = noveKraje;
	}

	public List<Obce> getObce() {
		return obce;
	}

	public void setObce(List<Obce> obce) {
		this.obce = obce;
	}

	public List<Okresy> getOkresy() {
		return okresy;
	}

	public void setOkresy(List<Okresy> okresy) {
		this.okresy = okresy;
	}

	public List<RZpochr> getRZpochr() {
		return rZpochr;
	}

	public void setRZpochr(List<RZpochr> rZpochr) {
		this.rZpochr = rZpochr;
	}

	public List<TBudov> getTBudov() {
		return tBudov;
	}

	public void setTBudov(List<TBudov> tBudov) {
		this.tBudov = tBudov;
	}

	public List<ZdrojeParcelZe> getZdrojeParcelZe() {
		return zdrojeParcelZe;
	}

	public void setZdrojeParcelZe(List<ZdrojeParcelZe> zdrojeParcelZe) {
		this.zdrojeParcelZe = zdrojeParcelZe;
	}

	public List<ZpOchranyNem> getZpOchranyNem() {
		return zpOchranyNem;
	}

	public void setZpOchranyNem(List<ZpOchranyNem> zpOchranyNem) {
		this.zpOchranyNem = zpOchranyNem;
	}

	public List<ZpUrceniVymery> getZpUrceniVymery() {
		return zpUrceniVymery;
	}

	public void setZpUrceniVymery(List<ZpUrceniVymery> zpUrceniVymery) {
		this.zpUrceniVymery = zpUrceniVymery;
	}

	public List<ZpVyuzitiBud> getZpVyuzitiBud() {
		return zpVyuzitiBud;
	}

	public void setZpVyuzitiBud(List<ZpVyuzitiBud> zpVyuzitiBud) {
		this.zpVyuzitiBud = zpVyuzitiBud;
	}

	public List<ZpVyuzitiPoz> getZpVyuzitiPoz() {
		return zpVyuzitiPoz;
	}

	public void setZpVyuzitiPoz(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		this.zpVyuzitiPoz = zpVyuzitiPoz;
	}

}
