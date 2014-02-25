package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.ConnectionParameters;
import cz.pfreiberg.knparser.domain.rizeni.Ucastnici;

public class UcastniciOracleDatabaseJdbcExporter extends
		StavOracleDatabaseJdbcExporter {

	private final static String name = "UCASTNICI";

	public UcastniciOracleDatabaseJdbcExporter(List<Ucastnici> ucastnici,
			ConnectionParameters connectionParameters) {
		super(connectionParameters, name);
		prepareStatement(ucastnici, name);
	}

	@Override
	public void insert(String table, Object rawRecord, boolean isRecord) {
		String insert = "INSERT INTO " + table + " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insert);

			Ucastnici record = (Ucastnici) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getRizeniId());
			preparedStatement.setObject(3, record.getDruhUcastnika());
			preparedStatement.setObject(4, record.getJmeno());
			preparedStatement.setObject(5, record.getJmenoU());
			preparedStatement.setObject(6, record.getPrijmeni());
			preparedStatement.setObject(7, record.getPrijmeniU());
			preparedStatement.setObject(8, record.getTitulPredJmenem());
			preparedStatement.setObject(9, record.getTitulZaJmenem());
			preparedStatement.setObject(10, record.getRc());
			preparedStatement.setObject(11, record.getRodnePrijmeni());
			preparedStatement.setObject(12, record.getRodinnyStav());
			preparedStatement.setObject(13, record.getObchodniJmeno());
			preparedStatement.setObject(14, record.getObchodniJmenoU());
			preparedStatement.setObject(15, record.getDic());
			preparedStatement.setObject(16, record.getIco());
			preparedStatement.setObject(17, record.getDoplnekIco());
			preparedStatement.setObject(18, record.getOverenPodpis());
			preparedStatement.setObject(19, record.getOverenProtiRs());
			preparedStatement.setObject(20, record.getOverenProtiOs());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
