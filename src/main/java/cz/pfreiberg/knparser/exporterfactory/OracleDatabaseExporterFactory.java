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
import cz.pfreiberg.knparser.exporter.oracledatabase.BonitDilyParcOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.BudovyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.CastiBudovOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ConnectionParameters;
import cz.pfreiberg.knparser.exporter.oracledatabase.DalsiPrvkyMapyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.HraniceBpejOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.JednotkyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.JinePravVztahyOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyBoduBpOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyBudovOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ObrazyDefBoduOracleDatabaseJdbcExporter;
import cz.pfreiberg.knparser.exporter.oracledatabase.ParcelyOracleDatabaseJdbcExporter;

public class OracleDatabaseExporterFactory implements ExporterFactory {

	private final ConnectionParameters connection;

	public OracleDatabaseExporterFactory(ConnectionParameters connection) {
		this.connection = connection;
	}

	@Override
	public Exporter getBonitDilyParcExporter(List<BonitDilyParc> bonitDilyParc) {
		return new BonitDilyParcOracleDatabaseJdbcExporter(bonitDilyParc, connection);
	}

	@Override
	public Exporter getJednotkyExporter(List<Jednotky> jednotky) {
		return new JednotkyOracleDatabaseJdbcExporter(jednotky, connection);
	}

	@Override
	public Exporter getTJednotekExporter(List<TJednotek> tJednotek) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZpVyuzitiJedExporter(List<ZpVyuzitiJed> zpVyuzitiJed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getJinePravVztahyExporter(
			List<JinePravVztahy> jinePravVztahy) {
		return new JinePravVztahyOracleDatabaseJdbcExporter(jinePravVztahy, connection);
	}

	@Override
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRJpvExporter(List<RJpv> rJpv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleDatabaseJdbcExporter(parcely, connection);
	}

	@Override
	public Exporter getBudovyExporter(List<Budovy> budovy) {
		return new BudovyOracleDatabaseJdbcExporter(budovy, connection);
	}

	@Override
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov) {
		return new CastiBudovOracleDatabaseJdbcExporter(castiBudov, connection);
	}

	@Override
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZdrojeParcelZeExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZpUrceniVymeryExporter(
			List<ZpUrceniVymery> zpUrceniVymery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTBudovExporter(List<TBudov> tBudov) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObceExporter(List<Obce> obce) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getCastiObciExporter(List<CastiObci> castiObci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getOkresyExporter(List<Okresy> okresy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getKrajeExporter(List<Kraje> Kraje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getPravaStavbyExporter(List<PravaStavby> pravaStavby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRUcelNemExporter(List<RUcelNem> rUcelNem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getUcelExporter(List<Ucel> ucel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getAdresyExporter(List<Adresy> adresy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getDalsiUdajeListinyExporter(
			List<DalsiUdajeListiny> dalsiUdajeListiny) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getListinyExporter(List<Listiny> listiny) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getListinyDalsiUdajeExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObeslaniMfExporter(List<ObeslaniMf> obeslaniMf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObjektyRizeniExporter(List<ObjektyRizeni> objektyRizeni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getPredmetyRizeniExporter(
			List<PredmetyRizeni> predmetyRizeni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRizeniExporter(List<Rizeni> rizeni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRizeniKuExporter(List<RizeniKu> rizeniKu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRListExporter(List<RList> rList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTListinExporter(List<TListin> tListin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTPredmetuRExporter(List<TPredmetuR> tPredmetuR) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTypyRizeniExporter(List<TypyRizeni> typyRizeni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTypyUcastnikuExporter(List<TypyUcastniku> typyUcastniku) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getUcastniciExporter(List<Ucastnici> ucastnici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getUcastniciTypExporter(List<UcastniciTyp> ucastniciTyp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getCharOsExporter(List<CharOs> charOs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getOpravSubjektyExporter(List<OpravSubjekty> opravSubjekty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTelesaExporter(List<Telesa> telesa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getVlastnictviExporter(List<Vlastnictvi> vlastnictvi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getDalsiPrvkyMapyExporter(
			List<DalsiPrvkyMapy> dalsiPrvkyMapy) {
		return new DalsiPrvkyMapyOracleDatabaseJdbcExporter(dalsiPrvkyMapy, connection);
	}

	@Override
	public Exporter getHraniceParcelExporter(List<HraniceParcel> hraniceParcel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getKodyCharQBoduExporter(List<KodyCharQBodu> kodyCharQBodu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObrazyBoduBpExporter(List<ObrazyBoduBp> obrazyBoduBp) {
		return new ObrazyBoduBpOracleDatabaseJdbcExporter(obrazyBoduBp, connection);
	}

	@Override
	public Exporter getObrazyBudovExporter(List<ObrazyBudov> obrazyBudov) {
		return new ObrazyBudovOracleDatabaseJdbcExporter(obrazyBudov, connection);
	}

	@Override
	public Exporter getObrazyParcelExporter(List<ObrazyParcel> obrazyParcel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getPrvkyOMapyExporter(List<PrvkyOMapy> prvkyOMapy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getSouradniceObrazuExporter(
			List<SouradniceObrazu> souradniceObrazu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getSouradnicePolohyExporter(
			List<SouradnicePolohy> souradnicePolohy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getSpojeniBMapyExporter(List<SpojeniBMapy> spojeniBMapy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getSpojeniBPolohExporter(List<SpojeniBPoloh> spojeniBPoloh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getSpojeniPoMapyExporter(List<SpojeniPoMapy> spojeniPoMapy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTPrvkuPDatExporter(List<TPrvkuPDat> tPrvkuPDat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTSouradSysExporter(List<TSouradSys> tSouradSys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZobrazeniVbExporter(List<ZobrazeniVb> zobrazeniVb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getHraniceBpejExporter(List<HraniceBpej> hraniceBpej) {
		return new HraniceBpejOracleDatabaseJdbcExporter(hraniceBpej, connection);
	}

	@Override
	public Exporter getOznaceniBpejExporter(List<OznaceniBpej> oznaceniBpej) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getNavrhyZmenKmExporter(List<NavrhyZmenKm> navrhyZmenKm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getNzZpmzExporter(List<NzZpmz> nzZpmz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getZpmzExporter(List<Zpmz> zpmz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getDotcenaParCislaExporter(
			List<DotcenaParCisla> dotcenaParCisla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getDotHistParCislaExporter(
			List<DotHistParCisla> dotHistParCisla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRezCislaPbppExporter(List<RezCislaPbpp> rezCislaPbpp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getRezParcelniCislaExporter(
			List<RezParcelniCisla> rezParcelniCisla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObrazyDefBoduExporter(List<ObrazyDefBodu> obrazyDefBodu) {
		return new ObrazyDefBoduOracleDatabaseJdbcExporter(obrazyDefBodu, connection);
	}

	@Override
	public Exporter getAdresaExporter(List<Adresa> Adresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getBudObjExporter(List<BudObj> BudObj) {
		// TODO Auto-generated method stub
		return null;
	}

}
