package myPackage;

import java.awt.Toolkit;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import library.SplashmathScreenshot;
public class Googly 
{
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	SplashmathScreenshot images;
	
	
	
	@Test
	public void openBrowser() throws Exception
	{
	//	Location where we need to store Report
		report = new ExtentReports("C:\\Users\\STUDYPAD\\workspace\\GooglyDemo\\Reports\\GoogleDemoReport.html");
		
		logger= report.startTest("Test001 VerifyPageTitle");
	
		
			System.setProperty("webdriver.chrome.driver","C:\\Users\\STUDYPAD\\Downloads\\sandeep\\jar\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();

			images=new SplashmathScreenshot(driver);
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenResolution = new Dimension((int) 
	                toolkit.getScreenSize().getWidth(), (int) 
	                toolkit.getScreenSize().getHeight());
	
				driver.manage().window().setSize(screenResolution);
				
				logger.log(LogStatus.INFO,"Browser Started...");
				
				driver.get("https://staging-1.splashmath.com");
				logger.log(LogStatus.INFO,"Application is Up and Running");
				
				
				String title = driver.getTitle();
				Assert.assertTrue(title.contains("Splash Math - Fun Math Practice for Grades 1-5 | Splash Math"));
			String location =	images.takeScreenshot("LandingPage", "abc");
	// To Tell Test is Pass
				logger.log(LogStatus.PASS,"Title Verified");
				
				
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
		String location =	images.takeScreenshot("LandingPage", result.getName());
			logger.log(LogStatus.FAIL, "Test Verification", location);
		}
		
		report.endTest(logger);
		report.flush();
// To open Report in the same browser
		driver.get("C:\\Users\\STUDYPAD\\workspace\\GooglyDemo\\Reports\\GoogleDemoReport.html");
	}
}
