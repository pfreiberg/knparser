package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.RList;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RListOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "R_LIST";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_R_LIST_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public RListOracleDatabaseJdbcExporter(List<RList> rList,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(rList, name);
	}

	protected void insertRecord(Object rawRecord)
			throws SQLException {
		RList record = (RList) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getListinId());
		psInsert.setObject(9, record.getParId());
		psInsert.setObject(10, record.getBudId());
		psInsert.setObject(11, record.getJedId());
		psInsert.setObject(12, record.getOpsubId());
		psInsert.setObject(13, record.getJpvId());
		psInsert.setObject(14,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psInsert.setObject(15, record.getRizeniIdVzniku2());
		psInsert.setObject(16, record.getPsId());
	}

	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		RList record = (RList) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getListinId());
		psHisInsert.setObject(9, record.getParId());
		psHisInsert.setObject(10, record.getBudId());
		psHisInsert.setObject(11, record.getJedId());
		psHisInsert.setObject(12, record.getOpsubId());
		psHisInsert.setObject(13, record.getJpvId());
		psHisInsert.setObject(14,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psHisInsert.setObject(15, record.getRizeniIdVzniku2());
		psHisInsert.setObject(16, record.getPsId());
	}

}
