package testScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchBookTest {
	WebDriver driver;
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
  @Test(priority=1)
  public void SignUp() throws IOException{
//	  driver.get("https://danube-webshop.herokuapp.com/");
	  String url = System.getProperty("user.dir")+"//src//test//resources//configFiles//book.properties";
	    Properties tempProp = new Properties();
		FileInputStream obtained = new FileInputStream(url);
		tempProp.load(obtained);
		url = tempProp.getProperty("url");
	  driver.get(url);
	  driver.findElement(By.xpath("//button[@id='signup']")).click();
	  driver.findElement(By.xpath("//input[@id='s-name']")).sendKeys("Uvaneswar");
	  driver.findElement(By.xpath("//input[@id='s-surname']")).sendKeys("Velmurugan");
	  driver.findElement(By.xpath("//input[@id='s-email']")).sendKeys("uvaneswarvelmurugan@gmail.com");
	  driver.findElement(By.xpath("//input[@id='s-password2']")).sendKeys("Uvan632");
	  driver.findElement(By.xpath("//input[@id='s-company']")).sendKeys("Zuci");
	  driver.findElement(By.xpath("//input[@id='myself']")).click();
	  driver.findElement(By.xpath("//input[@id='marketing-agreement']")).click();
	  driver.findElement(By.xpath("//input[@id='privacy-policy']")).click();
	  driver.findElement(By.xpath("//button[@id='register-btn']")).click();
  }
  @Test(priority=2)
  public void Search() throws IOException{
//	  driver.get("https://danube-webshop.herokuapp.com/");
	    String name = System.getProperty("user.dir")+"//src//test//resources//configFiles//book.properties";
	    Properties tempProp = new Properties();
		FileInputStream obtained = new FileInputStream(name);
		tempProp.load(obtained);
		name = tempProp.getProperty("name");
		driver.findElement(By.xpath("//input[@name='searchbar']")).sendKeys(name);
		driver.findElement(By.xpath("//button[@id='button-search']")).click();
//		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//p[@class='preview-price']")).click();
  }
  @Test(priority=3)
  public void AddCart() {
		driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
  }
  @Test(priority=4)
  public void Buy() {
	  driver.findElement(By.xpath("//input[@id='s-name']")).sendKeys("Uvaneswar");
	  driver.findElement(By.xpath("//input[@id='s-surname']")).sendKeys("Velmurugan");
	  driver.findElement(By.xpath("//input[@id='s-address']")).sendKeys("130, MGR Street");
	  driver.findElement(By.xpath("//input[@id='s-zipcode']")).sendKeys("607308");
	  driver.findElement(By.xpath("//input[@id='s-city']")).sendKeys("Neyveli");
	  driver.findElement(By.xpath("//input[@id='s-company']")).sendKeys("Zuci");
	  driver.findElement(By.xpath("//input[@id='asap']")).click();
	  driver.findElement(By.xpath("//button[contains(text(),'Buy')]")).click();
  }
  @AfterClass
  public void tearout() {
  	  driver.close();
  }
  
}
