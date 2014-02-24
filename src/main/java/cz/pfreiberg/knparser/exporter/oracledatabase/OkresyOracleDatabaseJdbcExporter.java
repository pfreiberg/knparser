package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.Okresy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OkresyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Okresy> okresy;
	private final static String name = "OKRESY";

	public OkresyOracleDatabaseJdbcExporter(List<Okresy> okresy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.okresy = okresy;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Okresy record : okresy) {
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

	private void processRecord(Okresy record) {

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
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Okresy record = (Okresy) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getKrajeKod());
			preparedStatement.setObject(3, record.getNazev());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(6, record.getNuts4());
			preparedStatement.setObject(7, record.getNkrajeKod());

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
