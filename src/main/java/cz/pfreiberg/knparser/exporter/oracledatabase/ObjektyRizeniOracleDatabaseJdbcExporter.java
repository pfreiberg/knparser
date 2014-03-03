package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObjektyRizeniOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "OBJEKTY_RIZENI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";

	public ObjektyRizeniOracleDatabaseJdbcExporter(
			List<ObjektyRizeni> objektyRizeni,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(objektyRizeni, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ObjektyRizeni record = (ObjektyRizeni) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getRizeniId());
		psInsert.setObject(3, record.getParId());
		psInsert.setObject(4, record.getBudId());
		psInsert.setObject(5, record.getJedId());
		psInsert.setObject(6,
				VfkUtil.convertToDatabaseDate(record.getDatumPlomby()));
		psInsert.setObject(7, VfkUtil.convertToDatabaseDate(record
				.getDatumOdstraneniPlomby()));
		psInsert.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getDatumHistOd()));
		psInsert.setObject(9,
				VfkUtil.convertToDatabaseDate(record.getDatumHistDo()));
		psInsert.setObject(10, record.getPsId());
	}

}
