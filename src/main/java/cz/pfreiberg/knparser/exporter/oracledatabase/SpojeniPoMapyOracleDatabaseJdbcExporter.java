package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SpojeniPoMapy;
import cz.pfreiberg.knparser.util.VfkUtil;

public class SpojeniPoMapyOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private List<SpojeniPoMapy> spojeniPoMapy;
	private final String name = "SPOJENI_PO_MAPY";

	public SpojeniPoMapyOracleDatabaseJdbcExporter(
			List<SpojeniPoMapy> spojeniPoMapy,
			ConnectionParameters connectionParameters) {
		this.spojeniPoMapy = spojeniPoMapy;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (SpojeniPoMapy record : spojeniPoMapy) {
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
				+ "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniPoMapy record = (SpojeniPoMapy) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getPoradoveCisloBodu());
			preparedStatement.setObject(6, record.getSouradniceY());
			preparedStatement.setObject(7, record.getSouradniceX());
			preparedStatement.setObject(8, record.getPomId());
			preparedStatement.setObject(9, record.getParametrySpojeni());

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
				+ "(SEQ_SPOJENI_PO_MAPY_MIN.nextval,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SpojeniPoMapy record = (SpojeniPoMapy) rawRecord;
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(4, 0);
			preparedStatement.setObject(5, record.getPoradoveCisloBodu());
			preparedStatement.setObject(6, record.getSouradniceY());
			preparedStatement.setObject(7, record.getSouradniceX());
			preparedStatement.setObject(8, record.getPomId());
			preparedStatement.setObject(9, record.getParametrySpojeni());

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
