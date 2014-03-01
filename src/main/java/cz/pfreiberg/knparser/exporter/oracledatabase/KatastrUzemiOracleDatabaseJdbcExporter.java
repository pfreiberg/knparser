package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KatastrUzemiOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "KATASTR_UZEMI";

	public KatastrUzemiOracleDatabaseJdbcExporter(
			List<KatastrUzemi> katastrUzemi,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(katastrUzemi, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			KatastrUzemi record = (KatastrUzemi) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getObceKod());
			preparedStatement.setObject(3, record.getNazev());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setNull(6, Types.DECIMAL);

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
