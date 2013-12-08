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

public interface ExporterFactory {
	
	public Exporter getBonitDilyParcExporter(List<BonitDilyParc> bonitDilyParc);
	
	public Exporter getJednotkyExporter(List<Jednotky> jednotky);
	public Exporter getTJednotekExporter(List<TJednotek> tJednotek);
	public Exporter getZpVyuzitiJedExporter(List<ZpVyuzitiJed> zpVyuzitiJed);
	
	public Exporter getJinePravVztahyExporter(List<JinePravVztahy> jinePravVztahy);
	public Exporter getTPravnichVztExporter(List<TPravnichVzt> tPravnichVzt);

	public Exporter getParcelyExporter(List<Parcely> parcely);
	public Exporter getBudovyExporter(List<Budovy> budovy);
	public Exporter getCastiBudovExporter(List<CastiBudov> castiBudov);
	public Exporter getZpOchranyNemExporter(List<ZpOchranyNem> zpOchranyNem);
	public Exporter getDPozemkuExporter(List<DPozemku> dPozemku);
	public Exporter getZpVyuzitiPozExporter(List<ZpVyuzitiPoz> zpVyuzitiPoz);
	public Exporter getZdrojeParcelZeExporter(List<ZdrojeParcelZe> zdrojeParcelZe);
	public Exporter getZpUrceniVymeryExporter(List<ZpUrceniVymery> zpUrceniVymery);
	public Exporter getTBudovExporter(List<TBudov> tBudov);
	public Exporter getMapoveListyExporter(List<MapoveListy> mapoveListy);
	public Exporter getKatastrUzemiExporter(List<KatastrUzemi> katastrUzemi);
	public Exporter getObceExporter(List<Obce> obce);
	public Exporter getCastiObciExporter(List<CastiObci> castiObci);
	public Exporter getOkresyExporter(List<Okresy> okresy);
	public Exporter getKrajeExporter(List<Kraje> Kraje);
	public Exporter getNoveKrajeExporter(List<NoveKraje> noveKraje);
	public Exporter getRZpochrExporter(List<RZpochr> rZpochr);
	public Exporter getZpVyuzitiBudExporter(List<ZpVyuzitiBud> zpVyuzitiBud);
	
	public Exporter getAdresyExporter(List<Adresy> adresy);
	public Exporter getDalsiUdajeListinyExporter(List<DalsiUdajeListiny> dalsiUdajeListiny);
	public Exporter getListinyExporter(List<Listiny> listiny);
	public Exporter getListinyDalsiUdajeExporter(List<ListinyDalsiUdaje> listinyDalsiUdaje);
	public Exporter getObeslaniMfExporter(List<ObeslaniMf> obeslaniMf);
	public Exporter getObjektyRizeniExporter(List<ObjektyRizeni> objektyRizeni);
	public Exporter getPredmetyRizeniExporter(List<PredmetyRizeni> predmetyRizeni);
	public Exporter getRizeniExporter(List<Rizeni> rizeni);
	public Exporter getRizeniKuExporter(List<RizeniKu> rizeniKu);
	public Exporter getRListExporter(List<RList> rList);
	public Exporter getTListinExporter(List<TListin> tListin);
	public Exporter getTPredmetuRExporter(List<TPredmetuR> tPredmetuR);
	public Exporter getTypyRizeniExporter(List<TypyRizeni> typyRizeni);
	public Exporter getTypyUcastnikuExporter(List<TypyUcastniku> typyUcastniku);
	public Exporter getUcastniciExporter(List<Ucastnici> ucastnici);
	public Exporter getUcastniciTypExporter(List<UcastniciTyp> ucastniciTyp);
	
	public Exporter getCharOsExporter(List<CharOs> charOs);
	public Exporter getOpravSubjektyExporter(List<OpravSubjekty> opravSubjekty);
	public Exporter getTelesaExporter(List<Telesa> telesa);
	public Exporter getVlastnictviExporter(List<Vlastnictvi> vlastnictvi);


}