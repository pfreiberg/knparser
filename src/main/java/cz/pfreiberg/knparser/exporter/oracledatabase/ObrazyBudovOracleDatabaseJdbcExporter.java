package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyBudovOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OBRAZY_BUDOV";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_OBRAZY_BUDOV_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public ObrazyBudovOracleDatabaseJdbcExporter(List<ObrazyBudov> obrazyBudov,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(obrazyBudov, name);
	}

	@Override
	protected void insertRecord(Object rawRecord)
			throws SQLException {
		ObrazyBudov record = (ObrazyBudov) rawRecord;
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
		psInsert.setObject(9, record.getSouradniceY());
		psInsert.setObject(10, record.getSouradniceX());
		psInsert.setObject(11, record.getVelikost());
		psInsert.setObject(12, record.getUhel());
		psInsert.setObject(13, record.getBudId());
		psInsert.setObject(14, record.getObrbudType());
	}

	@Override
	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		ObrazyBudov record = (ObrazyBudov) rawRecord;
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
		psHisInsert.setObject(9, record.getSouradniceY());
		psHisInsert.setObject(10, record.getSouradniceX());
		psHisInsert.setObject(11, record.getVelikost());
		psHisInsert.setObject(12, record.getUhel());
		psHisInsert.setObject(13, record.getBudId());
		psHisInsert.setObject(14, record.getObrbudType());
	}

}
