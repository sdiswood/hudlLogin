package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class profileMenuOptions {
  
	WebDriver driver;
	WebElement pageTitle;

	WebElement avatarBtn;
	WebElement avatarInitials;
	WebElement profileLink;
	WebElement acctSettings;
	WebElement getHelp;
	WebElement logout;
	WebElement resetPwLabel;
	WebElement resetPwLink;
	WebElement resetPwEmail;
	WebElement profileEmail;
	WebElement profileFirst;
	WebElement profileLast;
	WebElement cellPhone;
	WebElement cellCarrier;
	
	String initials="";
	String profileLastname="";
	String profileFirstname="";
	String email="";
	String userInitials="";
	
	
	
	public profileMenuOptions(WebDriver driver){	
        this.driver = driver;
    }
	
	public String getName() {
		String profileName =driver.findElement(By.cssSelector(".div[class='hui-globaluseritem__display-name']")).getAttribute("span").toString();
		System.out.println("Get small name: " + profileName);
		return profileName;
	}
	
	public boolean clickAvatarBtn() {
		avatarBtn = driver.findElement(By.xpath("//div[@class='hui-globalusermenu']"));
		if(avatarBtn.isDisplayed()) {
			avatarBtn.click();
			return true;
		}
		else {	
			System.out.println("Could not find Avatar: " + avatarBtn.isDisplayed());
			return false;
		}
	}
	
	public boolean clickProfileLink() {
		profileLink = driver.findElement(By.xpath("//span[normalize-space()='Your Profile']"));
		if(profileLink.isDisplayed()) {
			//clickElement(avatarBtn);
			profileLink.click();
			getProfileEmail();
			return true;
			}
		else 
			return false;
	}
	
	
	public String getProfileEmail() {
		profileEmail = driver.findElement(By.xpath("//input[@id='email']"));
		email = profileEmail.getAttribute("value").toString();
		return email;
	}
	
	public String getProfileFirstname() {
		profileFirst = driver.findElement(By.xpath("//input[@id='first_name']"));
		profileFirstname="";
		if(profileLast.isDisplayed()) {
			profileFirstname = profileFirst.getAttribute("value").toString();
			return profileFirstname;
		}
		else {
			System.out.println("Profile page's Firstname was not found");
			return profileFirstname;
		}
	}
	
	public String getProfileLastname() {
		profileLast = driver.findElement(By.xpath("//input[@id='last_name']"));
		
		if(profileLast.isDisplayed()) {
			profileLastname = profileLast.getAttribute("value").toString();
			return profileLastname;
		}
		else {
			System.out.println("Profile page's Lastname was not found");
			return "";
		}
	}
	
	
	

	public boolean clickAvatarInitials() {
		avatarInitials = driver.findElement(By.xpath("//div[@id='avatarInitialsContainer']//h5[@class='uni-avatar__initials uni-avatar__initials--user']"));
		if(profileLink.isDisplayed()) {
			clickElement(avatarBtn);
			userInitials = getAvatarInitials();
			return true;
			}
		else 
			return false;
	}
	public String getAvatarInitials() {
		profileLast = driver.findElement(By.xpath("//input[@id='last_name']"));	
		profileLastname = profileLast.getAttribute("value").toString();
		char last = profileLastname.charAt(0);
		char first = getProfileFirstname().charAt(0);
		initials=first+""+last;
		return initials;
	}
	
	
	
	public boolean clickAcctSettings() {
		acctSettings = driver.findElement(By.xpath("//a[@class='hui-globalusermenu__item']//span[contains(text(),'Account Settings')]"));
		if(profileLink.isDisplayed()) {
			//clickElement(avatarBtn);
			profileLink.click();
			getProfileEmail();
			return true;
			}
		else 
			return false;
	}
	
	public boolean clickGetHelp() {
		getHelp = driver.findElement(By.xpath("//div[@class='hui-globaladditionalitems hui-globaladditionalitems--not-phone']//div//a[@class='hui-globalusermenu__item hui-globalnav__help solvvy-support-flow-link']//span[contains(text(),'Get Help')]"));
		if(getHelp.isDisplayed()) {
			//clickElement(avatarBtn);
			getHelp.click();
			return true;
			}
		else 
			return false;
	}

	public boolean clickLogout() {
		logout = driver.findElement(By.xpath("//div[@class='hui-globaladditionalitems hui-globaladditionalitems--not-phone']//span[contains(text(),'Log Out')]"));
		if(logout.isDisplayed()) {
			logout.click();
			return true;
		}
		else {			
			System.out.println("Could not find logout: " + logout.isDisplayed());
			return false;
		}
	}
	
	public void logoutUser() {
		clickAvatarBtn();
		clickLogout();
	}
	
		public String getResetPwLabel() {
			resetPwLabel = driver.findElement(By.xpath("//h4[normalize-space()='Reset Password']"));
			System.out.println("Reset Label visible? " + resetPwLabel.isDisplayed());
			if(resetPwLabel.isDisplayed()) {
				System.out.println("Reset Label visible? " + resetPwLabel.isDisplayed());
				return resetPwLabel.getText().toString();
			}
			else 
				return "Reset Password label not found";
		}
		
		public boolean clickResetPwLink() {
			resetPwLink = driver.findElement(By.xpath("//button[@id='resetPassword']"));
			if(resetPwLink.isDisplayed()) {
				System.out.println("Reset Link visible? " + resetPwLink.isDisplayed());

				//clickElement(avatarBtn);
				resetPwLink.click();
				boolean email = validateEmailReset();
				return true;
				}
			else 
				return false;
		}
		
		public boolean validateEmailReset() {
			System.out.println("Reset Password Clicked");
			resetPwEmail  = driver.findElement(By.xpath("//body/div[@id='wrappy']/form[@id='aspnetForm']/div[@id='a_CenterDiv']/div[@id='pageContent']/div[@id='SuccessToast']/div[2]"));
			if(resetPwEmail.isDisplayed())
				return true;
			else
				return false;
		}
	
	 public void clickElement(WebElement element) {
	    	waitForVisibility(element);
	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
	    	executor.executeScript("arguments[0].click();", element);
	    }
	
	 private void waitForVisibility(WebElement element) throws Error{
         new WebDriverWait(driver, 60)
              .until(ExpectedConditions.visibilityOf(element));
  }
}
