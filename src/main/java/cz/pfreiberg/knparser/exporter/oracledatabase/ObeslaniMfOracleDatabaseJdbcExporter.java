package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObeslaniMfOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "OBESLANI_MF";

	public ObeslaniMfOracleDatabaseJdbcExporter(List<ObeslaniMf> obeslaniMf,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(obeslaniMf, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			ObeslaniMf record = (ObeslaniMf) rawRecord;
			preparedStatement.setObject(1, record.getObeslaniId());
			preparedStatement.setObject(2, record.getZpusobObeslani());
			preparedStatement.setObject(3, record.getTypopeKod());
			preparedStatement.setObject(4, record.getUcastId());
			preparedStatement.setObject(5, record.getStavObeslani());
			preparedStatement.setObject(6, VfkUtil.convertToDatabaseDate(record
					.getDatumPrijetiDorucenky()));
			preparedStatement.setObject(7, record.getOpsubId());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
