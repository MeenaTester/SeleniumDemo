package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class AdPage extends AbstractComponents{
    public WebDriver driver;
	public AdPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".item-img")
	WebElement AdPoster;
	
	public String callAdPoster() throws InterruptedException
	{
		VisibilityWebElementCheck(AdPoster);
		AdPoster.click();
		String navigatedURL = driver.getCurrentUrl();
		System.out.println("AdPoster URL ::: " + navigatedURL);
		driver.navigate().back();
		return navigatedURL;
	}

}
