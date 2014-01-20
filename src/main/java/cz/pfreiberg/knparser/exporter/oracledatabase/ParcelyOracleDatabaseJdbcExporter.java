package cz.pfreiberg.knparser.exporter.oracledatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cz.pfreiberg.knparser.domain.nemovitosti.Parcely;
import cz.pfreiberg.knparser.util.VfkUtil;

public class ParcelyOracleDatabaseJdbcExporter extends
		OracleDatabaseJdbcExporter {

	private List<Parcely> parcely;
	private Connection connection;
	private List<String> primaryKeys;
	private List<String> methodsName;
	private ResultSet rs;

	private final String name = "PARCELY";

	public ParcelyOracleDatabaseJdbcExporter(List<Parcely> parcely,
			ConnectionParameters connectionParameters) {
		this.parcely = parcely;
		connection = super.getConnection(connectionParameters);
		primaryKeys = super.getPrimaryKeys(connection, name);
		methodsName = VfkUtil.getMethods(primaryKeys);
		prepareStatement();
	}

	private void prepareStatement() {
		System.out.println(parcely.size());
		for (Parcely record : parcely) {
			if (record.getDatumZaniku() == null) {
				processRecord(record);
			} else {
				processHistoricalRecord(record);
			}
		}
	}

	private void processRecord(Parcely record) {

		String datumVzniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());
		List<Object> primaryKeysValues = getPrimaryKeysValues(record);
		if (!find(record)) {
			insert(name, record);
			System.out.println("Insert record.");
		} else
			System.out.println("Record is already in database.");

	}

	private void processHistoricalRecord(Parcely record) {

		String datumZaniku = VfkUtil.formatValueDatabase(record
				.getDatumVzniku());

		if (!find(record)) {
			insert(name + "_MIN", record);
			System.out.println("Insert historical record.");

			String datumVzniku = VfkUtil.formatValueDatabase(record
					.getDatumVzniku());
			if (!find(record)) {

				System.out.println("Delete historical record.");
			}
		}

	}

	private List<Object> getPrimaryKeysValues(Object record) {
		List<Object> primaryKeyValues = new ArrayList<>();
		try {
			for (int i = 0; i < methodsName.size(); i++) {
				Class<?> c = Class
						.forName("cz.pfreiberg.knparser.domain.nemovitosti.Parcely");
				Method method = c.getDeclaredMethod(methodsName.get(i));
				primaryKeyValues.add(method.invoke((Parcely) record));
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primaryKeyValues;
	}

	@Override
	public boolean find(Object record) {
		return false;
	}

	@Override
	public void insert(String table, Object rawRecord) {
		String insert = "INSERT INTO "
				+ table
				+ " VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement(insert);

			Parcely record = (Parcely) rawRecord;
			preparedStatement.setObject(1, record.getId());
			preparedStatement.setObject(2, record.getStavDat());
			preparedStatement.setObject(3,
					VfkUtil.convertToDatabaseDate(record.getDatumVzniku()));
			preparedStatement.setObject(4,
					VfkUtil.convertToDatabaseDate(record.getDatumZaniku()));
			preparedStatement.setObject(5, record.getPriznakKontextu());
			preparedStatement.setObject(6, record.getRizeniIdVzniku());
			preparedStatement.setObject(7, record.getRizeniIdZaniku());
			preparedStatement.setObject(8, record.getPknId());
			preparedStatement.setObject(9, record.getParType());
			preparedStatement.setObject(10, record.getKatuzeKod());
			preparedStatement.setObject(11, record.getKatuzeKodPuv());
			preparedStatement.setObject(12, record.getDruhCislovaniPar());
			preparedStatement.setObject(13, record.getKmenoveCisloPar());
			preparedStatement.setObject(14, record.getZdpazeKod());
			preparedStatement.setObject(15, record.getPoddeleniCislaPar());
			preparedStatement.setObject(16, record.getDilParcely());
			preparedStatement.setObject(17, record.getMaplisKod());
			preparedStatement.setObject(18, record.getZpurvyKod());
			preparedStatement.setObject(19, record.getDrupozKod());
			preparedStatement.setObject(20, record.getZpvypaKod());
			preparedStatement.setObject(21, record.getTypParcely());
			preparedStatement.setObject(22, record.getVymeraParcely());
			preparedStatement.setObject(23, record.getCenaNemovitosti());
			preparedStatement.setObject(24, record.getDefiniciniBodPar());
			preparedStatement.setObject(25, record.getTelId());
			preparedStatement.setObject(26, record.getParId());
			preparedStatement.setObject(27, record.getBudId());
			preparedStatement.setObject(28, record.getIdentBud());
			preparedStatement.setObject(29, record.getSoucasti());
			preparedStatement.setObject(30, record.getPsId());
			preparedStatement.setObject(31, record.getIdentPs());

			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String table, String first, String firstValue,
			String second, String secondValue) {
		String delete = "DELETE FROM " + table + " WHERE " + first + " = "
				+ firstValue + " AND " + second + " = " + secondValue;
		try {
			connection.prepareStatement(delete).execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
