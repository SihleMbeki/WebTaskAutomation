package testing;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Report;

@Listeners(utilities.Listener.class)
public class AppTest extends Base{
	private ExtentTest test;

	@BeforeMethod
	public void setup(Method method) {
		test=Report.createTest(method.getName());	
	}
	@Test
	public void test() {
		test.log(Status.PASS, "Test Passed");
	}
	@AfterMethod
	public void tearDown() {
		
	}
	
}
