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

	protected final PreparedStatement psInsert;
	protected final PreparedStatement psHisInsert;

	private enum LastInserted {
		record(), hisRecord(), nothing();
	}

	public HisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name,
			String insert, String hisInsert) {
		super(connectionParameters, name);
		PreparedStatement tempPsInsert = null;
		PreparedStatement tempHisPsInsert = null;
		try {
			tempPsInsert = connection.prepareStatement(insert);
			tempHisPsInsert = connection.prepareStatement(hisInsert);
		} catch (SQLException e) {
			log.error("Error during initializing prepared statement for "
					+ name + ".");
			log.debug("Stack trace:", e);
		}
		psInsert = tempPsInsert;
		psHisInsert = tempHisPsInsert;
	}

	protected final <T extends DomainWithDate> void prepareStatement(
			List<T> list, String name) {
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
				}
			}

			if (lastInserted == LastInserted.record)
				executeBatch(psInsert);
			else
				executeBatch(psHisInsert);
			connection.commit();
		} catch (SQLException e) {
			log.error("Error during commiting batch in table " + name + ".");
			log.debug("Stack trace: " + e);
		} finally {
			closePreparedStatement(psInsert);
			closePreparedStatement(psHisInsert);
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
	public final void insert(String table, Object rawRecord, boolean isRecord)
			throws JdbcException {
		try {
			if (isRecord) {
				insertRecord(rawRecord);
				addToBatch(psInsert);
			} else {
				insertHistoricalRecord(rawRecord);
				addToBatch(psHisInsert);
			}
		} catch (SQLException e) {
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during inserting "
					+ LogUtil.getHisMethodWhichThrowsException(e) + " in "
					+ LogUtil.getClassWhichThrowsException(e) + "." + "\n"
					+ rawRecord.toString());
		}
	}

	protected abstract void insertRecord(Object rawRecord) throws SQLException;

	protected abstract void insertHistoricalRecord(Object rawRecord)
			throws SQLException;

}
