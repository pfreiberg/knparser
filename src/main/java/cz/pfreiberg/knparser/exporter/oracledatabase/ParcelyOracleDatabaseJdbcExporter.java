package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;

public class ParcelyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Parcely> parcely;

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connection) {
		this.parcely = parcely;
		getConnection(connection);
	}

	@Override
	public void getConnection(ConnectionParameters connection) {
		// TODO Auto-generated method stub
		super.getConnection(connection);
	}

}
