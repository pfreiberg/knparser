package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel;
import cz.pfreiberg.knparser.util.LogUtil;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class HraniceParcelOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private static final Logger log = Logger
			.getLogger(HraniceParcelOracleDatabaseJdbcExporter.class);

	private List<HraniceParcel> hraniceParcel;
	private String parId1;
	private String parId2;

	private final static String name = "HRANICE_PARCEL";

	public HraniceParcelOracleDatabaseJdbcExporter(
			List<HraniceParcel> hraniceParcel,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.hraniceParcel = hraniceParcel;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (HraniceParcel record : hraniceParcel) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);

				try {
					if (record.getDatumZaniku() == null) {
						processRecord(record);
					} else {
						processHistoricalRecord(record);
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

	private void processRecord(HraniceParcel record) throws JdbcException {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				name, "DATUM_VZNIKU", record.getDatumVzniku());

		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (find(parameters, Operations.lessThan, true)) {
			delete(parameters, Operations.lessThan, true);
			insert(name, record, true);
		} else if (find(parameters, Operations.equal, true)) {

			getParId(name, "DATUM_VZNIKU", datumVzniku);

			if (parId1 != null && parId2 != null)
				return; // a
			else {
				long parId1Number = Long.valueOf(parId1);

				if (record.getParId2() == null) {
					if (record.getParId1() == parId1Number)
						return; // b-b
					else { // b-a
						record.setParId1(Math.min(parId1Number,
								record.getParId1()));
						record.setParId2(Math.max(parId1Number,
								record.getParId1()));
						update(name, record);
					}
				} else { // b-c
					record.setParId1(Math.min(record.getParId1(),
							record.getParId2()));
					record.setParId2(Math.max(record.getParId1(),
							record.getParId2()));
					update(name, record);

				}
				return;
			}
		} else if (find(parameters, Operations.greaterThan, true)) {
			return;
		} else
			insert(name, record, true);
	}

	private void processHistoricalRecord(HraniceParcel record)
			throws JdbcException {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(name
				+ "_MIN", "DATUM_VZNIKU", record.getDatumVzniku());

		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (find(parameters, Operations.equal, true)) {

			getParId(name + "_MIN", "DATUM_VZNIKU", datumVzniku);

			if (parId1 != null && parId2 != null)
				return;
			else {
				long parId1Number = Long.valueOf(parId1);

				if (record.getParId2() == null) {
					if (record.getParId1() == parId1Number)
						return;
					else {
						record.setParId1(Math.min(parId1Number,
								record.getParId1()));
						record.setParId2(Math.max(parId1Number,
								record.getParId1()));
						update(name + "_MIN", record);

					}
				} else {
					record.setParId1(Math.min(record.getParId1(),
							record.getParId2()));
					record.setParId2(Math.max(record.getParId1(),
							record.getParId2()));
					update(name + "_MIN", record);
				}
				return;
			}
		}

		insert(name + "_MIN", record, false);
		parameters.setTable(name);
		if (find(parameters, Operations.equal, true))
			delete(parameters, Operations.equal, true);
	}

	private void getParId(String table, String date, String dateValue) throws JdbcException {
		String select = "SELECT PAR_ID_1, PAR_ID_2 FROM " + table
				+ " WHERE *pk* AND " + date + "=" + dateValue;

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parId1 = resultSet.getString("PAR_ID_1");
				parId2 = resultSet.getString("PAR_ID_2");
			}
			preparedStatement.close();
			return;
		} catch (SQLException e) {
			throw new JdbcException("Error during " + select);
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
			String stackTrace = e.getStackTrace()[0].toString();
			throw new JdbcException("Error during inserting "
					+ LogUtil.getMethodWhichThrowsException(stackTrace)
					+ " in " + LogUtil.getClassWhichThrowsException(stackTrace)
					+ "." + "\n" + rawRecord.toString());
		}
	}

	private void insertRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		HraniceParcel record = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			record = (HraniceParcel) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getTypppdKod());
			preparedStatement.setObject(9, record.getParId1());
			preparedStatement.setObject(10, record.getParId2());
			preparedStatement.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	private void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_HRANICE_PARCEL_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			HraniceParcel record = (HraniceParcel) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getTypppdKod());
			preparedStatement.setObject(9, record.getParId1());
			preparedStatement.setObject(10, record.getParId2());
			preparedStatement.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

	private boolean update(String table, HraniceParcel record) throws JdbcException {
		String update = "UPDATE " + table + " SET PAR_ID_1 = "
				+ record.getParId1() + " , PAR_ID_2 = " + record.getParId2()
				+ " WHERE *pk*";

		for (int i = 0; i < primaryKeys.size(); i++) {
			update = update.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		update = update.replace(" AND *pk*", "");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(update);
			int affectedRows = preparedStatement.executeUpdate();
			return (affectedRows > 0);
		} catch (SQLException e) {
			throw new JdbcException("Error during " + update);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				log.error("Error during closing connection.");
				log.debug("Stack trace:", e);
			}
		}
	}

}
