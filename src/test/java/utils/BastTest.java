package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AdPage;

import pageObject.CheckOutPage;
import pageObject.HeaderPage;
import pageObject.PurchasePage;
import pageObject.SignInPage;

public class BastTest {
	public WebDriver driver;
	public AdPage adPage;
	public HeaderPage headerPage;
	public SignInPage signInPage;
	public PurchasePage purchasePage;
	public CheckOutPage checkOutPage;
	
	public static String emailTxt;
	public static String passwordTxt;
	public static String RegisteredUserName;
	public static String dressToSelect;
	public int totaldressInCart=0;
	public int totaldressInCheckOut=0;
	
	public WebDriver initialize() throws IOException
	{
		
		File filePath = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\global.properties");
		FileInputStream fis = new FileInputStream(filePath);
		Properties prop = new Properties();
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		emailTxt = prop.getProperty("emailId");
		passwordTxt = prop.getProperty("password");
		RegisteredUserName = prop.getProperty("userName");
		dressToSelect = prop.getProperty("dressToSelect");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		
		return driver;
		
	}
	@BeforeMethod(alwaysRun=true)
	public HeaderPage getDriverInstance() throws IOException
	{
		driver = initialize();
		headerPage = new HeaderPage(driver);
		totaldressInCart=0;
		return headerPage;
	}
	public AdPage getAdPageInstance()
	{
		adPage = new AdPage(driver);
		return adPage;
	}
	public SignInPage getSignInRef()
	{
		signInPage = new SignInPage(driver);
		
		return signInPage;
	}
	public void clickSignInButton()
	{
		headerPage.clickSigninButton();
	}
	public PurchasePage getPurchasePageRef()
	{
		purchasePage = new PurchasePage(driver);
		
		headerPage.mouseOverDresses();
		return purchasePage;
		
	}
	
	public CheckOutPage getCheckOutPageRef() {
		checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
	
	public void PlaceOrder()
	{
		headerPage.placeOrderFromCart();
	}
	
	public String getRegisName()
	{
		return headerPage.getRegisteredName();
	}
	
	public String getSCreenShot(String testCaseName,WebDriver driver1) throws IOException
	{
		
		TakesScreenshot ts = (TakesScreenshot)driver1;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+".\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, destFile);
		return System.getProperty("user.dir")+".\\reports\\"+testCaseName+".png";
	}
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][]  {{"revana10@gmail.com","Texas@123",false}, {"wrongEmail@gmail.com","Texas@123",true} };
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
		//driver=null;
	}
	

}
