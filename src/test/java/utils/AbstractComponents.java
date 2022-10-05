package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class AbstractComponents {
     WebDriver driver; 
     static WebDriverWait expWait;
	 public AbstractComponents(WebDriver driver) {
		 this.driver = driver;
		 expWait = new WebDriverWait(driver,Duration.ofSeconds(100));  
		 PageFactory.initElements(driver,this);
	}
	 
	
	 
	 public void VisibilityCheck(By findBy)
	 {
		      expWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));                                               
	 } 
	  public void VisibilityWebElementCheck(WebElement elem) throws InterruptedException        
     {     
		  expWait.until(ExpectedConditions.visibilityOf(elem));
     }
	 public void ElementToBeClickable(WebElement elem)                                
     {       		                                                                    
         expWait.until(ExpectedConditions.elementToBeClickable(elem));                  
     }
}

                                                                                  