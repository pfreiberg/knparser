package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JednotkyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "JEDNOTKY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_JEDNOTKY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public JednotkyOracleDatabaseJdbcExporter(List<Jednotky> jednotky,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(jednotky, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		Jednotky record = (Jednotky) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getBudId());
		psInsert.setObject(9, record.getTypjedKod());
		psInsert.setObject(10, record.getCisloJednotky());
		psInsert.setObject(11, record.getCenaNemovitosti());
		psInsert.setObject(12, record.getZpvyjeKod());
		psInsert.setObject(13, record.getTelId());
		psInsert.setObject(14, record.getPodilCitatel());
		psInsert.setObject(15, record.getPodilJmenovatel());
		psInsert.setObject(16, record.getPopis());
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		Jednotky record = (Jednotky) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getBudId());
		psHisInsert.setObject(9, record.getTypjedKod());
		psHisInsert.setObject(10, record.getCisloJednotky());
		psHisInsert.setObject(11, record.getCenaNemovitosti());
		psHisInsert.setObject(12, record.getZpvyjeKod());
		psHisInsert.setObject(13, record.getTelId());
		psHisInsert.setObject(14, record.getPodilCitatel());
		psHisInsert.setObject(15, record.getPodilJmenovatel());
		psHisInsert.setObject(16, record.getPopis());
	}

}
