package seleniumFrameworkPageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProductNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = ProductNames.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
