package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
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

	protected final PreparedStatement psInsert;

	public StavOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name,
			String insert) {
		super(connectionParameters, name);
		PreparedStatement tempPsInsert = null;
		try {
			tempPsInsert = connection.prepareStatement(insert);
		} catch (SQLException e) {
			log.error("Error during initializing prepared statement for "
					+ name + ".");
			log.debug("Stack trace:", e);
		}
		psInsert = tempPsInsert;
	}

	protected final <T> void prepareStatement(List<T> list, String name) {
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
				}
			}
			executeBatch(psInsert);
			connection.commit();
		} catch (SQLException e) {
			log.error("Error during commiting batch in table " + name + ".");
			log.debug("Stack trace: " + e);
		} finally {
			closePreparedStatement(psInsert);
			closeConnection(connection);
		}
	}

	private void processRecord(OracleDatabaseParameters parameters,
			Object record) throws JdbcException {
		delete(parameters, null, false);
		try {
			insert(parameters.getTable(), record, false);
			addToBatch(psInsert);
		} catch (SQLException e) {
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during inserting record in "
					+ LogUtil.getClassWhichThrowsException(e) + "."
					+ "\n" + record.toString());
		}
	}

}
