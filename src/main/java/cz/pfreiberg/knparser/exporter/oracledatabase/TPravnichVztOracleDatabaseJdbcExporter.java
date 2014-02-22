package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<TPravnichVzt> tPravnichVzt;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "T_PRAVNICH_VZT";

	public TPravnichVztOracleDatabaseJdbcExporter(
			List<TPravnichVzt> tPravnichVzt,
			ConnectionParameters connectionParameters) {
		this.tPravnichVzt = tPravnichVzt;
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
		for (TPravnichVzt record : tPravnichVzt) {
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

	private void processRecord(TPravnichVzt record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
			insert(name, record, false);
		} else {
			insert(name, record, false);
		}
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			TPravnichVzt record = (TPravnichVzt) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getTprKod());
			preparedStatement.setObject(3, record.getNazev());
			preparedStatement.setObject(4, record.getVlastnictvi());
			preparedStatement.setObject(5, record.getProOs());
			preparedStatement.setObject(6, record.getProNemovitost());
			preparedStatement.setObject(7, record.getKNemovitosti());
			preparedStatement.setObject(8,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(9, record.getSekce());
			preparedStatement.setObject(10,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(11, record.getVlvztah());
			preparedStatement.setObject(12, record.getKOs());
			preparedStatement.setObject(13, record.getPodilVeritele());
			preparedStatement.setObject(14, record.getPoradi());

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
