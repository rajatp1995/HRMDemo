package orangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Setup {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
	
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajat\\eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
    @AfterSuite
    public void afterSuite() throws InterruptedException {
    	System.out.println("Results written into TestResult.xlsx file.");
    	Thread.sleep(2000);
        driver.quit();
    }
    
}
