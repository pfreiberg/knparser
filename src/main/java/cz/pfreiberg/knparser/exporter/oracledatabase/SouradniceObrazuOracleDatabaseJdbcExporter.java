package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradniceObrazu;

public class SouradniceObrazuOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "SOURADNICE_OBRAZU";
	private final static String insert = "INSERT INTO " + name + " VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?,?)";

	public SouradniceObrazuOracleDatabaseJdbcExporter(
			List<SouradniceObrazu> souradniceObrazu,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name, insert);
		prepareStatement(souradniceObrazu, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord)
			throws SQLException {
		SouradniceObrazu record = (SouradniceObrazu) rawRecord;
		preparedStatement.setObject(1, record.getId());
		preparedStatement.setObject(2, record.getStavDat());
		preparedStatement.setObject(3, record.getKatuzeKod());
		preparedStatement.setObject(4, record.getCisloZpmz());
		preparedStatement.setObject(5, record.getCisloTl());
		preparedStatement.setObject(6, record.getCisloBodu());
		preparedStatement.setObject(7, record.getUplneCislo());
		preparedStatement.setObject(8, record.getSouradniceY());
		preparedStatement.setObject(9, record.getSouradniceX());
		preparedStatement.setObject(10, record.getKodchbKod());
		preparedStatement.setNull(11, Types.STRUCT, "MDSYS.SDO_GEOMETRY");
	}

}
