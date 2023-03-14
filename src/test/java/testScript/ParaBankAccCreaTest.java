package testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParaBankAccCreaTest {
	WebDriver driver;
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
 @Test
 public void Register() {
	  driver.get("https://parabank.parasoft.com/parabank/index.htm");
	  driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
	  driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Uvaneswar");
	  driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys("Velmurugan");
	  driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("130,MGR Street");
	  driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Neyveli");
	  driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Cuddalore");
	  driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("607308");
	  driver.findElement(By.xpath("//input[@id='customer.phoneNumber']")).sendKeys("9876543210");
	  driver.findElement(By.xpath("//input[@id='customer.ssn']")).sendKeys("XXX");
	  driver.findElement(By.xpath("//input[@id='customer.username']")).sendKeys("Uvan");
	  driver.findElement(By.xpath("//input[@id='customer.password']")).sendKeys("Uvan632");
	  driver.findElement(By.xpath("//input[@id='repeatedPassword']")).sendKeys("Uvan632");
	  driver.findElement(By.xpath("//input[@value='Register']")).click();
	  
  }
  @Test(priority=1,dependsOnMethods="Register")
  public void LoginTest() {
	  driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm");
	  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Uvan");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Uvan632");
	  driver.findElement(By.xpath("//input[@value='Log In']")).click();
  }
  @Test(priority=2)
  public void OpenAccount() {
	//  driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm");
	  driver.findElement(By.xpath("//a[contains(text(),'Open New Account')]")).click();
	  Select sel = new Select(driver.findElement(By.xpath("//select[@id='type']")));
	  sel.selectByVisibleText("SAVINGS");
	  driver.findElement(By.xpath("//div/input[@class='button']")).click();
  }
  @Test(priority=3)
  public void TranscationTest() throws InterruptedException {
	  
	  driver.navigate().to("https://parabank.parasoft.com/parabank/transfer.htm");
	  driver.navigate().refresh();
	  driver.findElement(By.xpath("//form//p/input")).sendKeys("100");
	  Select seltransfer = new Select(driver.findElement(By.xpath("\"//select[@id='toAccountId']\"")));
	  seltransfer.selectByVisibleText("13788");
	  driver.findElement(By.xpath("//div/input[@class='button']")).click();
	  Thread.sleep(2000);
  }
  @Test(priority=4)
  public void AccountOverView() {
	//  driver.navigate().to("https://parabank.parasoft.com/parabank/transfer.htm");
	  driver.findElement(By.xpath("//a[contains(text(),'Accounts Overview')]")).click();
	  driver.findElement(By.xpath("//a[@class='ng-binding']")).click();
//	  Select seltransfer = new Select(driver.findElement(By.xpath("//td/select[@id='month']")));
//	  seltransfer.selectByVisibleText("Feburuary");
//	  driver.findElement(By.xpath("//td/input[@class='button']")).click();
  }
  @AfterClass
  public void tearout() {
  	  driver.close();
  }
 
}
