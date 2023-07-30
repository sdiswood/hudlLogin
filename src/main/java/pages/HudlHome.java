package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HudlHome {
	
	WebDriver driver;
	WebElement pageTitle;
	WebElement homeLink;
	WebElement exploreLink;
	WebElement invalidUser;
	WebElement feedLink;
	WebElement trendingLink;
	
	public HudlHome(WebDriver driver){	
        this.driver = driver;
    }
	
	public boolean validateHomeMenu() throws InterruptedException {
		//System.out.println("ValidateHomePage Loads");
		Thread.sleep(3000);
		homeLink = driver.findElement(By.id("home-content"));
		//waitForVisibility(homeLink);
		if(homeLink.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean validateError() throws InterruptedException {
		Thread.sleep(3000);
		homeLink = driver.findElement(By.xpath("//div[@class='error-container uni-notice-transition uni-notice uni-env--light uni-notice--tight show']"));
		waitForVisibility(homeLink);
		if(homeLink.isDisplayed()) {
			getErrorMsg();
			return true;
		}		
		else 
			return false;
	}
	
	public boolean clickFeedLink() throws InterruptedException {
		Thread.sleep(3000);
		feedLink = driver.findElement(By.xpath("//span[normalize-space()='Feed']"));
		if(feedLink.isDisplayed()) {
			feedLink.click();
			return true;
		}		
		else 
			return false;
	}
	
	public boolean clickTrendingLink() throws InterruptedException {
		trendingLink = driver.findElement(By.xpath("//span[normalize-space()='Trending']"));
		waitForVisibility(trendingLink);
		if(trendingLink.isDisplayed()) {
			trendingLink.click();
			return true;
		}		
		else 
			return false;
	}


	public String getErrorMsg() {
		WebElement errorMsg = driver.findElement(By.xpath("//div[@class='error-container uni-notice-transition uni-notice uni-env--light uni-notice--tight show']"));
		return errorMsg.getText().toString();
	}
	
	//Method to capture the page heading
		public String getHeading() {
			String pageTitle = driver.getTitle().toString();
			return pageTitle;
		}
	
	public boolean emailRequired() {
		System.out.println("Validate Error Message for required email");
		//Thread.sleep(3000);
		homeLink = driver.findElement(By.xpath("//div[@class='input-container error required-error']//span[contains(text(),'Required')]"));
		waitForVisibility(homeLink);
		if(homeLink.isDisplayed())
			return true;
		else {
			//invalidUser = driver.findElement(By.xpath("(//p[normalize-space()='We don't recognize that email and/or password.'])[1]"));
			//getErrorMsg();
			return false;
		}
	}
	
	public String getReqErrorMsg() {
		WebElement loginField = driver.findElement(By.id("email-label"));
		WebElement reqErrorMsg = driver.findElement(By.xpath("//div[@id='username-container']"));
		String strReturned = loginField.getText().toString();
		System.out.println("Required error Message: " + reqErrorMsg.getText().toString());
		strReturned = strReturned.replace("\n", "");
		return (strReturned.trim());
	}
	
	/*public boolean validateExploreMenu() {
		
		exploreLink = driver.findElement(By.className("hui-globalusermenu")); 
		waitForVisibility(exploreLink);
		if(exploreLink.isDisplayed())
			return true;
		else 
			return false;
	}*/
	
	 private void waitForVisibility(WebElement element) throws Error{
         new WebDriverWait(driver, 60)
              .until(ExpectedConditions.visibilityOf(element));
  }

}
