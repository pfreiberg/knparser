package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.vlastnictvi.Vlastnictvi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class VlastnictviOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "VLASTNICTVI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO "
			+ name
			+ "_MIN"
			+ " VALUES"
			+ "(SEQ_VLASTNICTVI_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public VlastnictviOracleDatabaseJdbcExporter(List<Vlastnictvi> vlastnictvi,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(vlastnictvi, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		Vlastnictvi record = (Vlastnictvi) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getOpsubId());
		psInsert.setObject(9, record.getTypravKod());
		psInsert.setObject(10, record.getTelId());
		psInsert.setObject(11, record.getPodilCitatel());
		psInsert.setObject(12, record.getPodilJmenovatel());
		psInsert.setObject(13,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psInsert.setObject(14, record.getRizeniIdVzniku2());
		psInsert.setObject(15, record.getParId());
		psInsert.setObject(16, record.getBudId());
		psInsert.setObject(17, record.getJedId());
		psInsert.setObject(18, record.getPsId());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		Vlastnictvi record = (Vlastnictvi) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getOpsubId());
		psHisInsert.setObject(9, record.getTypravKod());
		psHisInsert.setObject(10, record.getTelId());
		psHisInsert.setObject(11, record.getPodilCitatel());
		psHisInsert.setObject(12, record.getPodilJmenovatel());
		psHisInsert.setObject(13,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku2()));
		psHisInsert.setObject(14, record.getRizeniIdVzniku2());
		psHisInsert.setObject(15, record.getParId());
		psHisInsert.setObject(16, record.getBudId());
		psHisInsert.setObject(17, record.getJedId());
		psHisInsert.setObject(18, record.getPsId());
	}

}
