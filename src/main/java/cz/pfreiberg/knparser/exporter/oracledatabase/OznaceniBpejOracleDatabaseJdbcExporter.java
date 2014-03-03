package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.bonitovanepudneekologickejednotky.OznaceniBpej;
import cz.pfreiberg.knparser.util.VfkUtil;

public class OznaceniBpejOracleDatabaseJdbcExporter extends
		HisOracleDatabaseJdbcExporter {

	private final static String name = "OZNACENI_BPEJ";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String hisInsert = "INSERT INTO " + name + "_MIN"
			+ " VALUES"
			+ "(SEQ_OZNACENI_BPEJ_MIN.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public OznaceniBpejOracleDatabaseJdbcExporter(
			List<OznaceniBpej> oznaceniBpej,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert, hisInsert);
		prepareStatement(oznaceniBpej, name);
	}

	protected void insertRecord(Object rawRecord)
			throws SQLException {
		OznaceniBpej record = (OznaceniBpej) rawRecord;
		psInsert.setObject(1, record.getId());
		psInsert.setObject(2, 0);
		psInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psInsert.setObject(5, 0);
		psInsert.setObject(6, record.getRizeniIdVzniku());
		psInsert.setObject(7, record.getRizeniIdZaniku());
		psInsert.setObject(8, record.getTypppdKod());
		psInsert.setObject(9, record.getSouradniceY());
		psInsert.setObject(10, record.getSouradniceX());
		psInsert.setObject(11, record.getText());
		psInsert.setObject(12, record.getVelikost());
		psInsert.setObject(13, record.getUhel());
		psInsert.setObject(14, record.getBpejKod());
		psInsert.setObject(15, record.getKatuzeKod());
		psInsert.setObject(16, record.getVztaznyBod());
	}

	protected void insertHistoricalRecord(Object rawRecord)
			throws SQLException {
		OznaceniBpej record = (OznaceniBpej) rawRecord;
		psHisInsert.setObject(1, record.getId());
		psHisInsert.setObject(2, 0);
		psHisInsert.setObject(3,
				VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
		psHisInsert.setObject(4,
				VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
		psHisInsert.setObject(5, 0);
		psHisInsert.setObject(6, record.getRizeniIdVzniku());
		psHisInsert.setObject(7, record.getRizeniIdZaniku());
		psHisInsert.setObject(8, record.getTypppdKod());
		psHisInsert.setObject(9, record.getSouradniceY());
		psHisInsert.setObject(10, record.getSouradniceX());
		psHisInsert.setObject(11, record.getText());
		psHisInsert.setObject(12, record.getVelikost());
		psHisInsert.setObject(13, record.getUhel());
		psHisInsert.setObject(14, record.getBpejKod());
		psHisInsert.setObject(15, record.getKatuzeKod());
		psHisInsert.setObject(16, record.getVztaznyBod());
	}
}
