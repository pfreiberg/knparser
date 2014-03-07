package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBPoloh;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBPolohOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "SPOJENI_B_POLOH";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_SPOJENI_B_POLOH_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public SpojeniBPolohOracleDatabaseJdbcExporter(
			List<SpojeniBPoloh> spojeniBPoloh,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(spojeniBPoloh, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		SpojeniBPoloh record = (SpojeniBPoloh) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getBpId());
		psInsert.setObject(9, record.getPoradoveCisloBodu());
		psInsert.setObject(10, record.getObId());
		psInsert.setObject(11, record.getHpId());
		psInsert.setObject(12, record.getDpmId());
		psInsert.setObject(13, record.getParametrySpojeni());
		psInsert.setObject(14, record.getZvbId());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		SpojeniBPoloh record = (SpojeniBPoloh) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getBpId());
		psHisInsert.setObject(9, record.getPoradoveCisloBodu());
		psHisInsert.setObject(10, record.getObId());
		psHisInsert.setObject(11, record.getHpId());
		psHisInsert.setObject(12, record.getDpmId());
		psHisInsert.setObject(13, record.getParametrySpojeni());
		psHisInsert.setObject(14, record.getZvbId());
	}

}
