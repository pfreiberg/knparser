package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.TSouradSys;
import cz.pfreiberg.knparser.util.VfkUtil;

public class TSouradSysOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "T_SOURAD_SYS";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public TSouradSysOracleDatabaseJdbcExporter(List<TSouradSys> tSouradSys,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(tSouradSys, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		TSouradSys record = (TSouradSys) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
