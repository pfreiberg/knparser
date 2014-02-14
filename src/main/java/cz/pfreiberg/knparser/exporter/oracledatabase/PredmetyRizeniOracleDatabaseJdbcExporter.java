package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni;

public class PredmetyRizeniOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<PredmetyRizeni> predmetyRizeni;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "PREDMETY_RIZENI";

	public PredmetyRizeniOracleDatabaseJdbcExporter(
			List<PredmetyRizeni> predmetyRizeni,
			ConnectionParameters connectionParameters) {
		this.predmetyRizeni = predmetyRizeni;
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
		for (PredmetyRizeni record : predmetyRizeni) {
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
						.forName("cz.pfreiberg.knparser.domain.rizeni.PredmetyRizeni");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((PredmetyRizeni) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(PredmetyRizeni record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (newFind(parameters, null, false)) {
			newDelete(parameters, null, false);
		}
		insert(name, record, false);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			PredmetyRizeni record = (PredmetyRizeni) rawRecord;
			preparedStatement.setObject(1, record.getRizeniId());
			preparedStatement.setObject(2, record.getTyppreKod());

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
