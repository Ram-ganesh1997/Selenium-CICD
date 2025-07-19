package seleniumFrameworkResources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

public static ExtentReports getReportObject() {
		
		//ExtentReports, ExtendSparkReporter
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Application Results");
		reporter.config().setDocumentTitle("Application Test");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ram Ganesh");
		return extent;
		

		
		
	}
}
