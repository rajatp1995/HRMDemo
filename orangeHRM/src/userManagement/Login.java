package userManagement;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import orangeHRM.ReadExcel;
import orangeHRM.Setup;
import pageFactory.LoginClass;

@Test(testName="Login Test")
public class Login{
	
	@Test (dataProvider = "logindata")
	public void login(String username, String password, String enabledFlag) {
		
		if (enabledFlag.equals("No")) { 
			throw new SkipException("Skipping disabled data."); 
		}
		 
		WebDriver driver = Setup.getDriver();
		LoginClass loginClass = PageFactory.initElements(driver, LoginClass.class);
		
		assertEquals(loginClass.getWebsiteTitle(), "OrangeHRM");
		loginClass.loginAction(username, password);
		assertEquals(loginClass.getConfirmationText(), "Dashboard");
		
		ITestResult dataObj = Reporter.getCurrentTestResult();
		String data = "Login test case for " + username;
		dataObj.setAttribute("tdata", data);
	}
	
    @DataProvider (name = "logindata")
    public Object[][] Authentication() throws Exception{
         Object[][] testObjArray = ReadExcel.getTableArray("Login");
         return (testObjArray);
    }
}
