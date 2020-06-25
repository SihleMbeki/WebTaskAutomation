package testing;


import java.util.Hashtable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.UserTableList;
import utilities.Database;
import utilities.Report;
import utilities.WebDriverInstance;

@Listeners(utilities.Listener.class)
public class UserList_Database extends Base {

	@BeforeMethod
	public void setup() {
		test = Report.createTest("method.getName()");
		driver = new WebDriverInstance();
	}

	@Test(dataProvider = "getTableData")
	public void addNewUser(Hashtable<String, String> usersList) {
		driver.goTo("http://www.way2automation.com/angularjs-protractor/webtables/");
		UserTableList userTableList = new UserTableList(driver, test);
		userTableList.databaseData(usersList);
		userTableList.executeTest();
		test.log(Status.PASS, "Test Passed");
	}

	@AfterMethod
	public void tearDown() {
		driver.closeDriver();
	}

	@DataProvider
	public Object[][] getTableData() {
		int rows = 0;
		Database db = new Database(database, table);
		Object[][] testData;
		testData = new Object[db.totalRows()][1];

		for (Object row : db.retrieveTableData()) {
			testData[rows][0] = row;
			rows++;
		}
		return testData;
	}

}
