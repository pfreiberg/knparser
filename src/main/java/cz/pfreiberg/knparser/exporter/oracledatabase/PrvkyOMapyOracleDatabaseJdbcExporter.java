package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class PrvkyOMapyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<PrvkyOMapy> prvkyOMapy;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "PRVKY_O_MAPY";

	public PrvkyOMapyOracleDatabaseJdbcExporter(
			List<PrvkyOMapy> prvkyOMapy,
			ConnectionParameters connectionParameters) {
		this.prvkyOMapy = prvkyOMapy;
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
		for (PrvkyOMapy record : prvkyOMapy) {
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
						.forName("cz.pfreiberg.knparser.domain.prvkykatastralnimapy.PrvkyOMapy");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((PrvkyOMapy) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(PrvkyOMapy record) {
		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (find(name, "DATUM_VZNIKU", datumVzniku, "<")) {
			delete(name, "DATUM_VZNIKU", datumVzniku, "<");
			insert(name, record, true);
		} else if (find(name, "DATUM_VZNIKU", datumVzniku, ">=")) {
			return;
		} else {
			insert(name, record, true);
		}
	}

	private void processHistoricalRecord(PrvkyOMapy record) {
		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		if (!find(name + "_MIN", "DATUM_VZNIKU", datumVzniku, "=")) {
			insert(name + "_MIN", record, false);
			if (find(name, "DATUM_VZNIKU", datumVzniku, "=")) {
				delete(name, "DATUM_VZNIKU", datumVzniku, "=");
			}
		}
	}

	@Override
	public boolean find(String table, String date, String dateValue,
			String operation) {
		String select = "SELECT * FROM " + table + " WHERE *pk* AND " + date
				+ operation + dateValue;

		for (int i = 0; i < primaryKeys.size(); i++) {
			select = select.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		select = select.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(select);
			boolean isFound = preparedStatement.executeQuery().next();
			preparedStatement.close();
			return isFound;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
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
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			PrvkyOMapy record = (PrvkyOMapy) rawRecord;
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
			preparedStatement.setObject(9, record.getText());
			preparedStatement.setObject(10, record.getVelikost());
			preparedStatement.setObject(11, record.getUhel());
			preparedStatement.setObject(12, record.getVztaznyBod());
			preparedStatement.setObject(13, record.getKatuzeKod());
			preparedStatement.setNull(14, Types.STRUCT, "MDSYS.SDO_GEOMETRY");

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

	public void insertHistoricalRecord(String table, Object rawRecord) {

		String insert = "INSERT INTO " + table + " VALUES"
				+ "(SEQ_PRVKY_O_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			PrvkyOMapy record = (PrvkyOMapy) rawRecord;
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
			preparedStatement.setObject(9, record.getText());
			preparedStatement.setObject(10, record.getVelikost());
			preparedStatement.setObject(11, record.getUhel());
			preparedStatement.setObject(12, record.getVztaznyBod());
			preparedStatement.setObject(13, record.getKatuzeKod());
			preparedStatement.setNull(14, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
		
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
	public void delete(String table, String date, String dateValue,
			String operation) {
		String delete = "DELETE FROM " + table + " WHERE *pk* AND " + date
				+ operation + dateValue;
		for (int i = 0; i < primaryKeys.size(); i++) {
			delete = delete.replace("*pk*", primaryKeys.get(i) + " = "
					+ VfkUtil.formatValueDatabase(primaryKeysValues.get(i))
					+ " AND *pk*");
		}
		delete = delete.replace(" AND *pk*", "");
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(delete);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
