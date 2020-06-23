package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Report {
	public static ExtentHtmlReporter htmlReporter;
	private static ExtentReports reports;
	private static String filename="Report.html";

	public static void createReport(String directory) {
		directory+="\\"+ filename;
		htmlReporter = new ExtentHtmlReporter(directory);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("QA Automation");
		htmlReporter.config().setReportName("Way2Automation");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	public static ExtentTest createTest(String testName) {
		ExtentTest test=reports.createTest(testName);
	return test;
	}

	public static void closeReport() {
		if (reports != null) {
			reports.flush();
		}
	}

}
