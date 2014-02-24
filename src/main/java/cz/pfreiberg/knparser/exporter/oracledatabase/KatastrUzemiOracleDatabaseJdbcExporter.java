package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KatastrUzemiOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<KatastrUzemi> katastrUzemi;
	private final static String name = "KATASTR_UZEMI";

	public KatastrUzemiOracleDatabaseJdbcExporter(
			List<KatastrUzemi> katastrUzemi,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.katastrUzemi = katastrUzemi;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (KatastrUzemi record : katastrUzemi) {
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

	private void processRecord(KatastrUzemi record) {

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
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			KatastrUzemi record = (KatastrUzemi) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getObceKod());
			preparedStatement.setObject(3, record.getNazev());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setNull(6, Types.DECIMAL);
			// TODO IS_DKN

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
