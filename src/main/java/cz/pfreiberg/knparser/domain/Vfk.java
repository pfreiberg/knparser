package cz.pfreiberg.knparser.domain;

import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.Adresa;
import cz.pfreiberg.knparser.domain.adresnimista.BudObj;
import cz.pfreiberg.knparser.domain.bonitnidilyparcel.BonitDilyParc;
import cz.pfreiberg.knparser.domain.bpej.HraniceBpej;
import cz.pfreiberg.knparser.domain.bpej.OznaceniBpej;
import cz.pfreiberg.knparser.domain.definicnibody.ObrazyDefBodu;
import cz.pfreiberg.knparser.domain.geometrickyplan.NavrhyZmenKm;
import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;
import cz.pfreiberg.knparser.domain.geometrickyplan.Zpmz;
import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.domain.jednotky.TJednotek;
import cz.pfreiberg.knparser.domain.jednotky.ZpVyuzitiJed;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.JinePravVztahy;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
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
import cz.pfreiberg.knparser.domain.nemovitosti.PravaStavby;
import cz.pfreiberg.knparser.domain.nemovitosti.RUcelNem;
import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
import cz.pfreiberg.knparser.domain.nemovitosti.Ucel;
import cz.pfreiberg.knparser.domain.nemovitosti.ZdrojeParcelZe;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpOchranyNem;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpUrceniVymery;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiBud;
import cz.pfreiberg.knparser.domain.nemovitosti.ZpVyuzitiPoz;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.DalsiPrvkyMapy;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.KodyCharQBodu;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBoduBp;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBudov;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyParcel;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradnicePolohy;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBPoloh;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniPoMapy;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TSouradSys;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ZobrazeniVb;
import cz.pfreiberg.knparser.domain.rezervovanacisla.DotHistParCisla;
import cz.pfreiberg.knparser.domain.rezervovanacisla.DotcenaParCisla;
import cz.pfreiberg.knparser.domain.rezervovanacisla.RezCislaPbpp;
import cz.pfreiberg.knparser.domain.rezervovanacisla.RezParcelniCisla;
import cz.pfreiberg.knparser.domain.rizeni.Adresy;
import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;
import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.domain.rizeni.ListinyDalsiUdaje;
import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;
import cz.pfreiberg.knparser.domain.rizeni.RList;
import cz.pfreiberg.knparser.domain.rizeni.Rizeni;
import cz.pfreiberg.knparser.domain.rizeni.RizeniKu;
import cz.pfreiberg.knparser.domain.rizeni.TListin;
import cz.pfreiberg.knparser.domain.rizeni.TPredmetuR;
import cz.pfreiberg.knparser.domain.rizeni.TypyRizeni;
import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;
import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;
import cz.pfreiberg.knparser.domain.rizeni.UcastniciTyp;
import cz.pfreiberg.knparser.domain.vlastnictvi.CharOs;
import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.domain.vlastnictvi.Telesa;
import cz.pfreiberg.knparser.domain.vlastnictvi.Vlastnictvi;

