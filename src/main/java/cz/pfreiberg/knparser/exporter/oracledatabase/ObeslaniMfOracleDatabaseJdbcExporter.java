package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.ObeslaniMf;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ObeslaniMfOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "OBESLANI_MF";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?)";

	public ObeslaniMfOracleDatabaseJdbcExporter(List<ObeslaniMf> obeslaniMf,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(obeslaniMf, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		ObeslaniMf record = (ObeslaniMf) rawRecord;
		psInsert.setObject(1, record.getObeslaniId());
		psInsert.setObject(2, record.getZpusobObeslani());
		psInsert.setObject(3, record.getTypopeKod());
		psInsert.setObject(4, record.getUcastId());
		psInsert.setObject(5, record.getStavObeslani());
		psInsert.setObject(6, VfkUtil.convertToDatabaseDate(record
				.getDatumPrijetiDorucenky()));
		psInsert.setObject(7, record.getOpsubId());
	}

}
