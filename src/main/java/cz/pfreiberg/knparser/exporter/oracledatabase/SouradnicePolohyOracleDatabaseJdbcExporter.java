package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.prvkykatastralnimapy.SouradnicePolohy;

public class SouradnicePolohyOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "SOURADNICE_POLOHY";

	public SouradnicePolohyOracleDatabaseJdbcExporter(
			List<SouradnicePolohy> souradnicePolohy,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(souradnicePolohy, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) throws SQLException {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			SouradnicePolohy record = (SouradnicePolohy) rawRecord;
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
			preparedStatement.setObject(11, record.getKatuzeKodMer());
			preparedStatement.setObject(12, record.getCisloZpmzMer());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
		}
	}

}
