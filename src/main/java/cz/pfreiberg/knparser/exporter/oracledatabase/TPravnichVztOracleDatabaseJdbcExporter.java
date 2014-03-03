package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.jinepravnivztahy.TPravnichVzt;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TPravnichVztOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_PRAVNICH_VZT";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public TPravnichVztOracleDatabaseJdbcExporter(
			List<TPravnichVzt> tPravnichVzt,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tPravnichVzt, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TPravnichVzt record = (TPravnichVzt) rawRecord;
		preparedStatement.setObject(1, record.getKod());
		preparedStatement.setObject(2, record.getTprKod());
		preparedStatement.setObject(3, record.getNazev());
		preparedStatement.setObject(4, record.getVlastnictvi());
		preparedStatement.setObject(5, record.getProOs());
		preparedStatement.setObject(6, record.getProNemovitost());
		preparedStatement.setObject(7, record.getKNemovitosti());
		preparedStatement.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		preparedStatement.setObject(9, record.getSekce());
		preparedStatement.setObject(10,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		preparedStatement.setObject(11, record.getVlvztah());
		preparedStatement.setObject(12, record.getKOs());
		preparedStatement.setObject(13, record.getPodilVeritele());
		preparedStatement.setObject(14, record.getPoradi());
	}

}
