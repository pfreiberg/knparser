package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.DalsiPrvkyMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiPrvkyMapyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "DALSI_PRVKY_MAPY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO "
			+ name
			+ "_MIN"
			+ " VALUES"
			+ "(SEQ_DALSI_PRVKY_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public DalsiPrvkyMapyOracleDatabaseJdbcExporter(
			List<DalsiPrvkyMapy> dalsiPrvkyMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(dalsiPrvkyMapy, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		DalsiPrvkyMapy record = (DalsiPrvkyMapy) rawRecord;
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
		psInsert.setObject(11, record.getText());
		psInsert.setObject(12, record.getVelikost());
		psInsert.setObject(13, record.getUhel());
		psInsert.setObject(14, record.getBpId());
		psInsert.setObject(15, record.getDpmType());
		psInsert.setObject(16, record.getVztaznyBod());
		psInsert.setObject(17, record.getKatuzeKod());
		psInsert.setNull(18, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		DalsiPrvkyMapy record = (DalsiPrvkyMapy) rawRecord;
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
		psHisInsert.setObject(11, record.getText());
		psHisInsert.setObject(12, record.getVelikost());
		psHisInsert.setObject(13, record.getUhel());
		psHisInsert.setObject(14, record.getBpId());
		psHisInsert.setObject(15, record.getDpmType());
		psHisInsert.setObject(16, record.getVztaznyBod());
		psHisInsert.setObject(17, record.getKatuzeKod());
		psHisInsert.setNull(18, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
