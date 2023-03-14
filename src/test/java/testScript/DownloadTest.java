package testScript;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadTest {
	WebDriver driver;
  @Test
  public void downloadfile() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
	  
	  ChromeOptions options = new ChromeOptions();
	  Map<String, Object> prefs = new HashMap<String, Object>();
	  prefs.put("download.prompt_for_download", false);
	  options.setExperimentalOption("prefs", prefs);
	  driver = new ChromeDriver(options);
	  
	  driver.manage().window().maximize();
	  driver.get("https://chromedriver.storage.googleapis.com/index.html?path=79.0.3945.36/");
	  Thread.sleep(2000);
	  WebElement btnDownload = driver.findElement(By.xpath(".//a[text()='chromedriver_win32.zip']"));
	  btnDownload.click();
	  Thread.sleep(7000);
  }
}
