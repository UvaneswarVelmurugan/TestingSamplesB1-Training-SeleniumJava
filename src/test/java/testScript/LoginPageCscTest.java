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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageCscTest {
	WebDriver driver;
	
  @BeforeMethod
  public void setup() {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  
  }
  @Test(dataProvider="loginData")
  public void validLoginTest(String strUser, String strPwd) throws InvalidFormatException, IOException, ParserConfigurationException, SAXException {
	  driver.get("http://the-internet.herokuapp.com/login");
	  driver.findElement(By.xpath(readXmlData("uname"))).sendKeys(strUser);
	  driver.findElement(By.id(readXmlData("pwd"))).sendKeys(strPwd);
	  driver.findElement(By.className(readXmlData("loginBtn"))).click();
	  boolean isDisp  = driver.findElement(By.cssSelector(readXmlData("successMsg"))).isDisplayed();
	  Assert.assertTrue(isDisp);
  }
  public String readData(String objName)throws InvalidFormatException, IOException {
		String objPath="";
		String path = System.getProperty("user.dir") +"//src//test//resources//testData//excelData.xlsx.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
		XSSFSheet sheet = workbook.getSheet("loginPage");
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
		String path = System.getProperty("user.dir") +"//src//test//resources//testData//XmlObjectRepo.xml";
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = factory.newDocumentBuilder();
		Document document = build.parse(file);
		NodeList list = document.getElementsByTagName("objRep");
		Node node1 = list.item(0);
		Element elem = (Element)node1;
		return elem.getElementsByTagName(tagname).item(0).getTextContent();
		
	}
  @DataProvider(name="loginData")
  public Object[][] getData() throws CsvValidationException, IOException {
	  String path = System.getProperty("user.dir") +"//src//test//resources//testData//loginData.csv";
	  String[] cols;
	  CSVReader reader = new CSVReader(new FileReader(path));
	  ArrayList<Object> dataList = new ArrayList<Object>();
	  while((cols = reader.readNext()) !=null) {
		  Object[] record = {cols[0], cols[1]};
		  dataList.add(record);
	  }
	  return dataList.toArray(new Object[dataList.size()][]);
  }
 

  @AfterMethod
  public void tearDown() {
	  driver.close();
  }
}
