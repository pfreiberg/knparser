package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.OpravSubjekty;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OpravSubjektyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OPRAV_SUBJEKTY";

	public OpravSubjektyOracleDatabaseJdbcExporter(
			List<OpravSubjekty> opravSubjekty,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(opravSubjekty, name);
	}

	protected void insertRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			OpravSubjekty record = (OpravSubjekty) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getIdJe1PartnerBsm());
			preparedStatement.setObject(9, record.getIdJe2PartnerBsm());
			preparedStatement.setObject(10, record.getIdZdroj());
			preparedStatement.setObject(11, record.getOpsubType());
			preparedStatement.setObject(12, record.getCharosKod());
			preparedStatement.setObject(13, record.getIco());
			preparedStatement.setObject(14, record.getDoplnekIco());
			preparedStatement.setObject(15, record.getNazev());
			preparedStatement.setObject(16, record.getNazevU());
			preparedStatement.setObject(17, record.getRodneCislo());
			preparedStatement.setObject(18, record.getTitulPredJmenem());
			preparedStatement.setObject(19, record.getJmeno());
			preparedStatement.setObject(20, record.getJmenoU());
			preparedStatement.setObject(21, record.getPrijmeni());
			preparedStatement.setObject(22, record.getPrijmeniU());
			preparedStatement.setObject(23, record.getTitulZaJmenem());
			preparedStatement.setObject(24, record.getCisloDomovni());
			preparedStatement.setObject(25, record.getCisloOrientacni());
			preparedStatement.setObject(26, record.getNazevUlice());
			preparedStatement.setObject(27, record.getCastObce());
			preparedStatement.setObject(28, record.getObec());
			preparedStatement.setObject(29, record.getOkres());
			preparedStatement.setObject(30, record.getStat());
			preparedStatement.setObject(31, record.getPsc());
			preparedStatement.setObject(32, record.getCpCe());
			preparedStatement.setObject(33, record.getMestskaCast());
			preparedStatement.setObject(34,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(35, record.getRizeniIdVzniku2());
			preparedStatement.setObject(36, record.getKodAdrm());
			preparedStatement.setObject(37, record.getIdNadrizenePo());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	protected void insertHistoricalRecord(String table, Object rawRecord) throws SQLException {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(SEQ_OPRAV_SUBJEKTY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			OpravSubjekty record = (OpravSubjekty) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getIdJe1PartnerBsm());
			preparedStatement.setObject(9, record.getIdJe2PartnerBsm());
			preparedStatement.setObject(10, record.getIdZdroj());
			preparedStatement.setObject(11, record.getOpsubType());
			preparedStatement.setObject(12, record.getCharosKod());
			preparedStatement.setObject(13, record.getIco());
			preparedStatement.setObject(14, record.getDoplnekIco());
			preparedStatement.setObject(15, record.getNazev());
			preparedStatement.setObject(16, record.getNazevU());
			preparedStatement.setObject(17, record.getRodneCislo());
			preparedStatement.setObject(18, record.getTitulPredJmenem());
			preparedStatement.setObject(19, record.getJmeno());
			preparedStatement.setObject(20, record.getJmenoU());
			preparedStatement.setObject(21, record.getPrijmeni());
			preparedStatement.setObject(22, record.getPrijmeniU());
			preparedStatement.setObject(23, record.getTitulZaJmenem());
			preparedStatement.setObject(24, record.getCisloDomovni());
			preparedStatement.setObject(25, record.getCisloOrientacni());
			preparedStatement.setObject(26, record.getNazevUlice());
			preparedStatement.setObject(27, record.getCastObce());
			preparedStatement.setObject(28, record.getObec());
			preparedStatement.setObject(29, record.getOkres());
			preparedStatement.setObject(30, record.getStat());
			preparedStatement.setObject(31, record.getPsc());
			preparedStatement.setObject(32, record.getCpCe());
			preparedStatement.setObject(33, record.getMestskaCast());
			preparedStatement.setObject(34,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
			preparedStatement.setObject(35, record.getRizeniIdVzniku2());
			preparedStatement.setObject(36, record.getKodAdrm());
			preparedStatement.setObject(37, record.getIdNadrizenePo());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
