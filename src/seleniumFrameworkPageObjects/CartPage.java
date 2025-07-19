package seleniumFrameworkPageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	
	public List<WebElement> getCartList() {
		return cartProducts;
	}
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage clickToCheckout() {
		
		checkOut.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
		
	}
}
