package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.adresnimista.Adresa;
import cz.pfreiberg.knparser.domain.adresnimista.BudObj;
import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;
import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.HraniceBpej;
import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.OznaceniBpej;
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
import cz.pfreiberg.knparser.exporter.Exporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.AdresyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.BonitDilyParcOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.BudovyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.CastiBudovOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.CastiObciOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.CharOsOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ConnectionParameters;
import cz.pfreiberg.knparser.exporter.oracledatabase.DPozemkuOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.DalsiPrvkyMapyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.DalsiUdajeListinyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.HraniceBpejOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.HraniceParcelOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.JednotkyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.JinePravVztahyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.KatastrUzemiOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.KodyCharQBoduOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.KrajeOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ListinyDalsiUdajeOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ListinyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.MapoveListyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.NavrhyZmenKmOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.NzZpmzOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObceOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObeslaniMfOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObjektyRizeniOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyBoduBpOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyBudovOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyDefBoduOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyParcelOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.OkresyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.OpravSubjektyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.OznaceniBpejOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ParcelyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.PravaStavbyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.PredmetyRizeniOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.PrvkyOMapyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RJpvOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RListOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RUcelNemOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RZpochrOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RizeniKuOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.RizeniOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.SouradniceObrazuOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.SouradnicePolohyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.SpojeniBMapyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.SpojeniBPolohOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.SpojeniPoMapyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TBudovOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TJednotekOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TListinOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TPravnichVztOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TPredmetuROracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TPrvkuPDatOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TSouradSysOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TelesaOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TypyRizeniOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.TypyUcastnikuOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.UcastniciOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.UcastniciTypOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.UcelOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.VlastnictviOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZdrojeParcelZeOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZobrazeniVbOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpOchranyNemOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpUrceniVymeryOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpVyuzitiBudOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpVyuzitiJedOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpVyuzitiPozOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ZpmzOracleDatabaseJdbcExporter;

public class OracleDatabaseExporterFactory implements ExporterFactory {

	private final ConnectionParameters connection;

	public OracleDatabaseExporterFactory(ConnectionParameters connection) {
		this.connection = connection;
	}

	@Override
	public Exporter getBonitDilyParcExporter(List<BonitDilyParc> bonitDilyParc) {
		System.out.println("Saving BonitDilyParc to database. Total: " + bonitDilyParc.size());
		return new BonitDilyParcOracleDatabaseJdbcExporter(bonitDilyParc, connection);
	}

	@Override
	public Exporter getJednotkyExporter(List<Jednotky> jednotky) {
		System.out.println("Saving Jednotky to database. Total: " + jednotky.size());
		return new JednotkyOracleDatabaseJdbcExporter(jednotky, connection);
	}

	@Override
	public Exporter getTJednotekExporter(List<TJednotek> tJednotek) {
		System.out.println("Saving TJednotek to database. Total: " + tJednotek.size());
		return new TJednotekOracleDatabaseJdbcExporter(tJednotek, connection);
	}

	@Override
	public Exporter getZpVyuzitiJedExporter(List<ZpVyuzitiJed> zpVyuzitiJed) {
		System.out.println("Saving ZpVyuzitiJed to database. Total: " + zpVyuzitiJed.size());
		return new ZpVyuzitiJedOracleDatabaseJdbcExporter(zpVyuzitiJed, connection);
	}

	@Override
	public Exporter getJinePravVztahyExporter(
			List<JinePravVztahy> jinePravVztahy) {
		System.out.println("Saving JinePravVztahy to database. Total: " + jinePravVztahy.size());
		return new JinePravVztahyOracleDatabaseJdbcExporter(jinePravVztahy, connection);
	}

