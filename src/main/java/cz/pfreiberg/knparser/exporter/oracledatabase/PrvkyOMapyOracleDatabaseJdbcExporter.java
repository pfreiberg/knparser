package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PrvkyOMapyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "PRVKY_O_MAPY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_PRVKY_O_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public PrvkyOMapyOracleDatabaseJdbcExporter(List<PrvkyOMapy> prvkyOMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(prvkyOMapy, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		PrvkyOMapy record = (PrvkyOMapy) rawRecord;
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
		psInsert.setObject(9, record.getText());
		psInsert.setObject(10, record.getVelikost());
		psInsert.setObject(11, record.getUhel());
		psInsert.setObject(12, record.getVztaznyBod());
		psInsert.setObject(13, record.getKatuzeKod());
		psInsert.setNull(14, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		PrvkyOMapy record = (PrvkyOMapy) rawRecord;
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
		psHisInsert.setObject(9, record.getText());
		psHisInsert.setObject(10, record.getVelikost());
		psHisInsert.setObject(11, record.getUhel());
		psHisInsert.setObject(12, record.getVztaznyBod());
		psHisInsert.setObject(13, record.getKatuzeKod());
		psHisInsert.setNull(14, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
