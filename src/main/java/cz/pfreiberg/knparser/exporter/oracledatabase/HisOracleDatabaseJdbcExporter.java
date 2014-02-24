package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.Operations;

public abstract class HisOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	public HisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		super(connectionParameters, name);
	}
	
	protected <T extends DomainWithDate> void prepareStatement(List<T> list, String name) {
		try {
			connection.setAutoCommit(false);
			for (T record : list) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);
				OracleDatabaseParameters parameters = new OracleDatabaseParameters(
						connection, name, primaryKeys, primaryKeysValues,
						"DATUM_VZNIKU", record.getDatumVzniku());
				if (record.getDatumZaniku() == null) {
					processRecord(parameters, record);
				} else {
					processHistoricalRecord(parameters, record);
				}
			}
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRecord(OracleDatabaseParameters parameters,
			Object record) {
		
		if (find(parameters, Operations.lessThan, true)) {
			delete(parameters, Operations.lessThan, true);
			insert(parameters.getTable(), record, true);
		} else if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			insert(parameters.getTable(), record, true);
		}
		
	}

	protected void processHistoricalRecord(OracleDatabaseParameters parameters,
			Object record) {

		String table = parameters.getTable();
		parameters.setTable(table + "_MIN");

		if (!find(parameters, Operations.equal, true)) {
			insert(parameters.getTable(), record, false);
			parameters.setTable(table);
			if (find(parameters, Operations.equal, true)) {
				delete(parameters, Operations.equal, true);
			}
		}

	}
	
	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		if (isRecord) {
			insertRecord(table, rawRecord);
		} else
			insertHistoricalRecord(table, rawRecord);
	}

	protected abstract void insertRecord(String table, Object rawRecord);

	protected abstract void insertHistoricalRecord(String table,
			Object rawRecord);


}
