package hudlLoginTestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HudlHome;
import pages.HudlLoginLink;
import pages.LoginUserPage;
import pages.profileMenuOptions;

public class AccountSettingsTests {
	
	WebDriver driver;
	WebElement pageTitle;
	HudlLoginLink loginBtn;
	LoginUserPage hudlBtn;
	LoginUserPage userPage;
	HudlHome homepage;
	WebElement homeLink;
	profileMenuOptions profile;
	profileMenuOptions profileEmail;
	String user="";
	String creds="";
	String website;
	String email="";
	Properties properties = new Properties();
	
	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		
		//Load config file with following:
		// URL to be tested
		// Username
		// Password
		properties.load(new FileInputStream("src/test/resources/config.properties"));
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		website = properties.getProperty("hudlWebsite").toString();
		System.out.println("Setup website: " + website);
		loginBtn = new HudlLoginLink(driver);	
		hudlBtn = new LoginUserPage(driver);
		userPage = new LoginUserPage(driver);
		homepage = new HudlHome(driver);
		profile = new profileMenuOptions(driver);
		profileEmail = new profileMenuOptions(driver);		
	}
		
		
	@Test(description="Logs in a user with an existing account \n"
			+ "Navigate to Profile link and click Reset Password \n"
			+ "Validate Reset Password Label \n"
			+ "Validate Reset Password Button is enabled and clickable \n"
			+ "Validate popup that email was sent to change password. \n")
			public void changePassword() throws InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		userPage.clickContinueBtn();
		//Validate the /home link is loaded after successful login
		
		Thread.sleep(3000);
		
		
		profile.clickAvatarBtn();
		profile.clickProfileLink();
		Thread.sleep(3000);
		String profileUrl = driver.getCurrentUrl().toString();
		
		int profileCheck = profileUrl.compareTo(properties.getProperty("hudlProfileUrl").toString()); 
		String resetPwLabel = profile.getResetPwLabel();
		boolean emailAlert= profile.clickResetPwLink();
		int resetMatch = resetPwLabel.compareTo("Reset Password");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		Thread.sleep(3000);
		AssertJUnit.assertEquals(profileCheck, 0);
		AssertJUnit.assertEquals(resetMatch, 0);
		AssertJUnit.assertEquals(emailAlert, true);
		
	}
	
	@Test(description="Navigates to Log In Page \n"
			+ "Navigate to Profile link and click Reset Password \n"
			+ "Get current focus of mouse. \n"
			+ "If it's a requirement, mouse should auto focus on the \n"
			+ "email field.")
	public void getLoginPageMouseFocus() throws InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		WebElement focused = driver.switchTo().activeElement();
		System.out.println("Focus: " + focused.getTagName());
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).equals(driver.switchTo().activeElement()));
		
	}
	
	
	@AfterClass
	public void afterTest() {		
		driver.close();
		driver.quit();
	}

}
