package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.DalsiUdajeListiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DalsiUdajeListinyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "DALSI_UDAJE_LISTINY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public DalsiUdajeListinyOracleDatabaseJdbcExporter(
			List<DalsiUdajeListiny> dalsiUdajeListiny,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(dalsiUdajeListiny, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		DalsiUdajeListiny record = (DalsiUdajeListiny) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
