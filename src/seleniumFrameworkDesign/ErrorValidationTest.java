package seleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumFrameworkTestComponents.BaseTest;
import seleniumFrameworkTestComponents.Retry;

public class ErrorValidationTest extends BaseTest{

@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)	
	public void errormessage() {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("ramaganesh209@gmail.com", "Ganesh1243");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
}

		