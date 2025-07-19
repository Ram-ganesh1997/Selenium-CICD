package seleniumFrameworkPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css = ".hero-primary")
	WebElement comfirmMessage;
	
	public String verifyConfirmationMessage() {
		return comfirmMessage.getText();
	
		
		
		
	}
	
	
//	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

}
