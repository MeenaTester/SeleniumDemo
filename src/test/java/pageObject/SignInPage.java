package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class SignInPage extends AbstractComponents{
    public WebDriver driver;
	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(id="email")
	WebElement emailElem;
	
	@FindBy(id="passwd")
	WebElement pwdElem;
	
	@FindBy(id="SubmitLogin")
	WebElement logbutton;
	
	@FindBy(css="div[class*='danger']")
	WebElement ErrorMsg;
	
	public String registerUser(String email,String pwd,Boolean isWrong) throws InterruptedException
	{
		VisibilityWebElementCheck(emailElem);
		emailElem.sendKeys(email);
		pwdElem.sendKeys(pwd);
		logbutton.click();
		if(isWrong)
		{
			System.out.println("ErrorMsg "+ErrorMsg.getText());
			return ErrorMsg.getText();
		}
		return "";
		
	}
	

}
