package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.ObrazyBudov;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObrazyBudovOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private List<ObrazyBudov> obrazyBudov;
	private final String name = "OBRAZY_BUDOV";

	public ObrazyBudovOracleDatabaseJdbcExporter(List<ObrazyBudov> obrazyBudov,
			ConnectionParameters connectionParameters) {
		this.obrazyBudov = obrazyBudov;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = super.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
			for (ObrazyBudov record : obrazyBudov) {
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
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ObrazyBudov record = (ObrazyBudov) rawRecord;
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
			preparedStatement.setObject(9, record.getSouradniceY());
			preparedStatement.setObject(10, record.getSouradniceX());
			preparedStatement.setObject(11, record.getVelikost());
			preparedStatement.setObject(12, record.getUhel());
			preparedStatement.setObject(13, record.getBudId());
			preparedStatement.setObject(14, record.getObrbudType());

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
				+ "(SEQ_OBRAZY_BUDOV_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ObrazyBudov record = (ObrazyBudov) rawRecord;
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
			preparedStatement.setObject(9, record.getSouradniceY());
			preparedStatement.setObject(10, record.getSouradniceX());
			preparedStatement.setObject(11, record.getVelikost());
			preparedStatement.setObject(12, record.getUhel());
			preparedStatement.setObject(13, record.getBudId());
			preparedStatement.setObject(14, record.getObrbudType());

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
