package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObjektyRizeniOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<ObjektyRizeni> objektyRizeni;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "OBJEKTY_RIZENI";

	public ObjektyRizeniOracleDatabaseJdbcExporter(
			List<ObjektyRizeni> objektyRizeni,
			ConnectionParameters connectionParameters) {
		this.objektyRizeni = objektyRizeni;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
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
			primaryKeysValues = getPrimaryKeysValues(record);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Object> getPrimaryKeysValues(Object record) {
		List<Object> primaryKeyValues = new ArrayList<>();
		try {
			for (int i = 0; i < methodsName.size(); i++) {
				Class<?> c = Class
						.forName("cz.pfreiberg.knparser.domain.rizeni.ObjektyRizeni");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((ObjektyRizeni) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(ObjektyRizeni record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (newFind(parameters, null, false)) {
			newDelete(parameters, null, false);
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
