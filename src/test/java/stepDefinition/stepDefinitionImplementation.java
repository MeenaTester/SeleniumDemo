package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AdPage;
import pageObject.CheckOutPage;
import pageObject.PurchasePage;
import pageObject.SignInPage;
import utils.BastTest;

public class stepDefinitionImplementation extends BastTest{
	public AdPage adPage;
	String navigatedURL;
	
	public SignInPage signInPage;
	String MsgFromSignIn;
	Boolean isWrong;
	
	public PurchasePage purchasePage;
	public CheckOutPage checkOutPage;
	String PurchaseErrMsg;
	
@Given("Landed on Your Logo Website")
public void Landed_on_Your_Logo_Website() throws IOException
{
	getDriverInstance();
}

//....................................................AdPoster............................................
@Given("Clicked on Adposter")
public void Clicked_on_Adposter() throws InterruptedException
{
	adPage = getAdPageInstance();
	
}
@When("Navigate to correct Ad page")
public void Navigate_to_correct_Ad_page() throws InterruptedException
{
	navigatedURL=adPage.callAdPoster();
}
@Then("Navigate back to Your Logo URL")
public void Navigate_back_to_Your_Logo_URL()
{
	Assert.assertEquals(navigatedURL, "https://www.prestashop.com/en");
}

//....................................................SignIn............................................
@Given("Click on sign In button")
public void Click_on_sign_In_button() {
	signInPage = getSignInRef();
	clickSignInButton();
}
@When("^Give (.+) and (.+) and (.+) and click signIn button$")
public void Give_and_and_and_click_signIn_button(String emailTxt,String passwordTxt, Boolean isWrongCredential) throws InterruptedException {
	MsgFromSignIn= signInPage.registerUser(emailTxt, passwordTxt,isWrongCredential);
	isWrong= isWrongCredential;
}
@Then("If correct credential page should signed in with correct registered name else error message should displayed")
public void if_correct_credential_page_should_signed_in_with_correct_registered_name_else_error_message_should_displayed() throws Throwable {
	if(isWrong) {
		Assert.assertEquals(MsgFromSignIn.split("Authentication")[1]," failed..");
	}
	else
	{
	    Assert.assertEquals(getRegisName(), RegisteredUserName);
	}
}

//....................................................Purchase............................................
@Given("Select dresses and in that summer dresses")
public void select_dresses_and_in_that_summer_dresses() {
	purchasePage = getPurchasePageRef();
}
@When("^Select (.+) and add to cart and call checkout$")
public void select_and_add_to_cart_and_call_checkout(String dresstoselect) throws Throwable {
	System.out.println("1 "+dressToSelect);
	totaldressInCart = purchasePage.callPurchaseItem(dressToSelect);
	PlaceOrder();
	checkOutPage = getCheckOutPageRef();
	totaldressInCheckOut = checkOutPage.callCheckOutPage(dressToSelect);
	Assert.assertEquals(totaldressInCheckOut, totaldressInCart);
}
@Then("^Sign in to checkout page and confirm the order with (.+) (.+)$")
public void sign_in_to_checkout_page_and_confirm_the_order_with(String emailTxt, String passwordTxt) throws Throwable {
	signInPage = getSignInRef();
	PurchaseErrMsg = signInPage.registerUser(emailTxt, passwordTxt,false);
}

@Then("^Sign in with wrong credentials (.+) (.+)$")
public void sign_in_with_wrong_credentials(String emailTxt, String passwordTxt) throws InterruptedException {
	signInPage = getSignInRef();
	PurchaseErrMsg = signInPage.registerUser(emailTxt, passwordTxt,true);
}

@And("^Amount is paid and \"([^\"]*)\" message is displayed$")
public void amount_is_paid_and_something_message_is_displayed(String strArg1) throws InterruptedException {
    Assert.assertEquals(getRegisName(), RegisteredUserName);
    checkOutPage.callCompleteCheckOut();
	String confirmMsg = checkOutPage.callPaymentPage();
	Assert.assertEquals(confirmMsg, strArg1);
}

@And("^error message should displayed$")
public void error_message_should_displayed() throws Throwable {
	Assert.assertEquals(PurchaseErrMsg.split("Authentication")[1]," failed.");
}

@After
public void closeBrowser()
{
	driver.close();
}

@AfterStep
public void TakeScreenshot(Scenario scenario) throws IOException
{
	if(scenario.isFailed())
	{
	  File sourcePath = 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  byte[] screenShotFiles = FileUtils.readFileToByteArray(sourcePath);
	  scenario.attach(screenShotFiles, "image/png", "failedImage");
	}
}
}
