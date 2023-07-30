package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage {
	
	
	WebDriver driver;
	@FindBy(xpath=("//header/span[@id='nav-btn-logo-hudl']"))
		WebElement hudlLogoLink;
	@FindBy(xpath=("//h2[normalize-space()='Forgot Password']"))
		WebElement forgotPwHeader;
	@FindBy(xpath=("//p[@id='email-reset-help']"))
		WebElement infoText;
	@FindBy(xpath=("//div[@id='email-reset-container']//label[@for='name'][normalize-space()='Email']"))
			WebElement emailLabel;
	@FindBy(xpath=("//input[@id='email-reset']"))
		WebElement emailInput;
	@FindBy(id=("btn-reset"))
		WebElement backToLogIn;
	@FindBy(xpath=("//button[@id='nav-btn-page']"))
		WebElement logInTopBtn;
	@FindBy(xpath=("//button[@id='btn-reset']"))
		WebElement continueBtn;
	
	String forgotPwH2 = "";
	String helpText = "";
	String emailLabelText = "";
	String backToLogInText ="";
	String contBtnText = "";
	
	public ForgotPasswordPage(WebDriver driver){	
        this.driver = driver;
    }
	
	public String getLogInH2Text() {
		forgotPwH2 = forgotPwHeader.getText().toString() ;
		return forgotPwH2;
	}
	
	public String getInfoText() {
		helpText = infoText.getText().toString() ;
		return helpText;
	}
	
	public String getEmailLabelText() {
		emailLabelText = emailLabel.getText().toString() ;
		return emailLabelText;
	}
	
	public String getBackToLogInText() {
		backToLogIn = driver.findElement(By.xpath("(//button[@type='button'])[1]"));
		backToLogInText = backToLogIn.getText().toString() ;
		return backToLogInText;
	}
	
	public String getContinueBtnText() {
		contBtnText = continueBtn.getText().toString() ;
		return contBtnText;
	}
	
	public String getTopLogInBtnText() {
		contBtnText = logInTopBtn.getText().toString() ;
		return contBtnText;
	}
	
	
	public void click() {
		infoText = driver.findElement(By.xpath("//p[@id='email-reset-help']"));
		emailLabel= driver.findElement(By.xpath("//div[@id='email-reset-container']//label[@for='name'][normalize-space()='Email']"));
		emailInput= driver.findElement(By.xpath("//input[@id='email-reset']"));
		backToLogIn = driver.findElement(By.id("btn-reset"));
		continueBtn = driver.findElement(By.xpath(""));
		
	}
	
	public boolean clickBackToLogin() {
		//backToLogIn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/form[1]/div[2]/button[1]"));
		if(backToLogIn.isDisplayed()) {
			backToLogIn.click();
			return true;
		}
		else {
			System.out.println("ERROR: Unable to click Back to Log In");
			return false;
		}
	}
	
	public boolean validateForgotPWHeader() {
		forgotPwHeader = driver.findElement(By.id("btn-reset"));
		if(forgotPwHeader.isDisplayed()) {
			System.out.println("ForgotPwH2: " +forgotPwHeader.getText().toString());
			return true;
		}
		else {
			System.out.println("ERROR: Unable to click Back to Log In");
			return false;
		}
	}

}
