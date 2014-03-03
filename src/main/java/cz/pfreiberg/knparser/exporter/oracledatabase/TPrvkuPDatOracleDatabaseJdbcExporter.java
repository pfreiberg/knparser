package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TPrvkuPDat;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPrvkuPDatOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_PRVKU_P_DAT";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?)";

	public TPrvkuPDatOracleDatabaseJdbcExporter(List<TPrvkuPDat> tPrvkuPDat,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tPrvkuPDat, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
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
	}

}
