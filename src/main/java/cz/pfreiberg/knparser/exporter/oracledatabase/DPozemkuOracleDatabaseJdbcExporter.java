package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.DPozemku;
import cz.pfreiberg.knparser.util.VfkUtil;

public class DPozemkuOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "D_POZEMKU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?)";

	public DPozemkuOracleDatabaseJdbcExporter(List<DPozemku> dPozemku,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(dPozemku, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		DPozemku record = (DPozemku) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3, record.getZemedelskaKultura());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(5, record.getTypppdKod());
		psInsert.setObject(6,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setObject(7, record.getZkratka());
		psInsert.setObject(8, record.getStavebniParcela());
	}
}
