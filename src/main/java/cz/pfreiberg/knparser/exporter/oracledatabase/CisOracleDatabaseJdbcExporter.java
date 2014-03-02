package cz.pfreiberg.knparser.exporter.oracledatabase;

import cz.pfreiberg.knparser.ConnectionParameters;

/**
 * Abstraktní třída poskytující logiku pro číselníky.
 *
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public abstract class CisOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	public CisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		super(connectionParameters, name);
	}
	
}
