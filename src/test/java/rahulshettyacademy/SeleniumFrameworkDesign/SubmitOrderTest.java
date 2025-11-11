package rahulshettyacademy.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjectModel.CartPage;
import rahulshettyacademy.pageObjectModel.CheckOutPage;
import rahulshettyacademy.pageObjectModel.ConfirmationPage;
import rahulshettyacademy.pageObjectModel.LandingPage;
import rahulshettyacademy.pageObjectModel.productCatalogue;
import rahulshettyacademy.reusableCode.OrderPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		/*productCatalogue pd = landingPage.loginApplication(email, password);
		
		List<WebElement> products =  pd.getProductList(); //gets product lists
		pd.addProductToCart(productName);//add product to cart
		CartPage cartPage = pd.goToCartPage();
	
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("india");
	    ConfirmationPage confirmationPage =checkoutpage.submitOrder();
	  
		String confirmMessage =confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));*/
		
		productCatalogue pd = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pd.getProductList();
		pd.addProductToCart(input.get("product"));
		CartPage cartPage = pd.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		productCatalogue pd = landingPage.loginApplication("emailname@gmail.com", "password");
		OrderPage ordersPage = pd.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<String,String> map = new HashMap<String,String>();
        map.put("email", "emailname@gmail.com");
		map.put("password", "password");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "emailname@gmail.com");
		map1.put("password", "password");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)} };
			
	}
	
}
