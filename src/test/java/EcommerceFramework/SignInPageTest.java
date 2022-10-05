package EcommerceFramework;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AdPage;

import pageObject.CheckOutPage;
import pageObject.HeaderPage;
import pageObject.PurchasePage;
import pageObject.SignInPage;
import utils.BastTest;
import utils.RetryListenerTestNg;

public class SignInPageTest extends BastTest {

	public SignInPage signInPage;
    
	@Test(dataProvider= "getData",groups={"SignIn"})//,retryAnalyzer=RetryListenerTestNg.class
	public void signInUser(String emailTxt,String passwordTxt, Boolean isWrong) throws InterruptedException {
		signInPage = getSignInRef();
		clickSignInButton();
		String Msg = signInPage.registerUser(emailTxt, passwordTxt,isWrong);
		if(isWrong) {
			Assert.assertEquals(Msg.split("Authentication")[1]," failed..");
		}
		else
		{
		    Assert.assertEquals(getRegisName(), RegisteredUserName);
		}
	}
	
	


	
	
}
