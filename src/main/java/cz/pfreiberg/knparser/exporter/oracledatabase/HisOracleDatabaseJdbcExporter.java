package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.LogUtil;
import cz.pfreiberg.knparser.util.Operations;

/**
 * Abstraktní třída poskytující logiku pro historizační tabulky.
 * 
 * @author Petr Freiberg (freibergp@gmail.com)
 * 
 */
public abstract class HisOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private static final Logger log = Logger
			.getLogger(HisOracleDatabaseJdbcExporter.class);

	protected PreparedStatement psInsert;
	protected PreparedStatement psHisInsert;

	private enum LastInserted {
		record(), hisRecord(), nothing();
	}

	public HisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name,
			String insert, String hisInsert) {
		super(connectionParameters, name);
		try {
			psInsert = connection.prepareStatement(insert);
			psHisInsert = connection.prepareStatement(hisInsert);
		} catch (SQLException e) {
			log.error("Error during initializing prepared statement for "
					+ name);
			log.debug("Stack trace:", e);
		}
	}

	protected <T extends DomainWithDate> void prepareStatement(List<T> list,
			String name) {
		try {
			connection.setAutoCommit(false);
			LastInserted lastInserted = LastInserted.nothing;
			for (T record : list) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);
				OracleDatabaseParameters parameters = new OracleDatabaseParameters(
						name, "DATUM_VZNIKU", record.getDatumVzniku());
				try {
					if (record.getDatumZaniku() == null) {

						if (lastInserted != LastInserted.record)
							executeBatch(psHisInsert);

						processRecord(parameters, record);
						lastInserted = LastInserted.record;
					} else {

						if (lastInserted != LastInserted.hisRecord)
							executeBatch(psInsert);

						processHistoricalRecord(parameters, record);
						lastInserted = LastInserted.hisRecord;
					}
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

		if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			delete(parameters, Operations.lessThan, true);
			insert(parameters.getTable(), record, true);
		}

	}

	private void processHistoricalRecord(OracleDatabaseParameters parameters,
			Object record) throws JdbcException {

		String table = parameters.getTable();
		parameters.setTable(table + "_MIN");

		if (!find(parameters, Operations.equal, true)) {
			insert(parameters.getTable(), record, false);
			parameters.setTable(table);
			delete(parameters, Operations.equal, true);
		}

	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws JdbcException {
		try {
			if (isRecord) {
				insertRecord(table, rawRecord);
				addToBatch(psInsert);
			} else {
				insertHistoricalRecord(table, rawRecord);
				addToBatch(psHisInsert);
			}
		} catch (SQLException e) {
			String stackTrace = e.getStackTrace()[0].toString();
			throw new JdbcException("Error during inserting "
					+ LogUtil.getMethodWhichThrowsException(stackTrace)
					+ " in " + LogUtil.getClassWhichThrowsException(stackTrace)
					+ "." + "\n" + rawRecord.toString());
		}
	}

	protected abstract void insertRecord(String table, Object rawRecord)
			throws SQLException;

	protected abstract void insertHistoricalRecord(String table,
			Object rawRecord) throws SQLException;

}
