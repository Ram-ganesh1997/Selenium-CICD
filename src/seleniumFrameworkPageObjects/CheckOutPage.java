package seleniumFrameworkPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")

	WebElement selcetCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void countryList(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitElementAppear(results);
		selcetCountry.click();
		//		submit.click();
	}

	public ConfirmationPage placeTheOrder() {
		submit.click();
		ConfirmationPage confirmMsg = new ConfirmationPage(driver);
		return confirmMsg;
	}	

}
