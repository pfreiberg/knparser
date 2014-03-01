package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.TListin;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TListinOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_LISTIN";

	public TListinOracleDatabaseJdbcExporter(List<TListin> tListin,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(tListin, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES" + "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			TListin record = (TListin) rawRecord;
			preparedStatement.setObject(1, record.getKod());
			preparedStatement.setObject(2, record.getNazev());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
			preparedStatement.setObject(4, record.getPopis());
			preparedStatement.setObject(5,
					VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
			preparedStatement.setObject(6, record.getDruhList());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
