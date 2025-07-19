package seleniumFrameworkPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import seleniumFrameworkAbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> Products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitElementAppear(productsBy);
		return Products;
	}
		
		public WebElement getProductByName(String productName) {
			WebElement prod = getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addToCart(String productName) throws Exception {
			WebElement prod = getProductByName(productName);
			prod.findElement(addToCart).click();
			waitElementAppear(toastMessage);
			waitElementDisappear(spinner);
			clickOnCart();
		
		}
			
		
	}

