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
import cz.pfreiberg.knparser.exporter.oracleloaderfile.AdresaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.AdresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BonitDilyParcOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BudObjOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BudovyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiObciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CharOsOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DPozemkuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DalsiPrvkyMapyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DalsiUdajeListinyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DotHistParCislaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DotcenaParCislaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.HraniceBpejOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.HraniceParcelOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.JednotkyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.JinePravVztahyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KatastrUzemiOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KodyCharQBoduOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ListinyDalsiUdajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ListinyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.MapoveListyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NavrhyZmenKmOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NoveKrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NzZpmzOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObceOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObeslaniMfOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObjektyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObrazyBoduBpOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObrazyBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObrazyDefBoduOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObrazyParcelOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OkresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OpravSubjektyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OznaceniBpejOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.PravaStavbyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.PredmetyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.PrvkyOMapyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RJpvOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RListOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RUcelNemOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RZpochrOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RezCislaPbppOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RezParcelniCislaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RizeniKuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.SouradniceObrazuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.SouradnicePolohyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.SpojeniBMapyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.SpojeniBPolohOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.SpojeniPoMapyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TJednotekOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TListinOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TPravnichVztOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TPredmetuROracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TPrvkuPDatOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TSouradSysOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TelesaOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TypyRizeniOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TypyUcastnikuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.UcastniciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.UcastniciTypOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.UcelOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.VlastnictviOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZdrojeParcelZeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZobrazeniVbOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpOchranyNemOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpUrceniVymeryOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiBudOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiJedOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiPozOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpmzOracleLoaderFileExporter;

public class OracleLoaderExporterFactory implements ExporterFactory {

	private final String characterSet;
	private final String prefix;
	private final String output;

	public OracleLoaderExporterFactory(int zmeny, String characterSet,
			String output) {
		this.characterSet = characterSet;
		this.prefix = (zmeny == 1) ? "TMP_" : "";
		this.output = output;
	}

