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
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getTprKod());
		psInsert.setObject(3, record.getNazev());
		psInsert.setObject(4, record.getVlastnictvi());
		psInsert.setObject(5, record.getProOs());
		psInsert.setObject(6, record.getProNemovitost());
		psInsert.setObject(7, record.getKNemovitosti());
		psInsert.setObject(8,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(9, record.getSekce());
		psInsert.setObject(10,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(11, record.getVlvztah());
		psInsert.setObject(12, record.getKOs());
		psInsert.setObject(13, record.getPodilVeritele());
		psInsert.setObject(14, record.getPoradi());
	}

}
