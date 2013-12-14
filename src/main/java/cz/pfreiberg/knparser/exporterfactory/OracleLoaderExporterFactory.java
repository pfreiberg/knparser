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
import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.domain.nemovitosti.TBudov;
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
import cz.pfreiberg.knparser.exporter.oracleloaderfile.AdresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BonitDilyParcOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BudovyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiObciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CharOsOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DPozemkuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DalsiUdajeListinyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.JednotkyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.JinePravVztahyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KatastrUzemiOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ListinyDalsiUdajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ListinyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.MapoveListyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NoveKrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObceOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObeslaniMfOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObjektyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OkresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OpravSubjektyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.PredmetyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RListOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RZpochrOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RizeniKuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TJednotekOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TListinOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TPravnichVztOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TPredmetuROracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TelesaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TypyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TypyUcastnikuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.UcastniciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.UcastniciTypOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.VlastnictviOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZdrojeParcelZeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpOchranyNemOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpUrceniVymeryOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiBudOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiJedOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiPozOracleLoaderFileExporter;

public class OracleLoaderExporterFactory implements ExporterFactory {

	String prefix;
	String characterSet;
	String output;

	public OracleLoaderExporterFactory(int zmeny, String characterSet,
			String output) {
		this.prefix = (zmeny == 1) ? "TMP_" : "";
		this.characterSet = characterSet;
		this.output = output;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleLoaderFileExporter(parcely, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getBudovyExporter(List<Budovy> budovy) {
		return new BudovyOracleLoaderFileExporter(budovy, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov) {
		return new CastiBudovOracleLoaderFileExporter(castiBudov, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem) {
		return new ZpOchranyNemOracleLoaderFileExporter(zpOchranyNem, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku) {
		return new DPozemkuOracleLoaderFileExporter(dPozemku, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		return new ZpVyuzitiPozOracleLoaderFileExporter(zpVyuzitiPoz, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZdrojeParcelZeExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe) {
		return new ZdrojeParcelZeOracleLoaderFileExporter(zdrojeParcelZe,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getZpUrceniVymeryExporter(
			List<ZpUrceniVymery> zpUrceniVymery) {
		return new ZpUrceniVymeryOracleLoaderFileExporter(zpUrceniVymery,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getTBudovExporter(List<TBudov> tBudov) {
		return new TBudovOracleLoaderFileExporter(tBudov, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy) {
		return new MapoveListyOracleLoaderFileExporter(mapoveListy, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi) {
		return new KatastrUzemiOracleLoaderFileExporter(katastrUzemi, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getObceExporter(List<Obce> obce) {
		return new ObceOracleLoaderFileExporter(obce, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getCastiObciExporter(List<CastiObci> castiObci) {
		return new CastiObciOracleLoaderFileExporter(castiObci, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getOkresyExporter(List<Okresy> okresy) {
		return new OkresyOracleLoaderFileExporter(okresy, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getKrajeExporter(List<Kraje> kraje) {
		return new KrajeOracleLoaderFileExporter(kraje, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje) {
		return new NoveKrajeOracleLoaderFileExporter(noveKraje, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr) {
		return new RZpochrOracleLoaderFileExporter(rZpochr, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud) {
		return new ZpVyuzitiBudOracleLoaderFileExporter(zpVyuzitiBud, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getBonitDilyParcExporter(List<BonitDilyParc> bonitDilyParc) {
		return new BonitDilyParcOracleLoaderFileExporter(bonitDilyParc, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getJednotkyExporter(List<Jednotky> jednotky) {
		return new JednotkyOracleLoaderFileExporter(jednotky, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTJednotekExporter(List<TJednotek> tJednotek) {
		return new TJednotekOracleLoaderFileExporter(tJednotek, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getZpVyuzitiJedExporter(List<ZpVyuzitiJed> zpVyuzitiJed) {
		return new ZpVyuzitiJedOracleLoaderFileExporter(zpVyuzitiJed, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getJinePravVztahyExporter(
			List<JinePravVztahy> jinePravVztahy) {
		return new JinePravVztahyOracleLoaderFileExporter(jinePravVztahy,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt) {
		return new TPravnichVztOracleLoaderFileExporter(tPravnichVzt, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getAdresyExporter(List<Adresy> adresy) {
		return new AdresyOracleLoaderFileExporter(adresy, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getDalsiUdajeListinyExporter(
			List<DalsiUdajeListiny> dalsiUdajeListiny) {
		return new DalsiUdajeListinyOracleLoaderFileExporter(dalsiUdajeListiny,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getListinyExporter(List<Listiny> listiny) {
		return new ListinyOracleLoaderFileExporter(listiny, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getListinyDalsiUdajeExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje) {
		return new ListinyDalsiUdajeOracleLoaderFileExporter(listinyDalsiUdaje,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getObeslaniMfExporter(List<ObeslaniMf> obeslaniMf) {
		return new ObeslaniMfOracleLoaderFileExporter(obeslaniMf, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getObjektyRizeniExporter(List<ObjektyRizeni> objektyRizeni) {
		return new ObjektyRizeniOracleLoaderFileExporter(objektyRizeni, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getPredmetyRizeniExporter(
			List<PredmetyRizeni> predmetyRizeni) {
		return new PredmetyRizeniOracleLoaderFileExporter(predmetyRizeni,
				prefix, characterSet, output);
	}

	@Override
	public Exporter getRizeniExporter(List<Rizeni> rizeni) {
		return new RizeniOracleLoaderFileExporter(rizeni, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getRizeniKuExporter(List<RizeniKu> rizeniKu) {
		return new RizeniKuOracleLoaderFileExporter(rizeniKu, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getRListExporter(List<RList> rList) {
		return new RListOracleLoaderFileExporter(rList, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getTListinExporter(List<TListin> tListin) {
		return new TListinOracleLoaderFileExporter(tListin, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTPredmetuRExporter(List<TPredmetuR> tPredmetuR) {
		return new TPredmetuROracleLoaderFileExporter(tPredmetuR, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTypyRizeniExporter(List<TypyRizeni> typyRizeni) {
		return new TypyRizeniOracleLoaderFileExporter(typyRizeni, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTypyUcastnikuExporter(List<TypyUcastniku> typyUcastniku) {
		return new TypyUcastnikuOracleLoaderFileExporter(typyUcastniku, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getUcastniciExporter(List<Ucastnici> ucastnici) {
		return new UcastniciOracleLoaderFileExporter(ucastnici, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getUcastniciTypExporter(List<UcastniciTyp> ucastniciTyp) {
		return new UcastniciTypOracleLoaderFileExporter(ucastniciTyp, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getCharOsExporter(List<CharOs> charOs) {
		return new CharOsOracleLoaderFileExporter(charOs, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getOpravSubjektyExporter(List<OpravSubjekty> opravSubjekty) {
		return new OpravSubjektyOracleLoaderFileExporter(opravSubjekty, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getTelesaExporter(List<Telesa> telesa) {
		return new TelesaOracleLoaderFileExporter(telesa, prefix, characterSet,
				output);
	}

	@Override
	public Exporter getVlastnictviExporter(List<Vlastnictvi> vlastnictvi) {
		return new VlastnictviOracleLoaderFileExporter(vlastnictvi, prefix,
				characterSet, output);
	}

	@Override
	public Exporter getDalsiPrvkyMapyExporter(
			List<DalsiPrvkyMapy> dalsiPrvkyMapy) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getObrazyBudovExporter(List<ObrazyBudov> obrazyBudov) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
