package pages;

import java.util.Hashtable;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.WebDriverInstance;

public class UserTableList {
	WebDriverInstance driver;
	ExtentTest test;

	private String title() {
		return "//title[text()='Protractor practice website - WebTables']";
	}

	private String addUser() {
		return "td[ng-show='actions.add.url'] button[type='add']";
	}

	private String addUserDialog() {
		return "//h3[text()='Add User']";
	}

	private String firstname() {
		return "input[name='FirstName']";
	}

	private String lastname() {
		return "input[name='LastName']";
	}

	private String username() {
		return "input[name='UserName']";
	}

	private String password() {
		return "input[name='Password']";
	}

	private String email() {
		return "input[name='Email']";
	}

	private String cellphone() {
		return "input[name='Mobilephone']";
	}

	private String customer(String option) {
		return "//label[text()='" + option + "']/input";
	}

	private String role() {
		return "select[name='RoleId']";
	}

	private String save() {
		return "button[ng-click='save(user)']";
	}

	private String validateTableData(String value, String index) {
		return "//table[@table-title='Smart Table example']/tbody/tr[position()=1]/td[text()='" + value
				+ "' and position()=" + index + "]";
	}

	public UserTableList(WebDriverInstance driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	private String fristname, lastname, username, role, email, cell, password, customer;

	public void excelData(String fristname, String lastname, String username, String role, String email, String cell,
			String password, String customer) {
		this.fristname = fristname;
		this.lastname = lastname;
		this.username = username;
		this.role = role;
		this.email = email;
		this.cell = cell;
		this.password = password;
		this.customer = customer;
	}

	public void databaseData(Hashtable<String, String> testData) {
		this.fristname = testData.get("FirstName");
		this.lastname = testData.get("LastName");
		this.username = testData.get("Username");
		this.role = testData.get("Role");
		this.email = testData.get("Email");
		this.cell = testData.get("Cell");
		this.password = testData.get("Password");
		this.customer = testData.get("Customer");
	}

	public void executeTest() {
		Assert.assertTrue(validatePage());
		Assert.assertTrue(addNewUser());
		Assert.assertTrue(validateUserRecord());
	}

	private boolean validatePage() {
		if (!driver.waitForPresenceOfElement(title())) {
			test.log(Status.FAIL, "Failed to validate page title");
			return false;
		}
		test.log(Status.PASS, "Validated user list table page");
		return true;

	}

	private boolean addNewUser() {
		driver.findElementByCssLocator(addUser()).click();
		test.log(Status.PASS, "Click add user");

		if (!driver.waitForVisibilityOfElement(addUserDialog())) {
			test.log(Status.FAIL, "Validate add user dialog");
			return false;
		}

		driver.findElementByCssLocator(firstname()).sendKeys(fristname);
		test.log(Status.PASS, "Entered username:" + fristname);

		driver.findElementByCssLocator(lastname()).sendKeys(lastname);
		test.log(Status.PASS, "Entered lastname:" + lastname);

		driver.findElementByCssLocator(username()).sendKeys(username);
		test.log(Status.PASS, "Entered username:" + username);

		driver.findElementByCssLocator(password()).sendKeys(password);
		test.log(Status.PASS, "Entered password:" + password);

		driver.clickElementByLocator(customer(customer));
		test.log(Status.PASS, "Selected customer:" + customer);

		driver.findElementByCssLocator(email()).sendKeys(email);
		test.log(Status.PASS, "Entered email:" + email);

		driver.selectDropdown(driver.findElementByCssLocator(role()), role);
		test.log(Status.PASS, "Selected role:" + role);

		driver.findElementByCssLocator(cellphone()).sendKeys(cell);
		test.log(Status.PASS, "Entered cellphone:" + cell);

		driver.findElementByCssLocator(save()).click();
		test.log(Status.PASS, "Clicked save:");
		return true;

	}

	public boolean validateUserRecord() {
		if (!driver.waitForVisibilityOfElement(validateTableData(username, "3"))) {
			test.log(Status.FAIL, "Validate username :" + username);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(fristname, "1"))) {
			test.log(Status.FAIL, "Validate firstname:" + fristname);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(lastname, "2"))) {
			test.log(Status.FAIL, "Validate lastname:" + lastname);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(role, "6"))) {
			test.log(Status.FAIL, "Validate role:" + role);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(email, "7"))) {
			test.log(Status.FAIL, "Validate email:" + email);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(cell, "8"))) {
			test.log(Status.FAIL, "Validate cellphone:" + cell);
			return false;
		}

		if (!driver.findElementByXpath(validateTableData(customer, "5"))) {
			test.log(Status.FAIL, "Validate customer:" + customer);
			return false;
		}
		return true;
	}
}
