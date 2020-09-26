package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Database {
	private String databaseURL;
	private int rowCount = -1;
	private String table;

	public Database(String connectonString, String table) {
		databaseURL = connectonString;
		this.table = table;
	}

	public ArrayList retrieveTableData() {
		ArrayList dataset = new ArrayList();
		try (Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + databaseURL)) {
			String sql = "SELECT * FROM " + table;

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			Hashtable<String, String> tableData;
			while (result.next()) {
				tableData = new Hashtable<String, String>();
				rowCount++;
				tableData.put("FirstName", result.getString("FirstName"));
				tableData.put("LastName", result.getString("LastName"));
				tableData.put("Username", result.getString("Username"));
				tableData.put("Role", result.getString("Role"));
				tableData.put("Email", result.getString("Email"));
				tableData.put("Cell", result.getString("Cell"));
				tableData.put("Password", result.getString("Password"));
				tableData.put("Customer", result.getString("Customer"));
				dataset.add(tableData);
			}
			return dataset;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public int totalRows() {
		try (Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + databaseURL)) {
			String sql = "SELECT count(*) as count FROM " + table;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next())
				rowCount = result.getInt("count");
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
}
