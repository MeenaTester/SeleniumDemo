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

public class PurchasePageTest extends BastTest {
	public PurchasePage purchasePage;
	public CheckOutPage checkOutPage;

    
	
	@Test(dataProvider= "getData",groups={"Purchase"})
	public void PurchaseDress(String emailTxt,String passwordTxt, Boolean isWrong) throws InterruptedException {
		
		purchasePage = getPurchasePageRef();
		totaldressInCart = purchasePage.callPurchaseItem(dressToSelect);
		
		PlaceOrder();
		checkOutPage = getCheckOutPageRef();
		totaldressInCheckOut = checkOutPage.callCheckOutPage(dressToSelect);
		Assert.assertEquals(totaldressInCheckOut, totaldressInCart);
		
		signInPage = getSignInRef();
		String Msg = signInPage.registerUser(emailTxt, passwordTxt,isWrong);
		if(isWrong) {
			Assert.assertEquals(Msg.split("Authentication")[1]," failed.");
		}
		else
		{
		    Assert.assertEquals(getRegisName(), RegisteredUserName);
		    checkOutPage.callCompleteCheckOut();
			String confirmMsg = checkOutPage.callPaymentPage();
			Assert.assertEquals(confirmMsg, "Your order on My Store is complete.");
		}
		
		
		
	}
}
