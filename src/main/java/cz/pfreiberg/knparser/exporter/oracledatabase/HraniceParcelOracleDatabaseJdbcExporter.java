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

	private final static String name = "HRANICE_PARCEL";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_HRANICE_PARCEL_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?)";

	private List<HraniceParcel> hraniceParcel;
	private PreparedStatement psInsert;
	private PreparedStatement psHisInsert;
	private String parId1;
	private String parId2;

	public HraniceParcelOracleDatabaseJdbcExporter(
			List<HraniceParcel> hraniceParcel,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.hraniceParcel = hraniceParcel;
		try {
			psInsert = connection.prepareStatement(insert);
			psHisInsert = connection.prepareStatement(hisInsert);
		} catch (SQLException e) {
			log.error("Error during initializing prepared statement for "
					+ name + ".");
			log.debug("Stack trace:", e);
		}
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
				}
			}
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

	private void getParId(String table, String date, String dateValue)
			throws JdbcException {
		String select = "SELECT PAR_ID_1, PAR_ID_2 FROM " + table
				+ " WHERE *pk* AND " + date + "=" + dateValue;

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(select);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parId1 = resultSet.getString("PAR_ID_1");
				parId2 = resultSet.getString("PAR_ID_2");
			}
			preparedStatement.close();
			return;
		} catch (SQLException e) {
			throw new JdbcException("Error during " + select);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
	}

	private boolean update(String table, HraniceParcel record)
			throws JdbcException {
		String update = "UPDATE " + table + " SET PAR_ID_1 = "
				+ record.getParId1() + " , PAR_ID_2 = " + record.getParId2()
				+ " WHERE *pk*";

		for (int i = 0; i < primaryKeys.size(); i++) {
			update = update.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		update = update.replace(" AND *pk*", "");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(update);
			int affectedRows = ps.executeUpdate();
			return (affectedRows > 0);
		} catch (SQLException e) {
			throw new JdbcException("Error during " + update);
		} finally {
			closePreparedStatement(ps);
		}
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws JdbcException {
		try {
			if (isRecord) {
				insertRecord(table, rawRecord);
				psInsert.execute();
				psInsert.clearParameters();
			} else {
				insertHistoricalRecord(table, rawRecord);
				psHisInsert.execute();
				psHisInsert.clearParameters();
			}
		} catch (SQLException e) {
			log.debug("Stack trace:", e);
			throw new JdbcException("Error during inserting "
					+ LogUtil.getHisMethodWhichThrowsException(e) + " in "
					+ LogUtil.getClassWhichThrowsException(e) + "." + "\n"
					+ rawRecord.toString());
		}
	}

	private void insertRecord(String table, Object rawRecord)
			throws SQLException {
		HraniceParcel record = (HraniceParcel) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getTypppdKod());
		psInsert.setObject(9, record.getParId1());
		psInsert.setObject(10, record.getParId2());
		psInsert.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

	private void insertHistoricalRecord(String table, Object rawRecord)
			throws SQLException {
		HraniceParcel record = (HraniceParcel) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getTypppdKod());
		psHisInsert.setObject(9, record.getParId1());
		psHisInsert.setObject(10, record.getParId2());
		psHisInsert.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
