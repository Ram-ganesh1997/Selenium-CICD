package seleniumFrameworkAbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkPageObjects.CartPage;
import seleniumFrameworkPageObjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
	}
	
	@FindBy (css ="[routerlink*='cart']" )
	WebElement cartClick;
	
	
	public CartPage clickOnCart() {
		
		cartClick.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	@FindBy (css ="[routerlink*='myorders']" )
	WebElement orderHeader;
	
	
	public OrdersPage goToOrdersPage() {
		
		orderHeader.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
		
	}
	
	

	public void waitElementAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitWebElemenTotAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitElementDisappear(WebElement ele) throws Exception {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}



}
