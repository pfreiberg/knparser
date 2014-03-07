package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.PravaStavby;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PravaStavbyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "PRAVA_STAVBY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES" + "(SEQ_PRAVA_STAVBY_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";

	public PravaStavbyOracleDatabaseJdbcExporter(List<PravaStavby> pravaStavby,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(pravaStavby, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		PravaStavby record = (PravaStavby) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getDatumPrijeti()));
		psInsert.setObject(9, record.getTelId());
		psInsert.setObject(10,
				VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		PravaStavby record = (PravaStavby) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getDatumPrijeti()));
		psHisInsert.setObject(9, record.getTelId());
		psHisInsert.setObject(10,
				VfkUtil.convertToDatabaseDate(record.getDatumUkonceni()));
	}

}
