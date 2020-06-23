package testing;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.Report;

public class Base {
	public String directory;

	@BeforeSuite
	public void suiteSetup() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String report = format.format(date).toString();
		directory = System.getProperty("user.dir") + "\\Reports\\"
				+ report.replace(":", "_").replace(" ", "").replace("-", "");
		try {
			File file = new File(directory);
			file.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Report.createReport(directory);
	}

	@AfterSuite
	public void suiteCleanup() {
		Report.closeReport();
	}
}
