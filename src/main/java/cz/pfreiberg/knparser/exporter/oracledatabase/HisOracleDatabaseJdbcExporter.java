package cz.pfreiberg.knparser.exporter.oracledatabase;

import cz.pfreiberg.knparser.util.Operations;

public abstract class HisOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	protected void processRecord(OracleDatabaseParameters parameters, Object record) {
		
		if (find(parameters, Operations.lessThan, true)) {
			delete(parameters, Operations.lessThan, true);
			insert(parameters.getTable(), record, true);
		} else if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			insert(parameters.getTable(), record, true);
		}
	}
	
	protected void processHistoricalRecord(OracleDatabaseParameters parameters, Object record) {
		
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

}
