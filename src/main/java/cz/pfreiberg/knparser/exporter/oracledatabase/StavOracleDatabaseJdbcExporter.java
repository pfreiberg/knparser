package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.util.LogUtil;

/**
 * Abstraktní třída poskytující logiku pro stavové tabulky.
 *
 * @author Petr Freiberg (freibergp@gmail.com)
 *
 */
public abstract class StavOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private static final Logger log = Logger
			.getLogger(StavOracleDatabaseJdbcExporter.class);

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
						name, null, null);
				try {
					processRecord(parameters, record);
				} catch (JdbcException e) {
					log.error(e.getMessage());
					log.debug("Stack trace:", e);
				}
			}
			connection.commit();
		} catch (SQLException e) {
			String stackTrace = e.getStackTrace()[0].toString();
			log.error("Error during commiting batch in "
					+ LogUtil.getClassWhichThrowsException(stackTrace) + ".");
		} finally {
			closeConnection(connection);
		}
	}

	private void processRecord(OracleDatabaseParameters parameters,
			Object record) throws JdbcException {
		delete(parameters, null, false);
		try {
			insert(parameters.getTable(), record, false);
		} catch (SQLException e) {
			String stackTrace = e.getStackTrace()[0].toString();
			throw new JdbcException("Error during inserting record in "
					+ LogUtil.getClassWhichThrowsException(stackTrace) + "."
					+ "\n" + record.toString());
		}
	}

}
