package EcommerceFramework;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestCases {
	public static WebDriver driver;
	public static WebDriverWait expWait;

	public static String emailId = "revana10@gmail.com";
	public static String password = "Texas@123";
	public static String userName = "Meena Kasi";
	public static String dressToSelect = "Printed Summer Dress";
	public static int totaldressInCart = 0;
	public static int totaldressInCheckOut = 0;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		expWait = new WebDriverWait(driver, Duration.ofSeconds(130));
		//callAd();
		//callSignInPage();
		PurchaseItem();

	}

	public static void callAd() {
		WebElement AdPoster = driver.findElement(By.cssSelector(".item-img"));
		System.out.println("callAd");
		expWait.until(ExpectedConditions.visibilityOf(AdPoster));
		AdPoster.click();
		System.out.println("AdPoster " + driver.getCurrentUrl());
		driver.navigate().back();
	}

	public static void callSignInPage() {
		WebElement signInButton = driver.findElement(By.cssSelector(".login"));
		signInButton.click();

		WebElement emailTxt = driver.findElement(By.id("email"));
		expWait.until(ExpectedConditions.visibilityOf(emailTxt));
		WebElement passwordTxt = driver.findElement(By.id("passwd"));
		WebElement logbutton = driver.findElement(By.id("SubmitLogin"));
		emailTxt.sendKeys("revana10@gmail.com");
		passwordTxt.sendKeys("Texas@123");
		logbutton.click();
		By accountName = By.cssSelector(".account");
		expWait.until(ExpectedConditions.visibilityOfElementLocated(accountName));
		String userNameInSignPage = driver.findElement(By.cssSelector(".account span")).getText();
		System.out.println("userNameInSignPage : " + userNameInSignPage);

	}

	public static void PurchaseItem() throws InterruptedException
	{
		WebElement dressButton  = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li/a[@title='Dresses']"));
		Actions act = new Actions(driver);
		act.moveToElement(dressButton).build().perform();
		WebElement CasDressBut = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li/a[@title='Dresses']/following-sibling::ul/li/a[@title='Summer Dresses']"));
		CasDressBut.click();
	    List<WebElement> DressList = driver.findElements(By.xpath("//ul[@class='product_list grid row']/li"));
	   
	    for(int i =0;i<DressList.size();i++)
	    {
	    	String src1 = DressList.get(i).findElement(By.xpath("//div/div[@class='right-block']/h5/a")).getAttribute("href");
	    	System.out.println(i + " ::::::::::::: "+src1);
	    }
	    for(int i =1;i<=DressList.size();i++)
	    {
	    	String src =driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']/h5/a")).getAttribute("href");
	    	
	    	String dressDescr =driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']/h5/a")).getText();
	    	if(dressDescr.equalsIgnoreCase(dressToSelect)) {
	    		WebElement selectedItemElement = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']"));
	    		act.moveToElement(selectedItemElement).build().perform();
	    		driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']/div[@class='button-container']/a[@title='Add to cart']")).click();
	    		totaldressInCart++;
	    		
	    		expWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='layer_cart']"))));
	    		WebElement selectedLayOut =driver.findElement(By.xpath("//div[@class='layer_cart_product_info']"));
	    		driver.findElement(By.xpath("//div[@id='layer_cart']/div/div/span[@title='Close window']")).click();
	    	
	    	}
	    }
		WebElement headerCart = driver.findElement(By.xpath("//div[@class='shopping_cart']/a"));
	    act.moveToElement(headerCart).build().perform();
	    WebElement checkOutButton = driver.findElement(By.xpath("//a[@id='button_order_cart']"));
	    checkOutButton.click();
	   // callCheckOutpage();
	}
	public static void callCheckOutpage()
	{
		
		WebElement proceedToCheckOutButton = driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']"));
		
		List<WebElement> cartList = driver.findElements(By.xpath("//table[@id='cart_summary']/tbody/tr"));
		System.out.println("cartList "+cartList.size());
		for(int i =0;i<cartList.size();i++)
		{
			int j=i+1;
			String cartItemString = driver.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr["+j+"]/td[@class='cart_description']/p/a")).getText();
			if(cartItemString.equalsIgnoreCase(dressToSelect))
			{
				System.out.println("cartItemString true "+cartItemString);
				totaldressInCheckOut++;
			}
			else
			{
				System.out.println("cartItemString false "+cartItemString);
			}
			
		
		}
		if(totaldressInCart==totaldressInCheckOut)
		{
			System.out.println("all items added in checkout page");
		}
		else
		{
			System.out.println("error in checkout page");
		}
		
		proceedToCheckOutButton.click();
		//WebElement proceedToCheckOutButtonDub =driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/button"));
		driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/button")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/button")).click();
		driver.findElement(By.xpath("//a[@class='cheque']")).click();
		callPaymentPage();
	}
   public static void callPaymentPage()
   {
	   driver.findElement(By.xpath("//p[@id='cart_navigation']/button")).click();
	   System.out.println("order confirmation :  "+driver.findElement(By.cssSelector("p[class*='alert']")).getText());
	   
   }
}

