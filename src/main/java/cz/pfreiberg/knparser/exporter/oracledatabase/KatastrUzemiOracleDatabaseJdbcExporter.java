package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.nemovitosti.KatastrUzemi;
import cz.pfreiberg.knparser.util.VfkUtil;

public class KatastrUzemiOracleDatabaseJdbcExporter extends
		CisOracleDatabaseJdbcExporter {

	private final static String name = "KATASTR_UZEMI";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?)";

	public KatastrUzemiOracleDatabaseJdbcExporter(
			List<KatastrUzemi> katastrUzemi,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(katastrUzemi, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		KatastrUzemi record = (KatastrUzemi) rawRecord;
		psInsert.setObject(1, record.getKod());
		psInsert.setObject(2, record.getObceKod());
		psInsert.setObject(3, record.getNazev());
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getPlatnostOd()));
		psInsert.setObject(5,
				VfkUtil.convertToDatabaseDate(record.getPlatnostDo()));
		psInsert.setNull(6, Types.DECIMAL);
	}

}
