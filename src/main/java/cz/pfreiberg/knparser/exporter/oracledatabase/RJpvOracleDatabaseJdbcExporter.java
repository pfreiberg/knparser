package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.RJpv;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RJpvOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private List<RJpv> rJpv;
	private final String name = "R_JPV";

	public RJpvOracleDatabaseJdbcExporter(List<RJpv> rJpv,
			ConnectionParameters connectionParameters) {
		this.rJpv = rJpv;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (RJpv record : rJpv) {
				primaryKeysValues = getPrimaryKeysValues(record, methodsName);
				OracleDatabaseParameters parameters = new OracleDatabaseParameters(
						connection, name, primaryKeys, primaryKeysValues,
						"DATUM_VZNIKU", record.getDatumVzniku());
				if (record.getDatumZaniku() == null) {
					processRecord(parameters, record);
				} else {
					processHistoricalRecord(parameters, record);
				}
			}
			connection.commit();
			connection.close();
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
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RJpv record = (RJpv) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getVerze());
			preparedStatement.setObject(3, 0);
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getHjpvId1());
			preparedStatement.setObject(9, record.getHjpvId2());
			preparedStatement.setObject(10, record.getTypvazbyJpv());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
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
				+ "(SEQ_R_JPV_MIN.nextval,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RJpv record = (RJpv) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getVerze());
			preparedStatement.setObject(3, 0);
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getHjpvId1());
			preparedStatement.setObject(9, record.getHjpvId2());
			preparedStatement.setObject(10, record.getTypvazbyJpv());

			preparedStatement.executeUpdate();
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
