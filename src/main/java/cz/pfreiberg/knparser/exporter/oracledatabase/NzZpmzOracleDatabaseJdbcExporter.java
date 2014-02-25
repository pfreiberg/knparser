package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.geometrickyplan.NzZpmz;

public class NzZpmzOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "NZ_ZPMZ";

	public NzZpmzOracleDatabaseJdbcExporter(List<NzZpmz> nzZpmz,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(nzZpmz, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			NzZpmz record = (NzZpmz) rawRecord;
			preparedStatement.setObject(1, record.getNzId());
			preparedStatement.setObject(2, record.getZpmzCisloZpmz());
			preparedStatement.setObject(3, record.getZpmzKatuzeKod());

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
