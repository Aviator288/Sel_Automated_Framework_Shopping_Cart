package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjectModel.CartPage;
import rahulshettyacademy.pageObjectModel.CheckOutPage;
import rahulshettyacademy.pageObjectModel.ConfirmationPage;
import rahulshettyacademy.pageObjectModel.productCatalogue;

public class ErrorValidations extends BaseTest {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		//String productName = "ZARA COAT 3";
		productCatalogue pd = landingPage.loginApplication("bluejohnson1234@gmail.com", "GOD4ever!!");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		productCatalogue pd = landingPage.loginApplication("blacksovereign467@gmail.com", "GOD4ever@");
		
		List<WebElement> products =  pd.getProductList(); //gets product lists
		pd.addProductToCart(productName);//add product to cart
		CartPage cartPage = pd.goToCartPage();
	
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}
