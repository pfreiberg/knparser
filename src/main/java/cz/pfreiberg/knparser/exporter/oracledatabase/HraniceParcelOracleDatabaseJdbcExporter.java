package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class HraniceParcelOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<HraniceParcel> hraniceParcel;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private String parId1;
	private String parId2;

	private final String name = "HRANICE_PARCEL";

	public HraniceParcelOracleDatabaseJdbcExporter(
			List<HraniceParcel> hraniceParcel,
			ConnectionParameters connectionParameters) {
		this.hraniceParcel = hraniceParcel;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (HraniceParcel record : hraniceParcel) {
			primaryKeysValues = getPrimaryKeysValues(record);
			if (record.getDatumZaniku() == null) {
				processRecord(record);
			} else {
				processHistoricalRecord(record);
			}
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Object> getPrimaryKeysValues(Object record) {
		List<Object> primaryKeyValues = new ArrayList<>();
		try {
			for (int i = 0; i < methodsName.size(); i++) {
				Class<?> c = Class
						.forName("cz.pfreiberg.knparser.domain.prvkykatastralnimapy.HraniceParcel");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((HraniceParcel) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(HraniceParcel record) {
		
		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		
		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (newFind(parameters, Operations.lessThan, true)) {
			newDelete(parameters, Operations.lessThan, true);
			insert(name, record, true);
		} else if (newFind(parameters, Operations.equal, true)) {

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
		} else if (newFind(parameters, Operations.greaterThan, true)) {
			return;
		} else
			insert(name, record, true);
	}

	private void processHistoricalRecord(HraniceParcel record) {
		
		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name + "_MIN", primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (newFind(parameters, Operations.equal, true)) {

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
		if (newFind(parameters, Operations.equal, true))
			newDelete(parameters, Operations.equal, true);
	}

	private void getParId(String table, String date, String dateValue) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		if (isRecord) {
			insertRecord(table, rawRecord);
		} else
			insertHistoricalRecord(table, rawRecord);
	}

	public void insertRecord(String table, Object rawRecord) {
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
			preparedStatement.close();
		}

		catch (Exception e) {
			System.out.println(record);
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean update(String table, HraniceParcel record) {
		String select = "UPDATE " + table + " SET PAR_ID_1 = "
				+ record.getParId1()
				+ " , PAR_ID_2 = "
				+ record.getParId2()
				+ " WHERE *pk*";

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			int affectedRows = preparedStatement.executeUpdate();
			preparedStatement.close();
			return (affectedRows > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void insertHistoricalRecord(String table, Object rawRecord) {

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
			preparedStatement.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String table, String date, String dateValue,
			String operation) {
		// TODO Auto-generated method stub
		
	}
}