	@Override
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt) {
		System.out.println("Saving TPravnichVzt to database. Total: " + tPravnichVzt.size());
		return new TPravnichVztOracleDatabaseJdbcExporter(tPravnichVzt, connection);
	}

	@Override
	public Exporter getRJpvExporter(List<RJpv> rJpv) {
		System.out.println("Saving RJpv to database. Total: " + rJpv.size());
		return new RJpvOracleDatabaseJdbcExporter(rJpv, connection);
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		System.out.println("Saving Parcely to database. Total: " + parcely.size());
		return new ParcelyOracleDatabaseJdbcExporter(parcely, connection);
	}

	@Override
	public Exporter getBudovyExporter(List<Budovy> budovy) {
		System.out.println("Saving Budovy to database. Total: " + budovy.size());
		return new BudovyOracleDatabaseJdbcExporter(budovy, connection);
	}

	@Override
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov) {
		System.out.println("Saving CastiBudov to database. Total: " + castiBudov.size());
		return new CastiBudovOracleDatabaseJdbcExporter(castiBudov, connection);
	}

	@Override
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem) {
		System.out.println("Saving ZpOchranyNem to database. Total: " + zpOchranyNem.size());
		return new ZpOchranyNemOracleDatabaseJdbcExporter(zpOchranyNem, connection);
	}

	@Override
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku) {
		System.out.println("Saving DPozemku to database. Total: " + dPozemku.size());
		return new DPozemkuOracleDatabaseJdbcExporter(dPozemku, connection);
	}

	
	@Override
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		System.out.println("Saving ZpVyuzitiPoz to database. Total: " + zpVyuzitiPoz.size());
		return new ZpVyuzitiPozOracleDatabaseJdbcExporter(zpVyuzitiPoz, connection);
	}

	@Override
	public Exporter getZdrojeParcelZeExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe) {
		System.out.println("Saving ZdrojeParcelZe to database. Total: " + zdrojeParcelZe.size());
		return new ZdrojeParcelZeOracleDatabaseJdbcExporter(zdrojeParcelZe, connection);
	}

	@Override
	public Exporter getZpUrceniVymeryExporter(
			List<ZpUrceniVymery> zpUrceniVymery) {
		System.out.println("Saving ZpUrceniVymery to database. Total: " + zpUrceniVymery.size());
		return new ZpUrceniVymeryOracleDatabaseJdbcExporter(zpUrceniVymery, connection);
	}

	@Override
	public Exporter getTBudovExporter(List<TBudov> tBudov) {
		System.out.println("Saving TBudov to database. Total: " + tBudov.size());
		return new TBudovOracleDatabaseJdbcExporter(tBudov, connection);
	}

	@Override
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy) {
		System.out.println("Saving MapoveListy to database. Total: " + mapoveListy.size());
		return new MapoveListyOracleDatabaseJdbcExporter(mapoveListy, connection);
	}

	@Override
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi) {
		System.out.println("Saving KatastrUzemi to database. Total: " + katastrUzemi.size());
		return new KatastrUzemiOracleDatabaseJdbcExporter(katastrUzemi, connection);
	}

	@Override
	public Exporter getObceExporter(List<Obce> obce) {
		System.out.println("Saving Obce to database. Total: " + obce.size());
		return new ObceOracleDatabaseJdbcExporter(obce, connection);
	}

	@Override
	public Exporter getCastiObciExporter(List<CastiObci> castiObci) {
		System.out.println("Saving CastiObci to database. Total: " + castiObci.size());
		return new CastiObciOracleDatabaseJdbcExporter(castiObci, connection);
	}

	@Override
	public Exporter getOkresyExporter(List<Okresy> okresy) {
		System.out.println("Saving Okresy to database. Total: " + okresy.size());
		return new OkresyOracleDatabaseJdbcExporter(okresy, connection);
	}

	@Override
	public Exporter getKrajeExporter(List<Kraje> kraje) {
		System.out.println("Saving Kraje to database. Total: " + kraje.size());
		return new KrajeOracleDatabaseJdbcExporter(kraje, connection);
	}

	@Override
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje) {
		return null;
	}

	@Override
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr) {
		System.out.println("Saving RZpochr to database. Total: " + rZpochr.size());
		return new RZpochrOracleDatabaseJdbcExporter(rZpochr, connection);
	}

	@Override
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud) {
		System.out.println("Saving ZpVyuzitiBud to database. Total: " + zpVyuzitiBud.size());
		return new ZpVyuzitiBudOracleDatabaseJdbcExporter(zpVyuzitiBud, connection);
	}

	@Override
	public Exporter getPravaStavbyExporter(List<PravaStavby> pravaStavby) {
		System.out.println("Saving PravaStavby to database. Total: " + pravaStavby.size());
		return new PravaStavbyOracleDatabaseJdbcExporter(pravaStavby, connection);
	}

	@Override
	public Exporter getRUcelNemExporter(List<RUcelNem> rUcelNem) {
		System.out.println("Saving RUcelNem to database. Total: " + rUcelNem.size());
		return new RUcelNemOracleDatabaseJdbcExporter(rUcelNem, connection);
	}

	@Override
	public Exporter getUcelExporter(List<Ucel> ucel) {
		System.out.println("Saving Ucel to database. Total: " + ucel.size());
		return new UcelOracleDatabaseJdbcExporter(ucel, connection);
	}

	@Override
	public Exporter getAdresyExporter(List<Adresy> adresy) {
		System.out.println("Saving Adresy to database. Total: " + adresy.size());
		return new AdresyOracleDatabaseJdbcExporter(adresy, connection);
	}

	@Override
	public Exporter getDalsiUdajeListinyExporter(
			List<DalsiUdajeListiny> dalsiUdajeListiny) {
		System.out.println("Saving DalsiUdajeListiny to database. Total: " + dalsiUdajeListiny.size());
		return new DalsiUdajeListinyOracleDatabaseJdbcExporter(dalsiUdajeListiny, connection);
	}

	@Override
	public Exporter getListinyExporter(List<Listiny> listiny) {
		System.out.println("Saving Listiny to database. Total: " + listiny.size());
		return new ListinyOracleDatabaseJdbcExporter(listiny, connection);
	}

	@Override
	public Exporter getListinyDalsiUdajeExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje) {
		System.out.println("Saving ListinyDalsiUdaje to database. Total: " + listinyDalsiUdaje.size());
		return new ListinyDalsiUdajeOracleDatabaseJdbcExporter(listinyDalsiUdaje, connection);
	}

	@Override
	public Exporter getObeslaniMfExporter(List<ObeslaniMf> obeslaniMf) {
		System.out.println("Saving ObeslaniMf to database. Total: " + obeslaniMf.size());
		return new ObeslaniMfOracleDatabaseJdbcExporter(obeslaniMf, connection);
	}

	@Override
	public Exporter getObjektyRizeniExporter(List<ObjektyRizeni> objektyRizeni) {
		System.out.println("Saving ObjektyRizeni to database. Total: " + objektyRizeni.size());
		return new ObjektyRizeniOracleDatabaseJdbcExporter(objektyRizeni, connection);
	}

	@Override
	public Exporter getPredmetyRizeniExporter(
			List<PredmetyRizeni> predmetyRizeni) {
		System.out.println("Saving PredmetyRizeni to database. Total: " + predmetyRizeni.size());
		return new PredmetyRizeniOracleDatabaseJdbcExporter(predmetyRizeni, connection);
	}

	@Override
	public Exporter getRizeniExporter(List<Rizeni> rizeni) {
		System.out.println("Saving RizeniExporter to database. Total: " + rizeni.size());
		return new RizeniOracleDatabaseJdbcExporter(rizeni, connection);
	}

	@Override
	public Exporter getRizeniKuExporter(List<RizeniKu> rizeniKu) {
		System.out.println("Saving RizeniKu to database. Total: " + rizeniKu.size());
		return new RizeniKuOracleDatabaseJdbcExporter(rizeniKu, connection);
	}

	@Override
	public Exporter getRListExporter(List<RList> rList) {
		System.out.println("Saving RList to database. Total: " + rList.size());
		return new RListOracleDatabaseJdbcExporter(rList, connection);
	}

	@Override
	public Exporter getTListinExporter(List<TListin> tListin) {
		System.out.println("Saving TListin to database. Total: " + tListin.size());
		return new TListinOracleDatabaseJdbcExporter(tListin, connection);
	}

	@Override
	public Exporter getTPredmetuRExporter(List<TPredmetuR> tPredmetuR) {
		System.out.println("Saving TPredmetuR to database. Total: " + tPredmetuR.size());
		return new TPredmetuROracleDatabaseJdbcExporter(tPredmetuR, connection);
	}

	@Override
	public Exporter getTypyRizeniExporter(List<TypyRizeni> typyRizeni) {
		System.out.println("Saving TypyRizeni to database. Total: " + typyRizeni.size());
		return new TypyRizeniOracleDatabaseJdbcExporter(typyRizeni, connection);
	}

	@Override
	public Exporter getTypyUcastnikuExporter(List<TypyUcastniku> typyUcastniku) {
		System.out.println("Saving TypyUcastniku to database. Total: " + typyUcastniku.size());
		return new TypyUcastnikuOracleDatabaseJdbcExporter(typyUcastniku, connection);
	}

	@Override
	public Exporter getUcastniciExporter(List<Ucastnici> ucastnici) {
		System.out.println("Saving Ucastnici to database. Total: " + ucastnici.size());
		return new UcastniciOracleDatabaseJdbcExporter(ucastnici, connection);
	}

	@Override
	public Exporter getUcastniciTypExporter(List<UcastniciTyp> ucastniciTyp) {
		System.out.println("Saving UcastniciTyp to database. Total: " + ucastniciTyp.size());
		return new UcastniciTypOracleDatabaseJdbcExporter(ucastniciTyp, connection);
	}

	@Override
	public Exporter getCharOsExporter(List<CharOs> charOs) {
		System.out.println("Saving CharOs to database. Total: " + charOs.size());
		return new CharOsOracleDatabaseJdbcExporter(charOs, connection);
	}

	@Override
	public Exporter getOpravSubjektyExporter(List<OpravSubjekty> opravSubjekty) {
		System.out.println("Saving OpravSubjekty to database. Total: " + opravSubjekty.size());
		return new OpravSubjektyOracleDatabaseJdbcExporter(opravSubjekty, connection);
	}

	@Override
	public Exporter getTelesaExporter(List<Telesa> telesa) {
		System.out.println("Saving Telesa to database. Total: " + telesa.size());
		return new TelesaOracleDatabaseJdbcExporter(telesa, connection);
	}

	@Override
	public Exporter getVlastnictviExporter(List<Vlastnictvi> vlastnictvi) {
		System.out.println("Saving Vlastnictvi to database. Total: " + vlastnictvi.size());
		return new VlastnictviOracleDatabaseJdbcExporter(vlastnictvi, connection);
	}

	@Override
	public Exporter getDalsiPrvkyMapyExporter(
			List<DalsiPrvkyMapy> dalsiPrvkyMapy) {
		System.out.println("Saving DalsiPrvkyMapy to database. Total: " + dalsiPrvkyMapy.size());
		return new DalsiPrvkyMapyOracleDatabaseJdbcExporter(dalsiPrvkyMapy, connection);
	}

	@Override
	public Exporter getHraniceParcelExporter(List<HraniceParcel> hraniceParcel) {
		System.out.println("Saving HraniceParcel to database. Total: " + hraniceParcel.size());
		return new HraniceParcelOracleDatabaseJdbcExporter(hraniceParcel, connection);
	}

	@Override
	public Exporter getKodyCharQBoduExporter(List<KodyCharQBodu> kodyCharQBodu) {
		System.out.println("Saving KodyCharQBodu to database. Total: " + kodyCharQBodu.size());
		return new KodyCharQBoduOracleDatabaseJdbcExporter(kodyCharQBodu, connection);
	}

	@Override
	public Exporter getObrazyBoduBpExporter(List<ObrazyBoduBp> obrazyBoduBp) {
		System.out.println("Saving ObrazyBoduBp to database. Total: " + obrazyBoduBp.size());
		return new ObrazyBoduBpOracleDatabaseJdbcExporter(obrazyBoduBp, connection);
	}

	@Override
	public Exporter getObrazyBudovExporter(List<ObrazyBudov> obrazyBudov) {
		System.out.println("Saving ObrazyBudov to database. Total: " + obrazyBudov.size());
		return new ObrazyBudovOracleDatabaseJdbcExporter(obrazyBudov, connection);
	}

	@Override
	public Exporter getObrazyParcelExporter(List<ObrazyParcel> obrazyParcel) {
		System.out.println("Saving ObrazyParcel to database. Total: " + obrazyParcel.size());
		return new ObrazyParcelOracleDatabaseJdbcExporter(obrazyParcel, connection);
	}

	@Override
	public Exporter getPrvkyOMapyExporter(List<PrvkyOMapy> prvkyOMapy) {
		System.out.println("Saving PrvkyOMapy to database. Total: " + prvkyOMapy.size());
		return new PrvkyOMapyOracleDatabaseJdbcExporter(prvkyOMapy, connection);
	}

	@Override
	public Exporter getSouradniceObrazuExporter(
			List<SouradniceObrazu> souradniceObrazu) {
		System.out.println("Saving SouradniceObrazu to database. Total: " + souradniceObrazu.size());
		return new SouradniceObrazuOracleDatabaseJdbcExporter(souradniceObrazu, connection);
	}

	@Override
	public Exporter getSouradnicePolohyExporter(
			List<SouradnicePolohy> souradnicePolohy) {
		System.out.println("Saving SouradnicePolohy to database. Total: " + souradnicePolohy.size());
		return new SouradnicePolohyOracleDatabaseJdbcExporter(souradnicePolohy, connection);
	}

	@Override
	public Exporter getSpojeniBMapyExporter(List<SpojeniBMapy> spojeniBMapy) {
		System.out.println("Saving SpojeniBMapy to database. Total: " + spojeniBMapy.size());
		return new SpojeniBMapyOracleDatabaseJdbcExporter(spojeniBMapy, connection);
	}

	@Override
	public Exporter getSpojeniBPolohExporter(List<SpojeniBPoloh> spojeniBPoloh) {
		System.out.println("Saving SpojeniBPoloh to database. Total: " + spojeniBPoloh.size());
		return new SpojeniBPolohOracleDatabaseJdbcExporter(spojeniBPoloh, connection);
	}

	@Override
	public Exporter getSpojeniPoMapyExporter(List<SpojeniPoMapy> spojeniPoMapy) {
		System.out.println("Saving SpojeniPoMapy to database. Total: " + spojeniPoMapy.size());
		return new SpojeniPoMapyOracleDatabaseJdbcExporter(spojeniPoMapy, connection);
	}

	@Override
	public Exporter getTPrvkuPDatExporter(List<TPrvkuPDat> tPrvkuPDat) {
		System.out.println("Saving TPrvkuPDat to database. Total: " + tPrvkuPDat.size());
		return new TPrvkuPDatOracleDatabaseJdbcExporter(tPrvkuPDat, connection);
	}

	@Override
	public Exporter getTSouradSysExporter(List<TSouradSys> tSouradSys) {
		System.out.println("Saving TSouradSys to database. Total: " + tSouradSys.size());
		return new TSouradSysOracleDatabaseJdbcExporter(tSouradSys, connection);
	}

	@Override
	public Exporter getZobrazeniVbExporter(List<ZobrazeniVb> zobrazeniVb) {
		System.out.println("Saving ZobrazeniVb to database. Total: " + zobrazeniVb.size());
		return new ZobrazeniVbOracleDatabaseJdbcExporter(zobrazeniVb, connection);
	}

	@Override
	public Exporter getHraniceBpejExporter(List<HraniceBpej> hraniceBpej) {
		System.out.println("Saving HraniceBpej to database. Total: " + hraniceBpej.size());
		return new HraniceBpejOracleDatabaseJdbcExporter(hraniceBpej, connection);
	}

	@Override
	public Exporter getOznaceniBpejExporter(List<OznaceniBpej> oznaceniBpej) {
		System.out.println("Saving OznaceniBpej to database. Total: " + oznaceniBpej.size());
		return new OznaceniBpejOracleDatabaseJdbcExporter(oznaceniBpej, connection);
	}

	@Override
	public Exporter getNavrhyZmenKmExporter(List<NavrhyZmenKm> navrhyZmenKm) {
		System.out.println("Saving NavrhyZmenKm to database. Total: " + navrhyZmenKm.size());
		return new NavrhyZmenKmOracleDatabaseJdbcExporter(navrhyZmenKm, connection);
	}

	@Override
	public Exporter getNzZpmzExporter(List<NzZpmz> nzZpmz) {
		System.out.println("Saving NzZpmz to database. Total: " + nzZpmz.size());
		return new NzZpmzOracleDatabaseJdbcExporter(nzZpmz, connection);
	}

	@Override
	public Exporter getZpmzExporter(List<Zpmz> zpmz) {
		System.out.println("Saving Zpmz to database. Total: " + zpmz.size());
		return new ZpmzOracleDatabaseJdbcExporter(zpmz, connection);
	}

	@Override
	public Exporter getDotcenaParCislaExporter(
			List<DotcenaParCisla> dotcenaParCisla) {
		return null;
	}

	@Override
	public Exporter getDotHistParCislaExporter(
			List<DotHistParCisla> dotHistParCisla) {
		return null;
	}

	@Override
	public Exporter getRezCislaPbppExporter(List<RezCislaPbpp> rezCislaPbpp) {
		return null;
	}

	@Override
	public Exporter getRezParcelniCislaExporter(
			List<RezParcelniCisla> rezParcelniCisla) {
		return null;
	}

	@Override
	public Exporter getObrazyDefBoduExporter(List<ObrazyDefBodu> obrazyDefBodu) {
		System.out.println("Saving ObrazyDefBodu to database. Total: " + obrazyDefBodu.size());
		return new ObrazyDefBoduOracleDatabaseJdbcExporter(obrazyDefBodu, connection);
	}

	@Override
	public Exporter getAdresaExporter(List<Adresa> Adresa) {
		return null;
	}

	@Override
	public Exporter getBudObjExporter(List<BudObj> BudObj) {
		return null;
	}

}
