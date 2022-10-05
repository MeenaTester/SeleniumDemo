package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
    public WebDriver driver;
 
    public int totaldressInCheckOut=0;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")
	WebElement proceedToCheckOutButton;
	
	@FindBy(xpath="//table[@id='cart_summary']/tbody/tr")
	List<WebElement> cartList;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']/button")
	WebElement proceedToCheckOutButton1;
	
	@FindBy(id="cgv")
	WebElement termsAgree;
	
	@FindBy(xpath="//a[@class='cheque']")
	WebElement paymentButton;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']/button")
	WebElement proceedToCheckOutButton2;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']/button")
	WebElement confirmOrder;
	
	@FindBy(xpath="p[class*='alert']")
	WebElement confirmPageAlert;

	public int callCheckOutPage(String dressToSelect)
	{
		for(int i =0;i<cartList.size();i++)
		{
			int j=i+1;
			String cartItemString = driver.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr["+j+"]/td[@class='cart_description']/p/a")).getText();
			if(cartItemString.equalsIgnoreCase(dressToSelect))
			{
				totaldressInCheckOut++;
			}
			
		}
		proceedToCheckOutButton.click();
		return totaldressInCheckOut;
	}
	
	public void callCompleteCheckOut() {
		
        proceedToCheckOutButton1.click();
        termsAgree.click();
        proceedToCheckOutButton2.click();
        paymentButton.click();
	}
	
	public String callPaymentPage() throws InterruptedException
	{
		confirmOrder.click();
		
		return driver.findElement(By.cssSelector("p[class*='alert']")).getText();
	}
	
	
	
	

}
