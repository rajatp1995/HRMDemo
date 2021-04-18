package userManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import orangeHRM.ReadExcel;
import orangeHRM.Setup;
import pageFactory.LoginClass;
import pageFactory.SearchUserClass;

//@Listeners(orangeHRM.ListenerClass.class)

@Test(testName = "Search User Details")
public class SearchUser {

	@Test (dataProvider = "searchUserData", testName = "Seach for a User")
	public void searchForUser(String uName, String uRole, String eName, String status, String enabledFlag) throws InterruptedException {
		
		if (enabledFlag.equals("No")) { 
			throw new SkipException("Skipping disabled data."); 
		}
		
		WebDriver driver = Setup.getDriver();
		SearchUserClass searchUser = PageFactory.initElements(driver, SearchUserClass.class);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		Thread.sleep(1000);
		
		searchUser.goToUsers();	
		searchUser.enterSearchDetails(uName, uRole, eName, status);
		searchUser.SearchUser();
		searchUser.ResetSearch();
		
		ITestResult dataObj = Reporter.getCurrentTestResult();
		String data = "Search User Test case for " + uName;
		dataObj.setAttribute("tdata", data);
	}
	
    @DataProvider (name = "searchUserData")
    public Object[][] Authentication() throws Exception{
         Object[][] testObjArray = ReadExcel.getTableArray("SearchUser");
         return (testObjArray);
    }
}
