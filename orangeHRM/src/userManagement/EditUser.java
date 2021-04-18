package userManagement;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import orangeHRM.ReadExcel;
import orangeHRM.Setup;

@Test(testName = "Edit User Details")
public class EditUser {
	
	@Test (dataProvider = "editUserData")
	public void editUser(String uName, String uRole, String eName, String uNameNew, String status, String pwChange, String newPw, String enabledFlag) throws InterruptedException {
		
		if (enabledFlag.equals("No")) { 
			throw new SkipException("Skipping disabled data."); 
		}
		
		WebDriver driver = Setup.getDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		
		Thread.sleep(1000);
		
		ITestResult dataObj = Reporter.getCurrentTestResult();
		String data = "Edit User Test case for " + uName;
		dataObj.setAttribute("tdata", data);
		
		Actions a = new Actions(driver);
		WebElement adminTab = driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b"));
		WebElement userManagementTab = driver.findElement(By.xpath("//*[@id=\"menu_admin_UserManagement\"]"));
		WebElement users = driver.findElement(By.xpath("//*[@id=\"menu_admin_viewSystemUsers\"]"));
		a.moveToElement(adminTab).moveToElement(userManagementTab).moveToElement(users).click().build().perform();
		
		driver.findElement(By.xpath("//*[@id=\"searchSystemUser_userName\"]")).sendKeys(uName);
		driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")).click();
		String confirmName = driver.findElement(By.xpath("//*[@id=\"systemUser_userName\"]")).getText();
		//assertEquals(confirmName, uName);
		
		driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
		
		Select editUserRole = new Select (driver.findElement(By.xpath("//*[@id=\"systemUser_userType\"]")));
		editUserRole.selectByVisibleText(uRole);
		
		driver.findElement(By.xpath("//*[@id=\"systemUser_employeeName_empName\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"systemUser_employeeName_empName\"]")).sendKeys(eName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[4]/ul/li[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"systemUser_userName\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"systemUser_userName\"]")).sendKeys(uNameNew);
		
		Select editUserStatus = new Select (driver.findElement(By.xpath("//*[@id=\"systemUser_status\"]")));
		editUserStatus.selectByVisibleText(status);
		
		if (pwChange.equals("Yes")) {
			driver.findElement(By.xpath("//*[@id=\"systemUser_chkChangePassword\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"systemUser_password\"]")).sendKeys(newPw);
			driver.findElement(By.xpath("//*[@id=\"systemUser_confirmPassword\"]")).sendKeys(newPw);
		}
	}
	
    @DataProvider (name = "editUserData")
    public Object[][] Authentication() throws Exception{
         Object[][] testObjArray = ReadExcel.getTableArray("EditUser");
         return (testObjArray);
    }
}
