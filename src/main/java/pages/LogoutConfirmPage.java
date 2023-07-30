package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutConfirmPage {
	
	WebElement logoutLabel;
	WebElement hi;
	WebElement sureText;
	WebElement noBtn;
	WebElement yesBtn;
	WebDriver driver;
	WebElement pageTitle;
	
	public LogoutConfirmPage(WebDriver driver){	
        this.driver = driver;
    }
	
	public boolean validateLogoutLabel() {
		logoutLabel = driver.findElement(By.xpath("h1[normalize-space()='Logout']"));
		if(logoutLabel.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean validateHiLabel() {
		hi = driver.findElement(By.xpath("//p[@class='c74028152 c8d445162']"));
		if(hi.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean confirmText() {
		sureText = driver.findElement(By.xpath("//p[@class='c74028152 c8d445162']"));
		if(sureText.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean validateYesBtn() {
		yesBtn = driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
		if(yesBtn.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean validateNoBtn() {
		noBtn = driver.findElement(By.xpath("//button[normalize-space()='No']"));
		if(noBtn.isDisplayed())
			return true;
		else 
			return false;
	}
}


