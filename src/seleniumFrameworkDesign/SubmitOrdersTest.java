package seleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumFrameworkPageObjects.CartPage;
import seleniumFrameworkPageObjects.CheckOutPage;
import seleniumFrameworkPageObjects.ConfirmationPage;
import seleniumFrameworkPageObjects.LandingPage;
import seleniumFrameworkPageObjects.OrdersPage;
import seleniumFrameworkPageObjects.ProductCatalogue;
import seleniumFrameworkTestComponents.BaseTest;

public class SubmitOrdersTest  extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups = {"checking"})
	public void submitOrders(HashMap<String, String> input) throws Exception {
		ProductCatalogue productcatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addToCart(input.get("product"));
		CartPage cartPage =	productcatalogue.clickOnCart();
	
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.clickToCheckout();
		checkoutpage.countryList("India");
		ConfirmationPage confirmMsg = checkoutpage.placeTheOrder();

		String message = confirmMsg.verifyConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	
	@Test (dependsOnMethods = {"submitOrders"})	
public void OrderHistoreTest() {
		ProductCatalogue productcatalogue =landingPage.loginApplication("ramaganesh209@gmail.com", "Ganesh143");
		OrdersPage ordersPage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {
	
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\seleniumFrameworkDataJson\\dataProvider.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		

		
//		String [][] arr = new String[2][2];
//	return	new Object [][] {{"ramaganesh209@gmail.com", "Ganesh143","ZARA COAT 3" },{"ramaganesh209@gmail.com", "Ganesh143","ADIDAS ORIGINAL"}};
		
	}
	

}
