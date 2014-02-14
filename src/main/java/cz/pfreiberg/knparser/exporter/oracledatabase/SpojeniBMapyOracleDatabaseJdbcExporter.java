package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniBMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniBMapyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<SpojeniBMapy> spojeniBMapy;
	private Connection connection;
	private List<String> primaryKeys;
	private List<Object> primaryKeysValues;

	private final String name = "SPOJENI_B_MAPY";

	public SpojeniBMapyOracleDatabaseJdbcExporter(
			List<SpojeniBMapy> spojeniBMapy,
			ConnectionParameters connectionParameters) {
		this.spojeniBMapy = spojeniBMapy;
		connection = super.getConnection(connectionParameters);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (SpojeniBMapy record : spojeniBMapy) {
			i++;
			if (i % 100 == 0)
				System.out.println("SpojeniBMapy: " + i);
			getActualValues(record);
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


	private void processRecord(SpojeniBMapy record) {
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

	private void processHistoricalRecord(SpojeniBMapy record) {
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
