package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CastiBudovOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "CASTI_BUDOV";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES" + "(SEQ_CASTI_BUDOV_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";

	public CastiBudovOracleDatabaseJdbcExporter(List<CastiBudov> castiBudov,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(castiBudov, name);
	}

	protected void insertRecord(Object rawRecord)
			throws SQLException {
		CastiBudov record = (CastiBudov) rawRecord;
		psInsert.setObject(1, 0);
		psInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(4, 0);
		psInsert.setObject(5, record.getRizeniIdVzniku());
		psInsert.setObject(6, record.getRizeniIdZaniku());
		psInsert.setObject(7, record.getBudId());
		psInsert.setObject(8, record.getTypbudKod());
		psInsert.setObject(9, record.getCisloDomovni());
		psInsert.setObject(10, record.getCenaNemovitosti());
	}

	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		CastiBudov record = (CastiBudov) rawRecord;
		psHisInsert.setObject(1, 0);
		psHisInsert.setObject(2,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(4, 0);
		psHisInsert.setObject(5, record.getRizeniIdVzniku());
		psHisInsert.setObject(6, record.getRizeniIdZaniku());
		psHisInsert.setObject(7, record.getBudId());
		psHisInsert.setObject(8, record.getTypbudKod());
		psHisInsert.setObject(9, record.getCisloDomovni());
		psHisInsert.setObject(10, record.getCenaNemovitosti());

	}

}
