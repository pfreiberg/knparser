package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.KodyCharQBodu;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KodyCharQBoduOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "KODY_CHAR_Q_BODU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?)";

	public KodyCharQBoduOracleDatabaseJdbcExporter(
			List<KodyCharQBodu> kodyCharQBodu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(kodyCharQBodu, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		KodyCharQBodu record = (KodyCharQBodu) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getNazev());
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
	}

}
