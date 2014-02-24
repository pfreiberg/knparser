package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObeslaniMfOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<ObeslaniMf> obeslaniMf;
	private final static String name = "OBESLANI_MF";

	public ObeslaniMfOracleDatabaseJdbcExporter(List<ObeslaniMf> obeslaniMf,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.obeslaniMf = obeslaniMf;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ObeslaniMf record : obeslaniMf) {
			primaryKeysValues = getPrimaryKeysValues(record, methodsName);
			processRecord(record);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRecord(ObeslaniMf record) {
		
		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
		}
		insert(name, record, false);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			ObeslaniMf record = (ObeslaniMf) rawRecord;
			preparedStatement.setObject(1, record.getObeslaniId());
			preparedStatement.setObject(2, record.getZpusobObeslani());
			preparedStatement.setObject(3, record.getTypopeKod());
			preparedStatement.setObject(4, record.getUcastId());
			preparedStatement.setObject(5, record.getStavObeslani());
			preparedStatement.setObject(6,
					VfkUtil.convertToDatabaseDate(record.getDatumPrijetiDorucenky()));
			preparedStatement.setObject(7, record.getOpsubId());

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
