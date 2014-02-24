package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.RZpochr;
import cz.pfreiberg.knparser.util.VfkUtil;

public class RZpochrOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private List<RZpochr> rZpochr;
	private final String name = "R_ZPOCHR";

	public RZpochrOracleDatabaseJdbcExporter(List<RZpochr> rZpochr,
			ConnectionParameters connectionParameters) {
		this.rZpochr = rZpochr;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (RZpochr record : rZpochr) {
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
				+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RZpochr record = (RZpochr) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getZpochrKod());
			preparedStatement.setObject(9, record.getParId());
			preparedStatement.setObject(10, record.getBudId());
			preparedStatement.setObject(11, record.getJedId());
			preparedStatement.setObject(12, record.getPsId());

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
				+ "(SEQ_R_ZPOCHR_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			RZpochr record = (RZpochr) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, 0);
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, 0);
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getZpochrKod());
			preparedStatement.setObject(9, record.getParId());
			preparedStatement.setObject(10, record.getBudId());
			preparedStatement.setObject(11, record.getJedId());
			preparedStatement.setObject(12, record.getPsId());

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

}
