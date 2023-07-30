package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUserPage {
	
	WebDriver driver;
	WebElement pageTitle;
	WebElement logInH2;
	WebElement emailLabel;
	WebElement passwordLabel;
	WebElement createAcctTop;
	WebElement email;
	WebElement password;
	WebElement continueBtn;
	WebElement emailText;
	WebElement forgotPassLink;
	WebElement createAcctBtm;
	WebElement orDivider;
	WebElement facebookLink;
	WebElement googleLink;
	WebElement appleLink;
	WebElement noAcctText;
	WebElement createAcctBtn;
	WebElement createAcctText;
	//Create Page Elements
	WebElement signUpPage;
	WebElement resetPWPage;
	WebElement fbPage; //Facebook page loads after clicking facebook link
	WebElement applePage; //Apple Login page loads after clicking Apple Link
	WebElement googlePage; //Google login loads after clicking login via Google
	
	String noAcctMsg="";
	
	
	String emailHelpText = "If you are a Hudl user, log in with your Hudl account here.";
	String invalidUserText = "If you are a Hudl user, log in with your Hudl account here.";
	
	public LoginUserPage(WebDriver driver){	
        this.driver = driver;
    }
	//Validate Log In 'H2' Label
	public boolean validateLogInH2() {
		logInH2 = driver.findElement(By.xpath("//h2[normalize-space()='Log In']"));
		if(logInH2.isDisplayed())
			return true;
		else
		return false;
	}
	
	public boolean isContinueBtnEnabled() {
		continueBtn = driver.findElement(By.xpath("//button[@id='logIn']"));
		return continueBtn.isEnabled();
	}
			
	//Validate Email Label
	public boolean validateEmailLabel() {
		emailLabel = driver.findElement(By.xpath("//label[@id='email-label']"));
		if(emailLabel.isDisplayed())
			return true;
		else
			return false;
	}
	
	//Validate Password Label
		public boolean validatePasswordLabel() {
			passwordLabel = driver.findElement(By.xpath("//label[@id='password-label']"));
			if(passwordLabel.isDisplayed())
				return true;
			else
				return false;
		}
	
	public void inputEmail(String username) {
		email = driver.findElement(By.xpath("//input[@id='email']")); 
		if(email.isDisplayed())
			email.sendKeys(username);
	}
	
	public void inputPassword(String creds) {
		 password = driver.findElement(By.id("password")); 
		 waitForVisibility(password);
		 password.sendKeys(creds);
		}
	public void clickContinueBtn() {
		continueBtn = driver.findElement(By.xpath("//button[@id='logIn']"));
		waitForVisibility(continueBtn);
		clickElement(continueBtn);
		}
	
	public void clickEnterKey() {
		driver.findElement(By.xpath("//button[@id='logIn']")).sendKeys(Keys.ENTER);
		}
	
	public void clickReturnKey() {
		driver.findElement(By.xpath("//button[@id='logIn']")).sendKeys(Keys.RETURN);
		}
	
	public void validateEmailText(String helpText) {
		driver.findElement(By.id("username-helptext"));
	}
	
	public void validateInvalidUser() {
		driver.findElement(By.xpath("//div[@class='error-container uni-notice-transition uni-notice uni-env--light uni-notice--tight show']//p[@class='error-message uni-text uni-text--small uni-text--set-solid']"));
	}
	
	public boolean clickForgotPasswordLink() {
		forgotPassLink = driver.findElement(By.id("forgot-password"));
		if(forgotPassLink.isDisplayed()) {		
			forgotPassLink.click();
			System.out.println("Forgot Pw: " + driver.getCurrentUrl().toString());
			return true;
		}
		else {			
			System.out.println("Could not find Forgot Password: " + forgotPassLink.isDisplayed());
			return false;
		}
	}
	
	public boolean validateResetPage() {
		WebElement resetPage = driver.findElement(By.xpath("//div[@id='reset-box']"));
		if(resetPage.isDisplayed())
			return true;
		else 
			return false;
		
	}
	
	public boolean validateSignupPage() {
		WebElement signUpPage = driver.findElement(By.xpath("//div[@id='signup-box']"));
		if(signUpPage.isDisplayed())
			return true;
		else 
			return false;
		
	}
	
	public boolean validateOrDivider() {
		orDivider = driver.findElement(By.xpath("//div[@class='or-divider']"));
		if(orDivider.isDisplayed()) {
			return true;		
		}
		else
			return false;
		
	}
	
	public boolean clickFacebookLink() {
		facebookLink = driver.findElement(By.xpath("//p[normalize-space()='Continue with Facebook']"));
		if(facebookLink.isDisplayed()) {
			facebookLink.click();
			return true;		
		}
		else
		return false;
	}
	
	public boolean validateFacebookPage() {
		fbPage = driver.findElement(By.xpath("//a[@title='Go to Facebook home']"));
		if(fbPage.isDisplayed()) {
			return true;
	}
		else 
			return false;
		
	}
	
	public boolean clickAppleLink() {
		appleLink = driver.findElement(By.xpath("//button[@id='btn-apple']"));
		if(appleLink.isDisplayed()) {
			appleLink.click();
			return true;		
		}
		else
			return false;
	}
	
	public boolean validateApplePage() {
		applePage = driver.findElement(By.xpath("//input[@id='account_name_text_field']"));
		if(applePage.isDisplayed()) {	
			System.out.println("Could not find Apple Element" + applePage.isDisplayed());
			return true;
		}
		else 
			return false;
		
	}
	
	public boolean clickGoogleLink() {
		googleLink = driver.findElement(By.xpath("//button[@id='btn-google']"));
		if(googleLink.isDisplayed()) {
			googleLink.click();
			return true;		
		}
		else
			return false;
	}
	
	public boolean validateGooglePage() {
		googlePage = driver.findElement(By.xpath("//a[normalize-space()='Google']"));
		if(googlePage.isDisplayed()) {
			return true;		
		}
		else
			return false;
		
	}
	
	public boolean validateNoAcctElement() {
		noAcctText = driver.findElement(By.xpath("//div[@id='login-box']//p[@class='uni-text uni-text--default no-account-text']"));
		if(noAcctText.isDisplayed()) {
			return true;		
		}
		else
			return false;
	}
	
	public String getNoAcctText() {
		
		noAcctText =  driver.findElement(By.xpath("//div[@id='login-box']//p[@class='uni-text uni-text--default no-account-text']"));
		if(noAcctText.isDisplayed()) {
			noAcctMsg = noAcctText.getText().toString();
			return noAcctMsg;
		}
		else
			return "Could not retrieve Text";
		
		
	}
	
	public boolean validateCreateAccountBtmPage() {
		 createAcctTop = driver.findElement(By.xpath("//button[@id='btn-show-signup']"));
		if(createAcctTop.isDisplayed()) {
			return true;		
		}
		else
			return false;
	}
	
	public boolean clickCreateAcctBtm() {
		
		createAcctBtm =  driver.findElement(By.xpath("//button[@id='btn-show-signup']"));	
			if(createAcctBtm.isDisplayed()) {				
				createAcctBtm.click();
				return true;
			}
			else {				
				System.out.println("Could not find Bottom Create Account: " + createAcctBtm.isDisplayed());
				return false;
			}
		}
	
	
	public boolean clickCreateAcctTop() {
		createAcctTop = driver.findElement(By.xpath("//button[@id='btn-show-signup']"));
		if(createAcctTop.isDisplayed()) {
			createAcctTop.click();
			return true;		
		}
		else {
			System.out.println("Could not find Create Account on Top of Page: " + createAcctTop.isDisplayed());
			return false;
		}
			
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
