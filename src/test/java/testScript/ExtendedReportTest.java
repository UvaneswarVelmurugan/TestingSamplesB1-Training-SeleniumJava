package testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commonUtils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtendedReportTest {
	WebDriver driver;
	
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void setupExtent() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\SparkReport.html");
		reports.attachReporter(spark);
	}
	
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	  public void seleniumSearchTest() {
		 
	        extentTest = reports.createTest("Selenium Search Test");	  
			driver.get("https://www.google.com/");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Selenium Tutorial");
			searchBox.sendKeys(Keys.ENTER);
			System.out.println("Page Title : "+ driver.getTitle());
			Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
			
	  }
	@Test
	  public void javaSearchTest() {
		 
		    extentTest = reports.createTest("Java Search Test");
			driver.get("https://www.google.com/");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Java Tutorial");
			searchBox.sendKeys(Keys.ENTER);
			System.out.println("Page Title : "+ driver.getTitle());
			Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search Page");
			
	  }
	@AfterMethod
	  public void tearout(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus()) {
			extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			String strPath = Utility.getScreenshotPath(driver);
			extentTest.addScreenCaptureFromPath(strPath);
		}
	
	  }
	@AfterTest
	public void finishExtent() {
		reports.flush();
	}
}
