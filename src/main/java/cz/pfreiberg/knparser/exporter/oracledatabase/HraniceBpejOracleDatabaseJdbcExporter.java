package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.HraniceBpej;
import cz.pfreiberg.knparser.util.VfkUtil;

public class HraniceBpejOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "HRANICE_BPEJ";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_HRANICE_BPEJ_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?)";

	public HraniceBpejOracleDatabaseJdbcExporter(List<HraniceBpej> hraniceBpej,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(hraniceBpej, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		HraniceBpej record = (HraniceBpej) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getTypppdKod());
		psInsert.setObject(9, record.getBpejKodHranice1());
		psInsert.setObject(10, record.getBpejKodHranice2());
		psInsert.setObject(11, record.getKatuzeKod());
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		HraniceBpej record = (HraniceBpej) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getTypppdKod());
		psHisInsert.setObject(9, record.getBpejKodHranice1());
		psHisInsert.setObject(10, record.getBpejKodHranice2());
		psHisInsert.setObject(11, record.getKatuzeKod());
	}
}
