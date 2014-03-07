package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RJpvOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "R_JPV";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES" + "(SEQ_R_JPV_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";

	public RJpvOracleDatabaseJdbcExporter(List<RJpv> rJpv,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(rJpv, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		RJpv record = (RJpv) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getVerze());
		psInsert.setObject(3, 0);
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getHjpvId1());
		psInsert.setObject(9, record.getHjpvId2());
		psInsert.setObject(10, record.getTypvazbyJpv());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		RJpv record = (RJpv) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, record.getVerze());
		psHisInsert.setObject(3, 0);
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getHjpvId1());
		psHisInsert.setObject(9, record.getHjpvId2());
		psHisInsert.setObject(10, record.getTypvazbyJpv());
	}
}
