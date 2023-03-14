package rahulshettyacademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjectModel.CartPage;
import rahulshettyacademy.pageObjectModel.CheckOutPage;
import rahulshettyacademy.pageObjectModel.ConfirmationPage;
import rahulshettyacademy.pageObjectModel.LandingPage;
import rahulshettyacademy.pageObjectModel.productCatalogue;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landing = new LandingPage(driver);
		landing.goTo();
		productCatalogue pd = landing.loginApplication("blacksovereign467@gmail.com", "enter password");
		productCatalogue pd2 = new productCatalogue(driver);
		
		List<WebElement> products =  pd.getProductList(); //gets product lists
		pd.addProductToCart(productName);//add product to cart
		CartPage cartPage = pd.goToCartPage();
	
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("india");
	    ConfirmationPage confirmationPage =checkoutpage.submitOrder();
	  
		String confirmMessage =confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
	  
	}

}