/**
 * Zapouzdřuje data, které parser získal ze souboru.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public class Vfk {

	// Hlavička
	private int zmeny;

	// Adresní místa
	private List<Adresa> adresa;
	private List<BudObj> budObj;

	// Bonitní díl parcely
	private List<BonitDilyParc> bonitDilyParc;

	// Bonitované půdně ekologické jednotky
	private List<HraniceBpej> hraniceBpej;
	private List<OznaceniBpej> oznaceniBpej;

	// Definiční body
	private List<ObrazyDefBodu> obrazyDefBodu;

	// Geometrický plán
	private List<NavrhyZmenKm> navrhyZmenKm;
	private List<NzZpmz> nzZpmz;
	private List<Zpmz> zpmz;

	// Jednotky
	private List<Jednotky> jednotky;
	private List<TJednotek> tJednotek;
	private List<ZpVyuzitiJed> zpVyuzitiJed;

	// Jiné právní vztahy
	private List<JinePravVztahy> jinePravVztahy;
	private List<TPravnichVzt> tPravnichVzt;
	private List<RJpv> rJpv;

	// Nemovitosti
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
	private List<PravaStavby> pravaStavby;
	private List<RUcelNem> rUcelNem;
	private List<Ucel> ucel;

	// Prvky katastrální mapy
	private List<DalsiPrvkyMapy> dalsiPrvkyMapy;
	private List<HraniceParcel> hraniceParcel;
	private List<KodyCharQBodu> kodyCharQBodu;
	private List<ObrazyBoduBp> obrazyBoduBp;
	private List<ObrazyBudov> obrazyBudov;
	private List<ObrazyParcel> obrazyParcel;
	private List<PrvkyOMapy> prvkyOMapy;
	private List<SouradniceObrazu> souradniceObrazu;
	private List<SouradnicePolohy> souradnicePolohy;
	private List<SpojeniBMapy> spojeniBMapy;
	private List<SpojeniBPoloh> spojeniBPoloh;
	private List<SpojeniPoMapy> spojeniPoMapy;
	private List<TPrvkuPDat> tPrvkuPdat;
	private List<TSouradSys> tSouradSys;
	private List<ZobrazeniVb> zobrazeniVb;

	// Rezervovaná čísla
	private List<DotcenaParCisla> dotcenaParCisla;
	private List<DotHistParCisla> dotHistParCisla;
	private List<RezCislaPbpp> rezCislaPbpp;
	private List<RezParcelniCisla> rezParcelniCisla;

	// Řízení
	private List<Adresy> adresy;
	private List<DalsiUdajeListiny> dalsiUdajeListiny;
	private List<Listiny> listiny;
	private List<ListinyDalsiUdaje> listinyDalsiUdaje;
	private List<ObeslaniMf> obeslaniMf;
	private List<ObjektyRizeni> objektyRizeni;
	private List<PredmetyRizeni> predmetyRizeni;
	private List<Rizeni> rizeni;
	private List<RizeniKu> rizeniKu;
	private List<RList> rList;
	private List<TListin> tListin;
	private List<TPredmetuR> tPredmetuR;
	private List<TypyRizeni> typyRizeni;
	private List<TypyUcastniku> typyUcastniku;
	private List<Ucastnici> ucastnici;
	private List<UcastniciTyp> ucastniciTyp;

	// Vlastnictví
	private List<CharOs> charOs;
	private List<OpravSubjekty> opravSubjekty;
	private List<Telesa> telesa;
	private List<Vlastnictvi> vlastnictvi;

	private boolean lastBatch;

	public Vfk() {
		// Adresní místa
		adresa = new ArrayList<Adresa>();
		budObj = new ArrayList<BudObj>();

		// Bonitní díl parcely
		bonitDilyParc = new ArrayList<BonitDilyParc>();

		// Bonitované půdně ekologické jednotky
		hraniceBpej = new ArrayList<HraniceBpej>();
		oznaceniBpej = new ArrayList<OznaceniBpej>();

		// Definiční body
		obrazyDefBodu = new ArrayList<ObrazyDefBodu>();

		// Geometrický plán
		navrhyZmenKm = new ArrayList<NavrhyZmenKm>();
		nzZpmz = new ArrayList<NzZpmz>();
		zpmz = new ArrayList<Zpmz>();

		// Jednotky
		jednotky = new ArrayList<Jednotky>();
		tJednotek = new ArrayList<TJednotek>();
		zpVyuzitiJed = new ArrayList<ZpVyuzitiJed>();

		// Jiné právní vztahy
		jinePravVztahy = new ArrayList<JinePravVztahy>();
		tPravnichVzt = new ArrayList<TPravnichVzt>();
		rJpv = new ArrayList<RJpv>();

		// Nemovitosti
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
		pravaStavby = new ArrayList<PravaStavby>();
		rUcelNem = new ArrayList<RUcelNem>();
		ucel = new ArrayList<Ucel>();

		// Prvky katastrální mapy
		dalsiPrvkyMapy = new ArrayList<DalsiPrvkyMapy>();
		hraniceParcel = new ArrayList<HraniceParcel>();
		kodyCharQBodu = new ArrayList<KodyCharQBodu>();
		obrazyBoduBp = new ArrayList<ObrazyBoduBp>();
		obrazyBudov = new ArrayList<ObrazyBudov>();
		obrazyParcel = new ArrayList<ObrazyParcel>();
		prvkyOMapy = new ArrayList<PrvkyOMapy>();
		souradniceObrazu = new ArrayList<SouradniceObrazu>();
		souradnicePolohy = new ArrayList<SouradnicePolohy>();
		spojeniBMapy = new ArrayList<SpojeniBMapy>();
		spojeniBPoloh = new ArrayList<SpojeniBPoloh>();
		spojeniPoMapy = new ArrayList<SpojeniPoMapy>();
		tPrvkuPdat = new ArrayList<TPrvkuPDat>();
		tSouradSys = new ArrayList<TSouradSys>();
		zobrazeniVb = new ArrayList<ZobrazeniVb>();

		// Rezervovaná čísla
		dotcenaParCisla = new ArrayList<DotcenaParCisla>();
		dotHistParCisla = new ArrayList<DotHistParCisla>();
		rezCislaPbpp = new ArrayList<RezCislaPbpp>();
		rezParcelniCisla = new ArrayList<RezParcelniCisla>();

		// Řízení
		adresy = new ArrayList<Adresy>();
		dalsiUdajeListiny = new ArrayList<DalsiUdajeListiny>();
		listiny = new ArrayList<Listiny>();
		listinyDalsiUdaje = new ArrayList<ListinyDalsiUdaje>();
		obeslaniMf = new ArrayList<ObeslaniMf>();
		objektyRizeni = new ArrayList<ObjektyRizeni>();
		predmetyRizeni = new ArrayList<PredmetyRizeni>();
		rizeni = new ArrayList<Rizeni>();
		rizeniKu = new ArrayList<RizeniKu>();
		rList = new ArrayList<RList>();
		tListin = new ArrayList<TListin>();
		tPredmetuR = new ArrayList<TPredmetuR>();
		typyRizeni = new ArrayList<TypyRizeni>();
		typyUcastniku = new ArrayList<TypyUcastniku>();
		ucastnici = new ArrayList<Ucastnici>();
		ucastniciTyp = new ArrayList<UcastniciTyp>();

		// Vlastnictví
		charOs = new ArrayList<CharOs>();
		opravSubjekty = new ArrayList<OpravSubjekty>();
		telesa = new ArrayList<Telesa>();
		vlastnictvi = new ArrayList<Vlastnictvi>();
	}

	public int getZmeny() {
		return zmeny;
	}

	public void setZmeny(int zmeny) {
		this.zmeny = zmeny;
	}

	public List<Adresa> getAdresa() {
		return adresa;
	}

	public void setAdresa(List<Adresa> adresa) {
		this.adresa = adresa;
	}

	public List<BudObj> getBudObj() {
		return budObj;
	}

	public void setBudObj(List<BudObj> budObj) {
		this.budObj = budObj;
	}

	public List<BonitDilyParc> getBonitDilyParc() {
		return bonitDilyParc;
	}

	public void setBonitDilyParc(List<BonitDilyParc> bonitDilyParc) {
		this.bonitDilyParc = bonitDilyParc;
	}

	public List<HraniceBpej> getHraniceBpej() {
		return hraniceBpej;
	}

	public void setHraniceBpej(List<HraniceBpej> hraniceBpej) {
		this.hraniceBpej = hraniceBpej;
	}

	public List<OznaceniBpej> getOznaceniBpej() {
		return oznaceniBpej;
	}

	public void setOznaceniBpej(List<OznaceniBpej> oznaceniBpej) {
		this.oznaceniBpej = oznaceniBpej;
	}

	public List<ObrazyDefBodu> getObrazyDefBodu() {
		return obrazyDefBodu;
	}

	public void setObrazyDefBodu(List<ObrazyDefBodu> obrazyDefBodu) {
		this.obrazyDefBodu = obrazyDefBodu;
	}

	public List<NavrhyZmenKm> getNavrhyZmenKm() {
		return navrhyZmenKm;
	}

	public void setNavrhyZmenKm(List<NavrhyZmenKm> navrhyZmenKm) {
		this.navrhyZmenKm = navrhyZmenKm;
	}

	public List<NzZpmz> getNzZpmz() {
		return nzZpmz;
	}

	public void setNzZpmz(List<NzZpmz> nzZpmz) {
		this.nzZpmz = nzZpmz;
	}

	public List<Zpmz> getZpmz() {
		return zpmz;
	}

	public void setZpmz(List<Zpmz> zpmz) {
		this.zpmz = zpmz;
	}

	public List<Jednotky> getJednotky() {
		return jednotky;
	}

	public void setJednotky(List<Jednotky> jednotky) {
		this.jednotky = jednotky;
	}

	public List<TJednotek> getTJednotek() {
		return tJednotek;
	}

	public void setTJednotek(List<TJednotek> tJednotek) {
		this.tJednotek = tJednotek;
	}

	public List<ZpVyuzitiJed> getZpVyuzitiJed() {
		return zpVyuzitiJed;
	}

	public void setZpVyuzitiJed(List<ZpVyuzitiJed> zpVyuzitiJed) {
		this.zpVyuzitiJed = zpVyuzitiJed;
	}

	public List<JinePravVztahy> getJinePravVztahy() {
		return jinePravVztahy;
	}

	public void setJinePravVztahy(List<JinePravVztahy> jinePravVztahy) {
		this.jinePravVztahy = jinePravVztahy;
	}

	public List<TPravnichVzt> getTPravnichVzt() {
		return tPravnichVzt;
	}

	public void setTPravnichVzt(List<TPravnichVzt> tPravnichVzt) {
		this.tPravnichVzt = tPravnichVzt;
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

	public List<Parcely> getParcely() {
		return parcely;
	}

	public void setParcely(List<Parcely> parcely) {
		this.parcely = parcely;
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

	public List<DalsiPrvkyMapy> getDalsiPrvkyMapy() {
		return dalsiPrvkyMapy;
	}

	public void setDalsiPrvkyMapy(List<DalsiPrvkyMapy> dalsiPrvkyMapy) {
		this.dalsiPrvkyMapy = dalsiPrvkyMapy;
	}

	public List<HraniceParcel> getHraniceParcel() {
		return hraniceParcel;
	}

	public void setHraniceParcel(List<HraniceParcel> hraniceParcel) {
		this.hraniceParcel = hraniceParcel;
	}

	public List<KodyCharQBodu> getKodyCharQBodu() {
		return kodyCharQBodu;
	}

	public void setKodyCharQBodu(List<KodyCharQBodu> kodyCharQBodu) {
		this.kodyCharQBodu = kodyCharQBodu;
	}

	public List<ObrazyBoduBp> getObrazyBoduBp() {
		return obrazyBoduBp;
	}

	public void setObrazyBoduBp(List<ObrazyBoduBp> obrazyBoduBp) {
		this.obrazyBoduBp = obrazyBoduBp;
	}

	public List<ObrazyBudov> getObrazyBudov() {
		return obrazyBudov;
	}

	public void setObrazyBudov(List<ObrazyBudov> obrazyBudov) {
		this.obrazyBudov = obrazyBudov;
	}

	public List<ObrazyParcel> getObrazyParcel() {
		return obrazyParcel;
	}

	public void setObrazyParcel(List<ObrazyParcel> obrazyParcel) {
		this.obrazyParcel = obrazyParcel;
	}

	public List<PrvkyOMapy> getPrvkyOMapy() {
		return prvkyOMapy;
	}

	public void setPrvkyOMapy(List<PrvkyOMapy> prvkyOMapy) {
		this.prvkyOMapy = prvkyOMapy;
	}

	public List<SouradniceObrazu> getSouradniceObrazu() {
		return souradniceObrazu;
	}

	public void setSouradniceObrazu(List<SouradniceObrazu> souradniceObrazu) {
		this.souradniceObrazu = souradniceObrazu;
	}

	public List<SouradnicePolohy> getSouradnicePolohy() {
		return souradnicePolohy;
	}

	public void setSouradnicePolohy(List<SouradnicePolohy> souradnicePolohy) {
		this.souradnicePolohy = souradnicePolohy;
	}

	public List<SpojeniBMapy> getSpojeniBMapy() {
		return spojeniBMapy;
	}

	public void setSpojeniBMapy(List<SpojeniBMapy> spojeniBMapy) {
		this.spojeniBMapy = spojeniBMapy;
	}

	public List<SpojeniBPoloh> getSpojeniBPoloh() {
		return spojeniBPoloh;
	}

	public void setSpojeniBPoloh(List<SpojeniBPoloh> spojeniBPoloh) {
		this.spojeniBPoloh = spojeniBPoloh;
	}

	public List<SpojeniPoMapy> getSpojeniPoMapy() {
		return spojeniPoMapy;
	}

	public void setSpojeniPoMapy(List<SpojeniPoMapy> spojeniPoMapy) {
		this.spojeniPoMapy = spojeniPoMapy;
	}

	public List<TPrvkuPDat> getTPrvkuPDat() {
		return tPrvkuPdat;
	}

	public void setTPrvkuPDat(List<TPrvkuPDat> tPrvkuPdat) {
		this.tPrvkuPdat = tPrvkuPdat;
	}

	public List<TSouradSys> getTSouradSys() {
		return tSouradSys;
	}

	public void setTSouradSys(List<TSouradSys> tSouradSys) {
		this.tSouradSys = tSouradSys;
	}

	public List<ZobrazeniVb> getZobrazeniVb() {
		return zobrazeniVb;
	}

	public void setZobrazeniVb(List<ZobrazeniVb> zobrazeniVb) {
		this.zobrazeniVb = zobrazeniVb;
	}

	public List<DotcenaParCisla> getDotcenaParCisla() {
		return dotcenaParCisla;
	}

	public void setDotcenaParCisla(List<DotcenaParCisla> dotcenaParCisla) {
		this.dotcenaParCisla = dotcenaParCisla;
	}

	public List<DotHistParCisla> getDotHistParCisla() {
		return dotHistParCisla;
	}

	public void setDotHistParCisla(List<DotHistParCisla> dotHistParCisla) {
		this.dotHistParCisla = dotHistParCisla;
	}

	public List<RezCislaPbpp> getRezCislaPbpp() {
		return rezCislaPbpp;
	}

	public void setRezCislaPbpp(List<RezCislaPbpp> rezCislaPbpp) {
		this.rezCislaPbpp = rezCislaPbpp;
	}

	public List<RezParcelniCisla> getRezParcelniCisla() {
		return rezParcelniCisla;
	}

	public void setRezParcelniCisla(List<RezParcelniCisla> rezParcelniCisla) {
		this.rezParcelniCisla = rezParcelniCisla;
	}

	public List<Adresy> getAdresy() {
		return adresy;
	}

	public void setAdresy(List<Adresy> adresy) {
		this.adresy = adresy;
	}

	public List<DalsiUdajeListiny> getDalsiUdajeListiny() {
		return dalsiUdajeListiny;
	}

	public void setDalsiUdajeListiny(List<DalsiUdajeListiny> dalsiUdajeListiny) {
		this.dalsiUdajeListiny = dalsiUdajeListiny;
	}

	public List<Listiny> getListiny() {
		return listiny;
	}

	public void setListiny(List<Listiny> listiny) {
		this.listiny = listiny;
	}

	public List<ListinyDalsiUdaje> getListinyDalsiUdaje() {
		return listinyDalsiUdaje;
	}

	public void setListinyDalsiUdaje(List<ListinyDalsiUdaje> listinyDalsiUdaje) {
		this.listinyDalsiUdaje = listinyDalsiUdaje;
	}

	public List<ObeslaniMf> getObeslaniMf() {
		return obeslaniMf;
	}

	public void setObeslaniMf(List<ObeslaniMf> obeslaniMf) {
		this.obeslaniMf = obeslaniMf;
	}

	public List<ObjektyRizeni> getObjektyRizeni() {
		return objektyRizeni;
	}

	public void setObjektyRizeni(List<ObjektyRizeni> objektyRizeni) {
		this.objektyRizeni = objektyRizeni;
	}

	public List<PredmetyRizeni> getPredmetyRizeni() {
		return predmetyRizeni;
	}

	public void setPredmetyRizeni(List<PredmetyRizeni> predmetyRizeni) {
		this.predmetyRizeni = predmetyRizeni;
	}

	public List<Rizeni> getRizeni() {
		return rizeni;
	}

	public void setRizeni(List<Rizeni> rizeni) {
		this.rizeni = rizeni;
	}

	public List<RizeniKu> getRizeniKu() {
		return rizeniKu;
	}

	public void setRizeniKu(List<RizeniKu> rizeniKu) {
		this.rizeniKu = rizeniKu;
	}

	public List<RList> getRList() {
		return rList;
	}

	public void setRList(List<RList> rList) {
		this.rList = rList;
	}

	public List<TListin> getTListin() {
		return tListin;
	}

	public void setTListin(List<TListin> tListin) {
		this.tListin = tListin;
	}

	public List<TPredmetuR> getTPredmetuR() {
		return tPredmetuR;
	}

	public void setTPredmetuR(List<TPredmetuR> tPredmetuR) {
		this.tPredmetuR = tPredmetuR;
	}

	public List<TypyRizeni> getTypyRizeni() {
		return typyRizeni;
	}

	public void setTypyRizeni(List<TypyRizeni> typyRizeni) {
		this.typyRizeni = typyRizeni;
	}

	public List<TypyUcastniku> getTypyUcastniku() {
		return typyUcastniku;
	}

	public void setTypyUcastniku(List<TypyUcastniku> typyUcastniku) {
		this.typyUcastniku = typyUcastniku;
	}

	public List<Ucastnici> getUcastnici() {
		return ucastnici;
	}

	public void setUcastnici(List<Ucastnici> ucastnici) {
		this.ucastnici = ucastnici;
	}

	public List<UcastniciTyp> getUcastniciTyp() {
		return ucastniciTyp;
	}

	public void setUcastniciTyp(List<UcastniciTyp> ucastniciTyp) {
		this.ucastniciTyp = ucastniciTyp;
	}

	public List<CharOs> getCharOs() {
		return charOs;
	}

	public void setCharOs(List<CharOs> charOs) {
		this.charOs = charOs;
	}

	public List<OpravSubjekty> getOpravSubjekty() {
		return opravSubjekty;
	}

	public void setOpravSubjekty(List<OpravSubjekty> opravSubjekty) {
		this.opravSubjekty = opravSubjekty;
	}

	public List<Telesa> getTelesa() {
		return telesa;
	}

	public void setTelesa(List<Telesa> telesa) {
		this.telesa = telesa;
	}

	public List<Vlastnictvi> getVlastnictvi() {
		return vlastnictvi;
	}

	public void setVlastnictvi(List<Vlastnictvi> vlastnictvi) {
		this.vlastnictvi = vlastnictvi;
	}

	public List<PravaStavby> getPravaStavby() {
		return pravaStavby;
	}

	public void setPravaStavby(List<PravaStavby> pravaStavby) {
		this.pravaStavby = pravaStavby;
	}

	public List<RUcelNem> getRUcelNem() {
		return rUcelNem;
	}

	public void setRUcelNem(List<RUcelNem> rUcelNem) {
		this.rUcelNem = rUcelNem;
	}

	public List<Ucel> getUcel() {
		return ucel;
	}

	public void setUcel(List<Ucel> ucel) {
		this.ucel = ucel;
	}

	public List<RJpv> getRJpv() {
		return rJpv;
	}

	public void setRJpv(List<RJpv> rJpv) {
		this.rJpv = rJpv;
	}

	public boolean isLastBatch() {
		return lastBatch;
	}

	public void setLastBatch(boolean lastBatch) {
		this.lastBatch = lastBatch;
	}

}
