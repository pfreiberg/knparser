package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.TypyUcastniku;

public class TypyUcastnikuOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<TypyUcastniku> typyUcastniku;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "TYPY_UCASTNIKU";

	public TypyUcastnikuOracleDatabaseJdbcExporter(
			List<TypyUcastniku> typyUcastniku,
			ConnectionParameters connectionParameters) {
		this.typyUcastniku = typyUcastniku;
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
		for (TypyUcastniku record : typyUcastniku) {
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

	private void processRecord(TypyUcastniku record) {

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
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			TypyUcastniku record = (TypyUcastniku) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3, record.getPopis());
		
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
