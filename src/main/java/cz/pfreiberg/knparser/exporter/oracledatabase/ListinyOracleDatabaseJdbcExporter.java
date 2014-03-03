package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Listiny;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ListinyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "LISTINY";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public ListinyOracleDatabaseJdbcExporter(List<Listiny> listiny,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(listiny, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		Listiny record = (Listiny) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, record.getTyplistKod());
		psInsert.setObject(3, record.getPopis());
		psInsert.setObject(4, record.getObsah());
		psInsert.setObject(5, record.getStran());
		psInsert.setObject(6,
				VfkUtil.convertToDatabaseDate(record.getDatumVyhotoveni()));
		psInsert.setObject(7, record.getZhotovitel());
		psInsert.setObject(8, record.getPoradoveCisloZhotovitele());
		psInsert.setObject(9,
				VfkUtil.convertToDatabaseDate(record.getRokZhotovitele()));
		psInsert.setObject(10, record.getDoplneniZhotovitele());
		psInsert.setObject(11, record.getZkratka());
		psInsert.setObject(12, record.getRizeniId());
		psInsert.setObject(13, record.getZmenaPravVztahu());
		psInsert.setObject(14,
				VfkUtil.convertToDatabaseDate(record.getDatumPravMoci()));
		psInsert.setObject(15,
				VfkUtil.convertToDatabaseDate(record.getDatumVykonatelnosti()));
		psInsert.setObject(16,
				VfkUtil.convertToDatabaseDate(record.getDatumHistOd()));
		psInsert.setObject(17,
				VfkUtil.convertToDatabaseDate(record.getDatumHistDo()));
	}

}
