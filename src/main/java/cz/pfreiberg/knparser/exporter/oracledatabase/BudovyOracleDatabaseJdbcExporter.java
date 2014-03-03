package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Budovy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class BudovyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "BUDOVY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_BUDOVY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public BudovyOracleDatabaseJdbcExporter(List<Budovy> budovy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(budovy, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		Budovy record = (Budovy) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getTypbudKod());
		psInsert.setObject(9, record.getCaobceKod());
		psInsert.setObject(10, record.getCisloDomovni());
		psInsert.setObject(11, record.getCenaNemovitosti());
		psInsert.setObject(12, record.getZpvybuKod());
		psInsert.setObject(13, record.getTelId());
		psInsert.setObject(14, record.getDocasnaStavba());
		psInsert.setObject(15, record.getJeSoucasti());
		psInsert.setObject(16, record.getPsId());
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		Budovy record = (Budovy) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getTypbudKod());
		psHisInsert.setObject(9, record.getCaobceKod());
		psHisInsert.setObject(10, record.getCisloDomovni());
		psHisInsert.setObject(11, record.getCenaNemovitosti());
		psHisInsert.setObject(12, record.getZpvybuKod());
		psHisInsert.setObject(13, record.getTelId());
		psHisInsert.setObject(14, record.getDocasnaStavba());
		psHisInsert.setObject(15, record.getJeSoucasti());
		psHisInsert.setObject(16, record.getPsId());
	}

}
