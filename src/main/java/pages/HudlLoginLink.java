package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HudlLoginLink {
	
	WebDriver driver;
	WebElement pageTitle;

	WebElement loginLink;
	WebElement hudlLink;
	WebElement Wyscount;
	WebElement WIMUCloud;
	WebElement loginHeader;
	WebElement email;
	WebElement password;
	WebElement homeLogo;
	
	
	public HudlLoginLink(WebDriver driver){	
        this.driver = driver;
    }
	
	public boolean validateHomeLogo() {
		homeLogo = driver.findElement(By.xpath("//a[@title='Home']//*[name()='svg']"));
				
		boolean loggedOut=homeLogo.isDisplayed();
		if(loggedOut)
			return true;
		else
			return false;
	}
	
	public void clickLoginBtn() {
	 loginLink = driver.findElement(By.xpath("//a[normalize-space()='Log in']")); 
		clickElement(loginLink);
	}
	
	public void clickHudlBtn() {
		hudlLink = driver.findElement(By.xpath("(//span[@class='subnavitem__label'][normalize-space()='Hudl'])[5]"));
		clickElement(hudlLink);
		
		}
	
	public void loginUser() {
		clickLoginBtn();
		clickHudlBtn();
		loginHeader = driver.findElement(By.xpath("//h2[normalize-space()='Log In']"));
		waitForVisibility(loginHeader);
	}
	
	public void clickWyscount() {		
			clickElement(Wyscount);
		}
	
	 private void waitForVisibility(WebElement element) throws Error{
         new WebDriverWait(driver, 60)
              .until(ExpectedConditions.visibilityOf(element));
  }

	 
	 public void clickElement(WebElement element) {
	    	waitForVisibility(element);
	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
	    	executor.executeScript("arguments[0].click();", element);
	    }

}
