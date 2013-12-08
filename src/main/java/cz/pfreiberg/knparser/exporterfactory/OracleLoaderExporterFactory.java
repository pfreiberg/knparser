package cz.pfreiberg.knparser.exporterfactory;

import java.util.List;

import cz.pfreiberg.knparser.domain.bonitnidilparcely.BonitDilyParc;
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
import cz.pfreiberg.knparser.exporter.oracleloaderfile.BudovyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.CastiObciOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.DPozemkuOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KatastrUzemiOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.KrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.MapoveListyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.NoveKrajeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ObceOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.OkresyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ParcelyOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.RZpochrOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.TBudovOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZdrojeParcelZeOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpOchranyNemOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpUrceniVymeryOracleLoaderFileExporter;
import cz.pfreiberg.knparser.exporter.oracleloaderfile.ZpVyuzitiBudOracleLoaderFileExporter;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getJednotkyExporter(List<Jednotky> jednotky) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt) {
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

}
