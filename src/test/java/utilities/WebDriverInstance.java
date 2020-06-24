package utilities;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverInstance {
	private WebDriver driver;

	public WebDriverInstance() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public WebDriver getInstance() {
		return driver;
	}

	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	public void goTo(String url) {
		driver.get(url);
	}

	public boolean selectDropdown(WebElement select, String option) {
		try {
			Select selection = new Select(select);
			selection.selectByVisibleText(option);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean clickElementByLocator(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean clickElementByCssLocator(String css) {
		try {
			driver.findElement(By.cssSelector(css)).click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean enterText(String css,String text) {
		try {
			driver.findElement(By.cssSelector(css)).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public WebElement findElementByCssLocator(String css) {
		try {
			return driver.findElement(By.cssSelector(css));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean waitForVisibilityOfElement(By xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public String screenShot() {
		try {
			Date date=new Date();
			String fileName=Report.reportPath+date.toString().replace(":", "_").replace(" ", "")+".jpg";
			File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(fileName));
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
