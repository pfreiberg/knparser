package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPrvkuPDatOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<TPrvkuPDat> tPrvkuPDat;
	private final static String name = "T_PRVKU_P_DAT";

	public TPrvkuPDatOracleDatabaseJdbcExporter(List<TPrvkuPDat> tPrvkuPDat,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		this.tPrvkuPDat = tPrvkuPDat;
		prepareStatement();
	}

	private void prepareStatement() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TPrvkuPDat record : tPrvkuPDat) {
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

	private void processRecord(TPrvkuPDat record) {

		OracleDatabaseParameters parameters = new OracleDatabaseParameters(
				connection, name, primaryKeys, primaryKeysValues, null, null);

		if (find(parameters, null, false)) {
			delete(parameters, null, false);
			insert(name, record, false);
		} else {
			insert(name, record, false);
		}
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(insert);

			TPrvkuPDat record = (TPrvkuPDat) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getPolohopis());
			preparedStatement.setObject(3, record.getEditovatelny());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(5, record.getVyznam());
			preparedStatement.setObject(6, record.getKrivka());
			preparedStatement.setObject(7, record.getTypPrvku());
			preparedStatement.setObject(8,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));

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
