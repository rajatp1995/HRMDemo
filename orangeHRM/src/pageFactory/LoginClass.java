package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginClass {
	final WebDriver driver;
	 
	public LoginClass(WebDriver driver){
		this.driver=driver;
	}
	
	@FindBy(xpath="//*[@id=\"txtUsername\"]")
	WebElement userNameBox;
	
	@FindBy(xpath="//*[@id=\"txtPassword\"]")
	WebElement passwordBox;
	
	@FindBy(xpath="//*[@id=\"btnLogin\"]")
	WebElement loginButton;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/h1")
	WebElement getHomePageText;
	
	public String getWebsiteTitle() {
		return driver.getTitle();
	}
	
	public void loginAction (String uName, String uPass) {
		userNameBox.sendKeys(uName);
		passwordBox.sendKeys(uPass);
		loginButton.click();
	}
	
	public String getConfirmationText() {
		return getHomePageText.getText();
	}
}
