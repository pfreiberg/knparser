package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.DomainWithDate;
import cz.pfreiberg.knparser.util.LogUtil;
import cz.pfreiberg.knparser.util.Operations;

public abstract class HisOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private static final Logger log = Logger
			.getLogger(HisOracleDatabaseJdbcExporter.class);

	public HisOracleDatabaseJdbcExporter(
			ConnectionParameters connectionParameters, String name) {
		super(connectionParameters, name);
	}

	protected <T extends DomainWithDate> void prepareStatement(List<T> list,
			String name) {
		try {
			connection.setAutoCommit(false);
			for (T record : list) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);
				OracleDatabaseParameters parameters = new OracleDatabaseParameters(
						name, "DATUM_VZNIKU", record.getDatumVzniku());
				try {
					if (record.getDatumZaniku() == null) {
						processRecord(parameters, record);
					} else {
						processHistoricalRecord(parameters, record);
					}
				} catch (JdbcException e) {
					log.error(e.getMessage());
				}
			}
			connection.commit();
		} catch (SQLException e) {
			String stackTrace = e.getStackTrace()[0].toString();
			log.error("Error during commiting batch in "
					+ LogUtil.getClassWhichThrowsException(stackTrace) + ".");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error during closing connection.");
			}
		}
	}

	private void processRecord(OracleDatabaseParameters parameters,
			Object record) throws JdbcException {

		if (find(parameters, Operations.lessThan, true)) {
			delete(parameters, Operations.lessThan, true);
			insert(parameters.getTable(), record, true);
		} else if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
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
			if (find(parameters, Operations.equal, true)) {
				delete(parameters, Operations.equal, true);
			}
		}

	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws JdbcException {
		try {
			if (isRecord) {
				insertRecord(table, rawRecord);
			} else
				insertHistoricalRecord(table, rawRecord);
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
