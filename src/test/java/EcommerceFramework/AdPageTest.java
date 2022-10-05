package EcommerceFramework;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AdPage;
import utils.BastTest;

public class AdPageTest extends BastTest{
	public AdPage adPage;
  @Test(groups={"Ad"})
  public void callAdPage() throws InterruptedException
  {
	  adPage = getAdPageInstance();
	  String navigatedURL=adPage.callAdPoster();
	  Assert.assertEquals(navigatedURL, "https://www.prestashop.com/en");
	  
  }
}
