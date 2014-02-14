package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class CastiBudovOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<CastiBudov> castiBudov;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "CASTI_BUDOV";

	public CastiBudovOracleDatabaseJdbcExporter(List<CastiBudov> castiBudov,
			ConnectionParameters connectionParameters) {
		this.castiBudov = castiBudov;
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
		for (CastiBudov record : castiBudov) {
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
						.forName("cz.pfreiberg.knparser.domain.nemovitosti.CastiBudov");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((CastiBudov) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	private void processRecord(CastiBudov record) {
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

	private void processHistoricalRecord(CastiBudov record) {
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
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			CastiBudov record = (CastiBudov) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getRizeniIdVzniku());
			preparedStatement.setObject(6, record.getRizeniIdZaniku());
			preparedStatement.setObject(7, record.getBudId());
			preparedStatement.setObject(8, record.getTypbudKod());
			preparedStatement.setObject(9, record.getCisloDomovni());
			preparedStatement.setObject(10, record.getCenaNemovitosti());

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
				+ "(SEQ_CASTI_BUDOV_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			CastiBudov record = (CastiBudov) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getRizeniIdVzniku());
			preparedStatement.setObject(6, record.getRizeniIdZaniku());
			preparedStatement.setObject(7, record.getBudId());
			preparedStatement.setObject(8, record.getTypbudKod());
			preparedStatement.setObject(9, record.getCisloDomovni());
			preparedStatement.setObject(10, record.getCenaNemovitosti());

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
