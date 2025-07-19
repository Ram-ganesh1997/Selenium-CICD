package seleniumFrameworkPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	
	@FindBy(id ="login")
	WebElement submitButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMeaasge;
	
	
//	.ng-tns-c4-27.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submitButton.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitWebElemenTotAppear(errorMeaasge);
		return errorMeaasge.getText();
	
	}

}
