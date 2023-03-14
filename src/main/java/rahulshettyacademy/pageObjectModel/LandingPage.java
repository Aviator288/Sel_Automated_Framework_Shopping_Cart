package rahulshettyacademy.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.reusableCode.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	

	@FindBy(css="[class*='flyInOut']")
	WebElement errMsg;
	
	//Action methods
	public productCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		productCatalogue pd = new productCatalogue(driver);
		return pd;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errMsg);
		return errMsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
