package rahulshettyacademy.reusableCode;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.pageObjectModel.CheckOutPage;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
    //List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
    
    @FindBy(css="tr td:nth-child(3)")
	private List <WebElement> productNames;
    
    //driver.findElement(By.cssSelector(".totalRow button")).click();
    @FindBy(css=".totalRow button")
	private WebElement checkoutEle;
    
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//check if product is present in the cart page
   public  Boolean VerifyOrderDisplay(String productName) {
	   Boolean match = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
	return match;
	   
   }
	
}
