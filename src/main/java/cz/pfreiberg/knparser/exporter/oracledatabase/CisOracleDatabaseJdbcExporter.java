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
			ConnectionParameters connectionParameters, String name, String insert) {
		super(connectionParameters, name, insert);
	}
	
}
