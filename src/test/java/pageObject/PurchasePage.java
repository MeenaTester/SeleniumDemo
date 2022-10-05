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

public class PurchasePage extends AbstractComponents{
    public WebDriver driver;
    public int totaldressInCart=0;
	public PurchasePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='product_list grid row']/li")
	List<WebElement> DressList;
	
	
	@FindBy(xpath="//div[@id='layer_cart']")
	WebElement CartBox;
	
	@FindBy(xpath="//div[@id='layer_cart']/div/div/span[@title='Close window']")
	WebElement CartClose;
	
	
	public int callPurchaseItem(String dressToSelect) throws InterruptedException
	{
		Actions act = new Actions(driver);
		 for(int i =1;i<=DressList.size();i++)
		    {
			 System.out.println("2 "+dressToSelect);
			 String dressDescr =driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']/h5/a")).getText();
			 if(dressDescr.equalsIgnoreCase(dressToSelect)) {
		    		WebElement selectedItemElement = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']"));
		    		act.moveToElement(selectedItemElement).build().perform();
		    		driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[@class='right-block']/div[@class='button-container']/a[@title='Add to cart']")).click();
		    		totaldressInCart++;
		    		
		    		VisibilityWebElementCheck(CartBox);
		    		CartClose.click();
		    	
		    	}
		    }
		 return totaldressInCart;
	}
	
	
	
	

}
