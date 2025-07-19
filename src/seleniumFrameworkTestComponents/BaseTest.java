package seleniumFrameworkTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumFrameworkPageObjects.LandingPage;

public class BaseTest {
// THis is the base test only where we create frameworks for all testcases starts from here.
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializerDriver() throws Exception {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\seleniumFrameworkResources\\GolbalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		//Terenary opearator. we can pass broweser value using command prompt also.
//		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "./Resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.msedge.driver", "./Resources/msedgedriver.exe");
			driver = new EdgeDriver();		
		}
		//		System.out.println(browserName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;

	}
     //Regarding Json
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws Exception {

		//read json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);

		//String to HashMap - Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}


	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws Exception {
		driver = initializerDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void closingApplication() {
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws Exception {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName+".png");	
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\"+ testCaseName+".png";
		
	}
}
