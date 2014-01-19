package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Parcely> parcely;
	private Connection connection;
	private ResultSet rs;

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connectionParameters) {
		this.parcely = parcely;
		connection = getConnection(connectionParameters);
		prepareStatement();
	}

	@Override
	public Connection getConnection(ConnectionParameters connection) {
		return super.getConnection(connection);
	}

	@Override
	public void prepareStatement() {
		System.out.println(parcely.size());
		//int i = 0;
		for (Parcely record : parcely) {
			//System.out.println(i++);
			if (record.getDatumZaniku() == null) {
				// platný záznam
				if (!find("PARCELY", "PKN_ID", VfkUtil.formatValueDatabase(record.getPknId()), "DATUM_VZNIKU", VfkUtil.formatValueDatabase(record.getDatumVzniku())))
				{
					System.out.println("platny insert");
				}
			}
			else {
				if (!find("PARCELY_MIN", "PKN_ID", VfkUtil.formatValueDatabase(record.getPknId()), "DATUM_ZANIKU", VfkUtil.formatValueDatabase(record.getDatumZaniku())))
				{
					System.out.println("platny hist. insert");
				}
			}
		}
		
	}

	@Override
	public boolean find(String table, String first, String firstValue, String second, String secondValue) {
		try {
			String select = "SELECT " + first + ", " + second + " FROM " + table + " WHERE " + first + " = " + firstValue + " AND " + second + " = " + secondValue;
			rs = connection.prepareStatement(select).executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
