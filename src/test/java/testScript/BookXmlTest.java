package testScript;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookXmlTest {
	WebDriver driver;
	
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void setupExtent() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\DanubeSparkReport.html");
		reports.attachReporter(spark);
	}
	
  @BeforeMethod
  public void setup() {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));		  
  }
  @Test(dataProvider="bookTest")
  public void validBookName(String strCustName,String strSurName,String strEmail,String strPwd,String strComp,String strName,String strAdd,String strZip,String strCity) throws InvalidFormatException, IOException, ParserConfigurationException, SAXException {
	  extentTest = reports.createTest("Danube User Test "+strCustName);
	  driver.get("https://danube-webshop.herokuapp.com/");
	  
	  driver.findElement(By.xpath(readXmlData("signUpBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("userName"))).sendKeys(strCustName);
	  driver.findElement(By.xpath(readXmlData("uSurName"))).sendKeys(strSurName);
	  driver.findElement(By.xpath(readXmlData("eMail"))).sendKeys(strEmail);
	  driver.findElement(By.xpath(readXmlData("pwd"))).sendKeys(strPwd);
	  driver.findElement(By.xpath(readXmlData("comp"))).sendKeys(strComp);
	  driver.findElement(By.xpath(readXmlData("myselfBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("markAgreeBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("privacyBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("registerBtn"))).click();
	  
	  driver.findElement(By.xpath(readXmlData("bookName"))).sendKeys(strName);
	  driver.findElement(By.xpath(readXmlData("searchBtn"))).click();
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.findElement(By.xpath(readXmlData("bookBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("cartBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("checkBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("userName"))).sendKeys(strCustName);
	  driver.findElement(By.xpath(readXmlData("uSurName"))).sendKeys(strSurName);
	  driver.findElement(By.xpath(readXmlData("add"))).sendKeys(strAdd);
	  driver.findElement(By.xpath(readXmlData("zip"))).sendKeys(strZip);
	  driver.findElement(By.xpath(readXmlData("city"))).sendKeys(strCity);
	  driver.findElement(By.xpath(readXmlData("comp"))).sendKeys(strComp);
	  driver.findElement(By.xpath(readXmlData("asapBtn"))).click();
	  driver.findElement(By.xpath(readXmlData("buyBtn"))).click();
		
	  boolean isDisp  = driver.findElement(By.xpath(readXmlData("successMsg"))).isDisplayed();
	  Assert.assertTrue(isDisp);
  }
  public String readData(String objName) throws InvalidFormatException, IOException {
		String objPath="";
		String path = System.getProperty("user.dir") +"//src//test//resources//testData//bookData.xlsx.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
		XSSFSheet sheet = workbook.getSheet("bookName");
		int numRows = sheet.getLastRowNum();
		for(int i = 1; i <= numRows;i++) {
			XSSFRow row = sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
				objPath = row.getCell(1).getStringCellValue();
			}
		}
		return objPath;
	}
  public String readXmlData(String tagname) throws ParserConfigurationException, SAXException, IOException {
		String path = System.getProperty("user.dir") +"//src//test//resources//testData//bookTest.xml";
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = factory.newDocumentBuilder();
		Document document = build.parse(file);
    	NodeList list = document.getElementsByTagName("objRep");
    	Node node1 = list.item(0);
		Element elem = (Element)node1;
		return elem.getElementsByTagName(tagname).item(0).getTextContent();
		
	}
  @DataProvider(name="bookTest")
  public Object[][] getData() throws CsvValidationException, IOException {
	  String path = System.getProperty("user.dir") +"//src//test//resources//testData//danube.csv";
	  String[] cols;
	  CSVReader reader = new CSVReader(new FileReader(path));
	  ArrayList<Object> dataList = new ArrayList<Object>();
	  while((cols = reader.readNext()) !=null) {
		  Object[] record = {cols[0], cols[1], cols[2], cols[3], cols[4], cols[5], cols[6], cols[7], cols[8]};
		  dataList.add(record);
	  }
	  return dataList.toArray(new Object[dataList.size()][]);
  }
  @AfterMethod
  public void tearout(ITestResult result) {
	if(ITestResult.FAILURE == result.getStatus()) {
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	}
  	  driver.close();
  }
  @AfterTest
	public void finishExtent() {
		reports.flush();
	}
}
