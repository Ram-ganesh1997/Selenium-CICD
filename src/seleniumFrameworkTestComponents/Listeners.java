package seleniumFrameworkTestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import seleniumFrameworkResources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener, ITestNGListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace(); // Handle driver reflection failure
        }

        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            test.fail("Screenshot:",
                MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
        } catch (Exception e1) {
            e1.printStackTrace(); // Handle screenshot capture failure
        }
    }

    public void onTestSkipped(ITestResult result) {}

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    public void onStart(ITestContext context) {}

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}