package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class HeaderPage extends AbstractComponents{
    public WebDriver driver;
	public HeaderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".login")
	WebElement signInButton;
	
	@FindBy(css=".account span")
	WebElement accountName;
	
	By accNam = By.cssSelector(".account");
	
	@FindBy(xpath="//div[@id='block_top_menu']/ul/li/a[@title='Dresses']")
	WebElement dressButton;
	
	@FindBy(xpath="//div[@id='block_top_menu']/ul/li/a[@title='Dresses']/following-sibling::ul/li/a[@title='Summer Dresses']")
	WebElement CasDressBut;
	
	@FindBy(xpath="//div[@class='shopping_cart']/a")
	WebElement headerCart;
	
	@FindBy(xpath="//a[@id='button_order_cart']")
	WebElement checkOutButton;
	
	
	public void clickSigninButton() {
		signInButton.click();
	}
	
	public String getRegisteredName()
	{
		VisibilityCheck(accNam);
		return accountName.getText();
	}
	
	
	public void mouseOverDresses()
	{
		Actions act = new Actions(driver);
		act.moveToElement(dressButton).build().perform();
		CasDressBut.click();
	}
	
	public void placeOrderFromCart()
	{
		Actions act1 = new Actions(driver);
		act1.moveToElement(headerCart).build().perform();
		checkOutButton.click();
	}
}
