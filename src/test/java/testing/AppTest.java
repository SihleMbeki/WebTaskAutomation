package testing;

import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExcelReader;
import utilities.Report;
import utilities.WebDriverInstance;

@Listeners(utilities.Listener.class)
public class AppTest extends Base {
	private ExtentTest test;
	WebDriverInstance driver;

	@BeforeMethod
	public void setup(Method method) {
		test = Report.createTest(method.getName());
		driver = new WebDriverInstance();
	}

	@Test(dataProvider = "getData")
	public void test(String name, String surname) {
		driver.goTo("https://www.google.com");
		test.log(Status.PASS, "Test Passed");
	}

	@AfterMethod
	public void tearDown() {
		driver.closeDriver();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] testData;
		ExcelReader excel = new ExcelReader(excelFileName, 0);
		testData = new Object[excel.rowCount() - 1][columnNum];

		for (Row row : excel.getSheet(0)) {
			if (row.getRowNum() == 0) {
				continue;
			}
			for (int cell = 0; cell < 2; cell++) {
				testData[row.getRowNum() - 1][cell] = row.getCell(cell).toString();
			}
		}
		return testData;
	}

}