	@Override
	public Exporter getParcelyExporter(List<Parcely> parcely) {
		return new ParcelyOracleLoaderFileExporter(parcely, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getBudovyExporter(List<Budovy> budovy) {
		return new BudovyOracleLoaderFileExporter(budovy, characterSet, output,  prefix);
	}

	@Override
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov) {
		return new CastiBudovOracleLoaderFileExporter(castiBudov, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem) {
		return new ZpOchranyNemOracleLoaderFileExporter(zpOchranyNem,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku) {
		return new DPozemkuOracleLoaderFileExporter(dPozemku, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz) {
		return new ZpVyuzitiPozOracleLoaderFileExporter(zpVyuzitiPoz,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getZdrojeParcelZeExporter(
			List<ZdrojeParcelZe> zdrojeParcelZe) {
		return new ZdrojeParcelZeOracleLoaderFileExporter(zdrojeParcelZe,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getZpUrceniVymeryExporter(
			List<ZpUrceniVymery> zpUrceniVymery) {
		return new ZpUrceniVymeryOracleLoaderFileExporter(zpUrceniVymery,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getTBudovExporter(List<TBudov> tBudov) {
		return new TBudovOracleLoaderFileExporter(tBudov, characterSet, output,  prefix);
	}

	@Override
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy) {
		return new MapoveListyOracleLoaderFileExporter(mapoveListy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi) {
		return new KatastrUzemiOracleLoaderFileExporter(katastrUzemi,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObceExporter(List<Obce> obce) {
		return new ObceOracleLoaderFileExporter(obce, characterSet, output,  prefix);
	}

	@Override
	public Exporter getCastiObciExporter(List<CastiObci> castiObci) {
		return new CastiObciOracleLoaderFileExporter(castiObci, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getOkresyExporter(List<Okresy> okresy) {
		return new OkresyOracleLoaderFileExporter(okresy, characterSet, output,  prefix);
	}

	@Override
	public Exporter getKrajeExporter(List<Kraje> kraje) {
		return new KrajeOracleLoaderFileExporter(kraje, characterSet, output,  prefix);
	}

	@Override
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje) {
		return new NoveKrajeOracleLoaderFileExporter(noveKraje, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr) {
		return new RZpochrOracleLoaderFileExporter(rZpochr, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud) {
		return new ZpVyuzitiBudOracleLoaderFileExporter(zpVyuzitiBud,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getBonitDilyParcExporter(List<BonitDilyParc> bonitDilyParc) {
		return new BonitDilyParcOracleLoaderFileExporter(bonitDilyParc,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getJednotkyExporter(List<Jednotky> jednotky) {
		return new JednotkyOracleLoaderFileExporter(jednotky, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getTJednotekExporter(List<TJednotek> tJednotek) {
		return new TJednotekOracleLoaderFileExporter(tJednotek, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getZpVyuzitiJedExporter(List<ZpVyuzitiJed> zpVyuzitiJed) {
		return new ZpVyuzitiJedOracleLoaderFileExporter(zpVyuzitiJed,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getJinePravVztahyExporter(
			List<JinePravVztahy> jinePravVztahy) {
		return new JinePravVztahyOracleLoaderFileExporter(jinePravVztahy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt) {
		return new TPravnichVztOracleLoaderFileExporter(tPravnichVzt,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getAdresyExporter(List<Adresy> adresy) {
		return new AdresyOracleLoaderFileExporter(adresy, characterSet, output,  prefix);
	}

	@Override
	public Exporter getDalsiUdajeListinyExporter(
			List<DalsiUdajeListiny> dalsiUdajeListiny) {
		return new DalsiUdajeListinyOracleLoaderFileExporter(dalsiUdajeListiny,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getListinyExporter(List<Listiny> listiny) {
		return new ListinyOracleLoaderFileExporter(listiny, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getListinyDalsiUdajeExporter(
			List<ListinyDalsiUdaje> listinyDalsiUdaje) {
		return new ListinyDalsiUdajeOracleLoaderFileExporter(listinyDalsiUdaje,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObeslaniMfExporter(List<ObeslaniMf> obeslaniMf) {
		return new ObeslaniMfOracleLoaderFileExporter(obeslaniMf, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getObjektyRizeniExporter(List<ObjektyRizeni> objektyRizeni) {
		return new ObjektyRizeniOracleLoaderFileExporter(objektyRizeni,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getPredmetyRizeniExporter(
			List<PredmetyRizeni> predmetyRizeni) {
		return new PredmetyRizeniOracleLoaderFileExporter(predmetyRizeni,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getRizeniExporter(List<Rizeni> rizeni) {
		return new RizeniOracleLoaderFileExporter(rizeni, characterSet, output,  prefix);
	}

	@Override
	public Exporter getRizeniKuExporter(List<RizeniKu> rizeniKu) {
		return new RizeniKuOracleLoaderFileExporter(rizeniKu, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getRListExporter(List<RList> rList) {
		return new RListOracleLoaderFileExporter(rList, characterSet, output,  prefix);
	}

	@Override
	public Exporter getTListinExporter(List<TListin> tListin) {
		return new TListinOracleLoaderFileExporter(tListin, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getTPredmetuRExporter(List<TPredmetuR> tPredmetuR) {
		return new TPredmetuROracleLoaderFileExporter(tPredmetuR, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getTypyRizeniExporter(List<TypyRizeni> typyRizeni) {
		return new TypyRizeniOracleLoaderFileExporter(typyRizeni, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getTypyUcastnikuExporter(List<TypyUcastniku> typyUcastniku) {
		return new TypyUcastnikuOracleLoaderFileExporter(typyUcastniku,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getUcastniciExporter(List<Ucastnici> ucastnici) {
		return new UcastniciOracleLoaderFileExporter(ucastnici, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getUcastniciTypExporter(List<UcastniciTyp> ucastniciTyp) {
		return new UcastniciTypOracleLoaderFileExporter(ucastniciTyp,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getCharOsExporter(List<CharOs> charOs) {
		return new CharOsOracleLoaderFileExporter(charOs, characterSet, output,  prefix);
	}

	@Override
	public Exporter getOpravSubjektyExporter(List<OpravSubjekty> opravSubjekty) {
		return new OpravSubjektyOracleLoaderFileExporter(opravSubjekty,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getTelesaExporter(List<Telesa> telesa) {
		return new TelesaOracleLoaderFileExporter(telesa, characterSet, output,  prefix);
	}

	@Override
	public Exporter getVlastnictviExporter(List<Vlastnictvi> vlastnictvi) {
		return new VlastnictviOracleLoaderFileExporter(vlastnictvi,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getDalsiPrvkyMapyExporter(
			List<DalsiPrvkyMapy> dalsiPrvkyMapy) {
		return new DalsiPrvkyMapyOracleLoaderFileExporter(dalsiPrvkyMapy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getHraniceParcelExporter(List<HraniceParcel> hraniceParcel) {
		return new HraniceParcelOracleLoaderFileExporter(hraniceParcel,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getKodyCharQBoduExporter(List<KodyCharQBodu> kodyCharQBodu) {
		return new KodyCharQBoduOracleLoaderFileExporter(kodyCharQBodu,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObrazyBoduBpExporter(List<ObrazyBoduBp> obrazyBoduBp) {
		return new ObrazyBoduBpOracleLoaderFileExporter(obrazyBoduBp,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObrazyBudovExporter(List<ObrazyBudov> obrazyBudov) {
		return new ObrazyBudovOracleLoaderFileExporter(obrazyBudov,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObrazyParcelExporter(List<ObrazyParcel> obrazyParcel) {
		return new ObrazyParcelOracleLoaderFileExporter(obrazyParcel,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getPrvkyOMapyExporter(List<PrvkyOMapy> prvkyOMapy) {
		return new PrvkyOMapyOracleLoaderFileExporter(prvkyOMapy, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getSouradniceObrazuExporter(
			List<SouradniceObrazu> souradniceObrazu) {
		return new SouradniceObrazuOracleLoaderFileExporter(souradniceObrazu,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getSouradnicePolohyExporter(
			List<SouradnicePolohy> souradnicePolohy) {
		return new SouradnicePolohyOracleLoaderFileExporter(souradnicePolohy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getSpojeniBMapyExporter(List<SpojeniBMapy> spojeniBMapy) {
		return new SpojeniBMapyOracleLoaderFileExporter(spojeniBMapy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getSpojeniBPolohExporter(List<SpojeniBPoloh> spojeniBPoloh) {
		return new SpojeniBPolohOracleLoaderFileExporter(spojeniBPoloh,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getSpojeniPoMapyExporter(List<SpojeniPoMapy> spojeniPoMapy) {
		return new SpojeniPoMapyOracleLoaderFileExporter(spojeniPoMapy,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getTPrvkuPDatExporter(List<TPrvkuPDat> tPrvkuPDat) {
		return new TPrvkuPDatOracleLoaderFileExporter(tPrvkuPDat, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getTSouradSysExporter(List<TSouradSys> tSouradSys) {
		return new TSouradSysOracleLoaderFileExporter(tSouradSys, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getZobrazeniVbExporter(List<ZobrazeniVb> zobrazeniVb) {
		return new ZobrazeniVbOracleLoaderFileExporter(zobrazeniVb,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getHraniceBpejExporter(List<HraniceBpej> hraniceBpej) {
		return new HraniceBpejOracleLoaderFileExporter(hraniceBpej,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getOznaceniBpejExporter(List<OznaceniBpej> oznaceniBpej) {
		return new OznaceniBpejOracleLoaderFileExporter(oznaceniBpej,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getNavrhyZmenKmExporter(List<NavrhyZmenKm> navrhyZmenKm) {
		return new NavrhyZmenKmOracleLoaderFileExporter(navrhyZmenKm,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getNzZpmzExporter(List<NzZpmz> nzZpmz) {
		return new NzZpmzOracleLoaderFileExporter(nzZpmz, characterSet, output,  prefix);
	}

	@Override
	public Exporter getZpmzExporter(List<Zpmz> zpmz) {
		return new ZpmzOracleLoaderFileExporter(zpmz, characterSet, output,  prefix);
	}

	@Override
	public Exporter getDotcenaParCislaExporter(
			List<DotcenaParCisla> dotcenaParCisla) {
		return new DotcenaParCislaOracleLoaderFileExporter(dotcenaParCisla,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getDotHistParCislaExporter(
			List<DotHistParCisla> dotHistParCisla) {
		return new DotHistParCislaOracleLoaderFileExporter(dotHistParCisla,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getRezCislaPbppExporter(List<RezCislaPbpp> rezCislaPbpp) {
		return new RezCislaPbppOracleLoaderFileExporter(rezCislaPbpp,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getRezParcelniCislaExporter(
			List<RezParcelniCisla> rezParcelniCisla) {
		return new RezParcelniCislaOracleLoaderFileExporter(rezParcelniCisla,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getObrazyDefBoduExporter(List<ObrazyDefBodu> obrazyDefBodu) {
		return new ObrazyDefBoduOracleLoaderFileExporter(obrazyDefBodu,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getAdresaExporter(List<Adresa> adresa) {
		return new AdresaOracleLoaderFileExporter(adresa, characterSet, output,  prefix);
	}

	@Override
	public Exporter getBudObjExporter(List<BudObj> budObj) {
		return new BudObjOracleLoaderFileExporter(budObj, characterSet, output,  prefix);
	}

	@Override
	public Exporter getPravaStavbyExporter(List<PravaStavby> pravaStavby) {
		return new PravaStavbyOracleLoaderFileExporter(pravaStavby,
				characterSet, output,  prefix);
	}

	@Override
	public Exporter getRUcelNemExporter(List<RUcelNem> rUcelNem) {
		return new RUcelNemOracleLoaderFileExporter(rUcelNem, characterSet, output, 
				prefix);
	}

	@Override
	public Exporter getUcelExporter(List<Ucel> ucel) {
		return new UcelOracleLoaderFileExporter(ucel, characterSet, output,  prefix);
	}

	@Override
	public Exporter getRJpvExporter(List<RJpv> rJpv) {
		return new RJpvOracleLoaderFileExporter(rJpv, characterSet, output,  prefix);
	}
}
