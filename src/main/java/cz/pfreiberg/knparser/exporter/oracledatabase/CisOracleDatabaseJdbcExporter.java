package cz.pfreiberg.knparser.exporter.oracledatabase;

import cz.pfreiberg.knparser.ConnectionParameters;

public abstract class CisOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	public CisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		super(connectionParameters, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		// TODO Auto-generated method stub

	}

}
