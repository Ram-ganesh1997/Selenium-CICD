package extendReportsWebApplications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtrentDemo {
	
	
	ExtentReports extent;
	@BeforeMethod
	public void config() {
		
		//ExtentReports, ExtendSparkReporter
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Application Results");
		reporter.config().setDocumentTitle("Application Test");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ram Ganesh");

		
		
	}
	
	
	
	
@Test	
	public void initialDemo() {
	
	extent.createTest("Initaial Demo");
		System.setProperty("webdriver.chrome.driver", ".//Resources//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		driver.close();
		extent.flush();
		
	}
}
