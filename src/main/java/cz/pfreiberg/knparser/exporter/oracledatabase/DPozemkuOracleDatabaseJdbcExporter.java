package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DPozemkuOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "D_POZEMKU";

	public DPozemkuOracleDatabaseJdbcExporter(List<DPozemku> dPozemku,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(dPozemku, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			DPozemku record = (DPozemku) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3, record.getZemedelskaKultura());
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(5, record.getTypppdKod());
			preparedStatement.setObject(6,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(7, record.getZkratka());
			preparedStatement.setObject(8, record.getStavebniParcela());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}
}
