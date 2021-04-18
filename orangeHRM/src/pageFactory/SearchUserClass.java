package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchUserClass {
	final WebDriver driver;
	
	public SearchUserClass(WebDriver driver){
		this.driver=driver;
	}
	
	@FindBy(xpath="//*[@id=\"menu_admin_viewAdminModule\"]/b")
	WebElement adminTab;
	
	@FindBy(xpath="//*[@id=\"menu_admin_UserManagement\"]")
	WebElement userManagementTab;
	
	@FindBy(xpath="//*[@id=\"menu_admin_viewSystemUsers\"]")
	WebElement users;
	
	public void goToUsers() {
		Actions a = new Actions(driver);
		a.moveToElement(adminTab).moveToElement(userManagementTab).moveToElement(users).click().build().perform();
	}
	
	@FindBy(xpath="//*[@id=\"searchSystemUser_userName\"]")
	WebElement uName;
	
	@FindBy(xpath="//*[@id=\"searchSystemUser_userType\"]")
	WebElement uRole;
	
	@FindBy(xpath="//*[@id=\"searchSystemUser_employeeName_empName\"]")
	WebElement eName;
	
	@FindBy(xpath="/html/body/div[4]/ul/li[1]")
	WebElement eNameSelect;
	
	@FindBy(xpath="//*[@id=\"searchSystemUser_status\"]")
	WebElement uStatus;
	
	public void enterSearchDetails(String name, String role, String e_name, String status) throws InterruptedException {
		uName.sendKeys(name);
		
		Select userRole = new Select(uRole);
		userRole.selectByVisibleText(role);
		
		eName.sendKeys(e_name);
		Thread.sleep(1000);
		eNameSelect.click();
		
		Select userStatus = new Select(uStatus);
		userStatus.selectByVisibleText(status);
	}
	
	@FindBy(xpath="//*[@id=\"searchBtn\"]")
	WebElement sButton;
	
	@FindBy(xpath="//*[@id=\"resetBtn\"]")
	WebElement rButton;
	
	public void SearchUser() {
		sButton.click();
	}
	
	public void ResetSearch() {
		rButton.click();
	}

}
