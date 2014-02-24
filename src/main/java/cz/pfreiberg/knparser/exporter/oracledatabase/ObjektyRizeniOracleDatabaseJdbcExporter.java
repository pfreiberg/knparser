package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObjektyRizeniOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<ObjektyRizeni> objektyRizeni;
	private final static String name = "OBJEKTY_RIZENI";

	public ObjektyRizeniOracleDatabaseJdbcExporter(
			List<ObjektyRizeni> objektyRizeni,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.objektyRizeni = objektyRizeni;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ObjektyRizeni record : objektyRizeni) {
			primaryKeysValues = getPrimaryKeysValues(record, methodsName);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRecord(ObjektyRizeni record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(name, record, false);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

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

			preparedStatement.executeUpdate();
			preparedStatement.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
