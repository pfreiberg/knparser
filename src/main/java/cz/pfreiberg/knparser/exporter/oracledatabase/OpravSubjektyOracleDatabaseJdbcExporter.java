package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OpravSubjektyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OPRAV_SUBJEKTY";
	private final static String insert = "INSERT INTO "
			+ name
			+ " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO "
			+ name
			+ "_MIN"
			+ " VALUES"
			+ "(SEQ_OPRAV_SUBJEKTY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public OpravSubjektyOracleDatabaseJdbcExporter(
			List<OpravSubjekty> opravSubjekty,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(opravSubjekty, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		OpravSubjekty record = (OpravSubjekty) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getIdJe1PartnerBsm());
		psInsert.setObject(9, record.getIdJe2PartnerBsm());
		psInsert.setObject(10, record.getIdZdroj());
		psInsert.setObject(11, record.getOpsubType());
		psInsert.setObject(12, record.getCharosKod());
		psInsert.setObject(13, record.getIco());
		psInsert.setObject(14, record.getDoplnekIco());
		psInsert.setObject(15, record.getNazev());
		psInsert.setObject(16, record.getNazevU());
		psInsert.setObject(17, record.getRodneCislo());
		psInsert.setObject(18, record.getTitulPredJmenem());
		psInsert.setObject(19, record.getJmeno());
		psInsert.setObject(20, record.getJmenoU());
		psInsert.setObject(21, record.getPrijmeni());
		psInsert.setObject(22, record.getPrijmeniU());
		psInsert.setObject(23, record.getTitulZaJmenem());
		psInsert.setObject(24, record.getCisloDomovni());
		psInsert.setObject(25, record.getCisloOrientacni());
		psInsert.setObject(26, record.getNazevUlice());
		psInsert.setObject(27, record.getCastObce());
		psInsert.setObject(28, record.getObec());
		psInsert.setObject(29, record.getOkres());
		psInsert.setObject(30, record.getStat());
		psInsert.setObject(31, record.getPsc());
		psInsert.setObject(32, record.getCpCe());
		psInsert.setObject(33, record.getMestskaCast());
		psInsert.setObject(34,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psInsert.setObject(35, record.getRizeniIdVzniku2());
		psInsert.setObject(36, record.getKodAdrm());
		psInsert.setObject(37, record.getIdNadrizenePo());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		OpravSubjekty record = (OpravSubjekty) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getIdJe1PartnerBsm());
		psHisInsert.setObject(9, record.getIdJe2PartnerBsm());
		psHisInsert.setObject(10, record.getIdZdroj());
		psHisInsert.setObject(11, record.getOpsubType());
		psHisInsert.setObject(12, record.getCharosKod());
		psHisInsert.setObject(13, record.getIco());
		psHisInsert.setObject(14, record.getDoplnekIco());
		psHisInsert.setObject(15, record.getNazev());
		psHisInsert.setObject(16, record.getNazevU());
		psHisInsert.setObject(17, record.getRodneCislo());
		psHisInsert.setObject(18, record.getTitulPredJmenem());
		psHisInsert.setObject(19, record.getJmeno());
		psHisInsert.setObject(20, record.getJmenoU());
		psHisInsert.setObject(21, record.getPrijmeni());
		psHisInsert.setObject(22, record.getPrijmeniU());
		psHisInsert.setObject(23, record.getTitulZaJmenem());
		psHisInsert.setObject(24, record.getCisloDomovni());
		psHisInsert.setObject(25, record.getCisloOrientacni());
		psHisInsert.setObject(26, record.getNazevUlice());
		psHisInsert.setObject(27, record.getCastObce());
		psHisInsert.setObject(28, record.getObec());
		psHisInsert.setObject(29, record.getOkres());
		psHisInsert.setObject(30, record.getStat());
		psHisInsert.setObject(31, record.getPsc());
		psHisInsert.setObject(32, record.getCpCe());
		psHisInsert.setObject(33, record.getMestskaCast());
		psHisInsert.setObject(34,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psHisInsert.setObject(35, record.getRizeniIdVzniku2());
		psHisInsert.setObject(36, record.getKodAdrm());
		psHisInsert.setObject(37, record.getIdNadrizenePo());
	}

}
