package cucumberStepDefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkPageObjects.CartPage;
import seleniumFrameworkPageObjects.CheckOutPage;
import seleniumFrameworkPageObjects.ConfirmationPage;
import seleniumFrameworkPageObjects.LandingPage;
import seleniumFrameworkPageObjects.ProductCatalogue;
import seleniumFrameworkTestComponents.BaseTest;

public class StepDefinitionsImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	CartPage cartPage;
	ConfirmationPage confirmMsg;
	
//	Given I landed on Ecommerce page
	@Given ("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws Exception {

		landingpage = launchApplication();
	}
	
//	Given Logged in with username <username> and password <password>
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productcatalogue = landingpage.loginApplication(username,password);
	}
	
//	When I add product <productname> to cart
	@ When ("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productname) throws Exception {
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addToCart(productname);
	}
	
//	And Checkout<productname> and submit the order
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_orders(String productname) {
		cartPage =	productcatalogue.clickOnCart();
		Boolean match=cartPage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.clickToCheckout();
		checkoutpage.countryList("India");
		confirmMsg = checkoutpage.placeTheOrder();
	}
	
//	Then "THANKYOU FOR THE ORDER." message is displayes on confirmation page
	@Then ("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		String message = confirmMsg.verifyConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
//	Then "Incorrect email or password." message is displayed
	@Then ("{string} message is displayed")
	public void something_message_is_displayed(String errorMsg) {
		Assert.assertEquals(errorMsg, landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
