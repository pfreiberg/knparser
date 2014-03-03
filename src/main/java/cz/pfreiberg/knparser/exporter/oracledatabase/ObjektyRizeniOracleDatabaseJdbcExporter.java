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
		preparedStatement.setObject(1, record.getId());
		preparedStatement.setObject(2, record.getRizeniId());
		preparedStatement.setObject(3, record.getParId());
		preparedStatement.setObject(4, record.getBudId());
		preparedStatement.setObject(5, record.getJedId());
		preparedStatement.setObject(6,
				VfkUtil.convertToDatabaseDate(record.getDatumPlomby()));
		preparedStatement.setObject(7, VfkUtil.convertToDatabaseDate(record
				.getDatumOdstraneniPlomby()));
		preparedStatement.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getDatumHistOd()));
		preparedStatement.setObject(9,
				VfkUtil.convertToDatabaseDate(record.getDatumHistDo()));
		preparedStatement.setObject(10, record.getPsId());
	}

}
