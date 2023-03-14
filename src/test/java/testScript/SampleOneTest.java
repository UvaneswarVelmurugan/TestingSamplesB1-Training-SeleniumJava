package testScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {
	
	WebDriver driver;
	@Parameters("browser1")
	@BeforeMethod
	public void setup(String strBrowser) {
		if(strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		} 
		if(strBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();	
		} 
		driver.manage().window().maximize();
		
	}
  @Test(alwaysRun=true, dependsOnMethods = "seleniumSearchTest")
  public void javaSearchTest() throws IOException {
		 
//		driver.get("https://www.google.com/");
	    String url = System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
	    Properties tempProp = new Properties();
		FileInputStream obtained = new FileInputStream(url);
		tempProp.load(obtained);
		
		url = tempProp.getProperty("url");
		driver.get(url);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		System.out.println("Page Title : "+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
		
  }
  @Test
  public void seleniumSearchTest() {
	 
	  
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		System.out.println("Page Title : "+ driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
		
  }
  
//  @Test(enabled=false)
//  public void cucumberSearchTest() {
	 
	  
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		searchBox.sendKeys("Cucumber Tutorial");
//		searchBox.sendKeys(Keys.ENTER);
//		System.out.println("Page Title : "+ driver.getTitle());
//		Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
		
//  }
  
//  @Test(priority=1)
//  public void javaSearchTest() {
//		 
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		searchBox.sendKeys("Java Tutorial");
//		searchBox.sendKeys(Keys.ENTER);
//		System.out.println("Page Title : "+ driver.getTitle());
//		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
//		
 // }
 // @Test
//  public void seleniumSearchTest() {
//	 
//	  
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		searchBox.sendKeys("Selenium Tutorial");
//		searchBox.sendKeys(Keys.ENTER);
//		System.out.println("Page Title : "+ driver.getTitle());
//		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
//		
 // }
 // @Test
 // public void seleniumSearch() {
//	 
//	  
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		SoftAssert softAssert = new SoftAssert();
///		
//		softAssert.assertEquals(driver.getTitle(), "Google Page");
//		searchBox.sendKeys("Selenium Tutorial");
//		searchBox.sendKeys(Keys.ENTER);
//		System.out.println("Page Title : "+ driver.getTitle());
//		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
//		softAssert.assertAll();
//		
 // }
  @AfterMethod
  public void teardown() {
	  driver.close();
  }
  
}
