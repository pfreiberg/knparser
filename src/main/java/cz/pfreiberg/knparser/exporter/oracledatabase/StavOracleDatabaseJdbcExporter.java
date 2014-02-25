package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;

public abstract class StavOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	public StavOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		super(connectionParameters, name);
	}

	protected <T> void prepareStatement(List<T> list, String name) {
		try {
			connection.setAutoCommit(false);
			for (T record : list) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);
				OracleDatabaseParameters parameters = new OracleDatabaseParameters(
						connection, name, primaryKeys, primaryKeysValues, null,
						null);
				processRecord(parameters, record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRecord(OracleDatabaseParameters parameters,
			Object record) {
		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(parameters.getTable(), record, false);
	}

}
