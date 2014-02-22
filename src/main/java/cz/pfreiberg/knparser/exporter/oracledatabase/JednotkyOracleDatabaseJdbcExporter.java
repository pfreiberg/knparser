package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jednotky.Jednotky;
import cz.pfreiberg.knparser.util.Operations;
import cz.pfreiberg.knparser.util.VfkUtil;

public class JednotkyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Jednotky> jednotky;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private List<Object> primaryKeysValues;

	private final String name = "JEDNOTKY";

	public JednotkyOracleDatabaseJdbcExporter(List<Jednotky> jednotky,
			ConnectionParameters connectionParameters) {
		this.jednotky = jednotky;
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
		for (Jednotky record : jednotky) {
			primaryKeysValues = getPrimaryKeysValues(record, methodsName);
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

	private void processRecord(Jednotky record) {
		
		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		if (find(parameters, Operations.lessThan, true)) {
			delete(parameters, Operations.lessThan, true);
			insert(name, record, true);
		} else if (find(parameters, Operations.greaterThanOrEqual, true)) {
			return;
		} else {
			insert(name, record, true);
		}
		
	}

	private void processHistoricalRecord(Jednotky record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name + "_MIN", primaryKeys, primaryKeysValues, 
				"DATUM_VZNIKU", record.getDatumVzniku());
		
		if (!find(parameters, Operations.equal, true)) {
			insert(name + "_MIN", record, false);
			parameters.setTable(name);
			if (find(parameters, Operations.equal, true)) {
				delete(parameters, Operations.equal, true);
			}
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
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Jednotky record = (Jednotky) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBudId());
			preparedStatement.setObject(9, record.getTypjedKod());
			preparedStatement.setObject(10, record.getCisloJednotky());
			preparedStatement.setObject(11, record.getCenaNemovitosti());
			preparedStatement.setObject(12, record.getZpvyjeKod());
			preparedStatement.setObject(13, record.getTelId());
			preparedStatement.setObject(14, record.getPodilCitatel());
			preparedStatement.setObject(15, record.getPodilJmenovatel());
			preparedStatement.setObject(16, record.getPopis());

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
				+ "(SEQ_JEDNOTKY_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			Jednotky record = (Jednotky) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getBudId());
			preparedStatement.setObject(9, record.getTypjedKod());
			preparedStatement.setObject(10, record.getCisloJednotky());
			preparedStatement.setObject(11, record.getCenaNemovitosti());
			preparedStatement.setObject(12, record.getZpvyjeKod());
			preparedStatement.setObject(13, record.getTelId());
			preparedStatement.setObject(14, record.getPodilCitatel());
			preparedStatement.setObject(15, record.getPodilJmenovatel());
			preparedStatement.setObject(16, record.getPopis());

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

}
