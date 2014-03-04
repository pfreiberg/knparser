package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.util.LogUtil;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private static final Logger log = Logger
			.getLogger(SpojeniBMapyOracleDatabaseJdbcExporter.class);

	private List<SpojeniBMapy> spojeniBMapy;
	private final static String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleDatabaseJdbcExporter(
			List<SpojeniBMapy> spojeniBMapy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters);
		this.spojeniBMapy = spojeniBMapy;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (SpojeniBMapy record : spojeniBMapy) {
				getActualValues(record);

				try {
					if (record.getDatumZaniku() == null) {
						processRecord(record);
					} else {
						processHistoricalRecord(record);
					}
				} catch (JdbcException e) {
					log.error(e.getMessage());
				}
			}
			connection.commit();
		} catch (SQLException e) {
			log.error("Error during commiting batch in table " + name + ".");
			log.debug("Stack trace: " + e);
		} finally {
			closeConnection(connection);
		}
	}

	private void getActualValues(SpojeniBMapy record) {
		List<String> actualPrimaryKeys = new ArrayList<>();
		List<Object> actualPrimaryKeysValues = new ArrayList<>();
		if (record.getOpId() != null) {
			actualPrimaryKeys.add("OP_ID");
			actualPrimaryKeysValues.add(record.getOpId());
		} else if (record.getDpmId() != null) {
			actualPrimaryKeys.add("DPM_ID");
			actualPrimaryKeysValues.add(record.getDpmId());
		} else if (record.getHbpejId() != null) {
			actualPrimaryKeys.add("HBPEJ_ID");
			actualPrimaryKeysValues.add(record.getHbpejId());
		}
		actualPrimaryKeys.add("PORADOVE_CISLO_BODU");
		actualPrimaryKeysValues.add(record.getPoradoveCisloBodu());

		primaryKeys = actualPrimaryKeys;
		primaryKeysValues = actualPrimaryKeysValues;
	}

	private void processRecord(SpojeniBMapy record) throws JdbcException {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				name, "DATUM_VZNIKU", record.getDatumVzniku());

		if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			delete(parameters, Operations.lessThan, true);
			insert(parameters.getTable(), record, true);
		}

	}

	private void processHistoricalRecord(SpojeniBMapy record)
			throws JdbcException {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(name
				+ "_MIN", "DATUM_VZNIKU", record.getDatumVzniku());

		if (!find(parameters, Operations.equal, true)) {
			insert(name + "_MIN", record, false);
			parameters.setTable(name);
			delete(parameters, Operations.equal, true);
		}

	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws JdbcException {
		try {
			if (isRecord) {
				insertRecord(table, rawRecord);
			} else
				insertHistoricalRecord(table, rawRecord);
		} catch (SQLException e) {
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during inserting "
					+ LogUtil.getMethodWhichThrowsException(e)
					+ " in " + LogUtil.getClassWhichThrowsException(e)
					+ "." + "\n" + rawRecord.toString());
		}
	}

	private void insertRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniBMapy record = (SpojeniBMapy) rawRecord;
			preparedStatement.setObject(1,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(3, record.getPoradoveCisloBodu());
			preparedStatement.setObject(4, record.getSouradniceY());
			preparedStatement.setObject(5, record.getSouradniceX());
			preparedStatement.setObject(6, record.getOpId());
			preparedStatement.setObject(7, record.getDpmId());
			preparedStatement.setObject(8, record.getHbpejId());
			preparedStatement.setObject(9, record.getParametrySpojeni());
			preparedStatement.setObject(10, 0);

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	private void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_SPOJENI_B_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniBMapy record = (SpojeniBMapy) rawRecord;
			preparedStatement.setObject(1,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(3, record.getPoradoveCisloBodu());
			preparedStatement.setObject(4, record.getSouradniceY());
			preparedStatement.setObject(5, record.getSouradniceX());
			preparedStatement.setObject(6, record.getOpId());
			preparedStatement.setObject(7, record.getDpmId());
			preparedStatement.setObject(8, record.getHbpejId());
			preparedStatement.setObject(9, record.getParametrySpojeni());
			preparedStatement.setObject(10, 0);

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}

	}

}
