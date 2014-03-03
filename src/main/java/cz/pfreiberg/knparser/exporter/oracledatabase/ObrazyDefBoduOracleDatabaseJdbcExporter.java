package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.definicnibody.ObrazyDefBodu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyDefBoduOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OBRAZY_DEF_BODU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_OBRAZY_DEF_BODU_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";

	public ObrazyDefBoduOracleDatabaseJdbcExporter(
			List<ObrazyDefBodu> obrazyDefBodu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(obrazyDefBodu, name);
	}

	protected void insertRecord(String table, Object rawRecord)
			throws SQLException {
		ObrazyDefBodu record = (ObrazyDefBodu) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getParId());
		psInsert.setObject(7, record.getBudId());
		psInsert.setObject(8, record.getTypbudKod());
		psInsert.setObject(9, record.getCisloDomovni());
		psInsert.setObject(10, record.getSouradniceY());
		psInsert.setObject(11, record.getSouradniceX());
		psInsert.setNull(12, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

	protected void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		ObrazyDefBodu record = (ObrazyDefBodu) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getParId());
		psHisInsert.setObject(7, record.getBudId());
		psHisInsert.setObject(8, record.getTypbudKod());
		psHisInsert.setObject(9, record.getCisloDomovni());
		psHisInsert.setObject(10, record.getSouradniceY());
		psHisInsert.setObject(11, record.getSouradniceX());
		psHisInsert.setNull(12, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
