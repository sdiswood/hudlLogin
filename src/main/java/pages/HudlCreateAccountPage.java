package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HudlCreateAccountPage {
	WebDriver driver;
	WebElement pageTitle;
	WebElement firstname;
	WebElement lastname;
	WebElement email;
	WebElement password;
	WebElement confirmPassword;
	WebElement pwRequiredChars;
	WebElement chars8;
	WebElement specialChar;
	WebElement alphaNumeric;
	
	WebElement createAcctBtn;
	WebElement acctExistLabel;
	WebElement logInBtn;
	LoginUserPage userLoginPage;
	
	
	
	public HudlCreateAccountPage(WebDriver driver){	
        this.driver = driver;
    }
	
	public boolean validateLogInBtn() {
		logInBtn = driver.findElement(By.xpath("//button[@id='btn-show-login']"));
		if(logInBtn.isDisplayed() & logInBtn.isEnabled()){
			logInBtn.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateFirstname() {
		firstname = driver.findElement(By.xpath("//input[@id='first-name']"));
		if(firstname.isDisplayed() & logInBtn.isEnabled()){
			firstname.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateLastName() {
		lastname = driver.findElement(By.xpath("By.xpath(\"//input[@id='last-name']"));
		if(lastname.isDisplayed() & logInBtn.isEnabled()){
			lastname.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateEmail() {
		email = driver.findElement(By.xpath("//input[@id='email-signup']"));
		if(email.isDisplayed() & logInBtn.isEnabled()){
			email.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validatePassword() {
		password = driver.findElement(By.xpath("//input[@id='password-signup']"));
		if(password.isDisplayed() & logInBtn.isEnabled()){
			password.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateConfirmPassword() {
		confirmPassword = driver.findElement(By.xpath("//input[@id='password-confirm']"));
		if(confirmPassword.isDisplayed() & logInBtn.isEnabled()){
			confirmPassword.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validatePwReqsLabel() {
		pwRequiredChars = driver.findElement(By.xpath("//input[@id='password-confirm']"));
		if(pwRequiredChars.isDisplayed()){
			pwRequiredChars.getText().toString();
			return true;
		}
		else
			return false;
	}
	
	public boolean validatePw8Charsl() {
		chars8 = driver.findElement(By.xpath("//p[normalize-space()='8 characters']"));
		if(chars8.isDisplayed()){
			chars8.getText().toString();
			return true;
		}
		else
			return false;
	}
	
	public boolean validatePwSpecialCharsl() {
		specialChar = driver.findElement(By.xpath("//p[normalize-space()='One special character (!@#$%^&*)']"));
		if(specialChar.isDisplayed()){
			specialChar.getText().toString();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateAlphaNumCharsl() {
		alphaNumeric = driver.findElement(By.xpath("//p[contains(text(),'One lowercase, uppercase and numeric character (a,')]"));
		if(alphaNumeric.isDisplayed()){
			alphaNumeric.getText().toString();
			return true;
		}
		else
			return false;
	}
	public boolean validateCreateAcctBtn() {
		createAcctBtn = driver.findElement(By.xpath("//button[@id='btn-signup']"));
		if(createAcctBtn.isDisplayed()){
			createAcctBtn.getText().toString();
			createAcctBtn.click();
			return true;
		}
		else
			return false;
	}
	
	public boolean validateCurrentAcctLabel() {
		acctExistLabel = driver.findElement(By.xpath("//p[normalize-space()='Already have an account?']"));
		if(acctExistLabel.isDisplayed()){
			acctExistLabel.getText().toString();
			
			return true;
		}
		else
			return false;
	}
	

}
