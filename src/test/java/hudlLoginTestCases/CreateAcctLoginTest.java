package hudlLoginTestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ForgotPasswordPage;
import pages.HudlCreateAccountPage;
import pages.HudlHome;
import pages.HudlLoginLink;
import pages.LoginUserPage;
import pages.profileMenuOptions;

public class CreateAcctLoginTest {
	
	WebDriver driver;
	Properties properties = new Properties();
	String website="";
	String user="";
	String creds = "";
	HudlCreateAccountPage logInFromCreateAcct;
	LoginUserPage userPage;
	HudlHome homepage;
	profileMenuOptions profile;
	HudlLoginLink loginBtn;
	LoginUserPage hudlBtn;
	ForgotPasswordPage forgotPwPage;
	
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
	//	driver.get(website);
		logInFromCreateAcct = new HudlCreateAccountPage(driver);	
		
		loginBtn = new HudlLoginLink(driver);	
		hudlBtn = new LoginUserPage(driver);
		userPage = new LoginUserPage(driver);
		homepage = new HudlHome(driver);
		profile = new profileMenuOptions(driver);
		//profileEmail = new profileMenuOptions(driver);
		//loginUserPage = new LoginUserPage(driver);
		//homepage = new HudlHome(driver);
		profile = new profileMenuOptions(driver);
		forgotPwPage = new ForgotPasswordPage(driver);
		//profileEmail = new profileMenuOptions(driver);
	}
	
	
	@Test(description="Click Login --> Hudl (loads User Login Page \n"
			+ "Click Forgot Password via LoginUserPage \n"
		+ "Validate navigation to Account Settings Page \n"
		+ "Click Back to Login Page. \n"
		+ "Validate the user is then logged in successfully")
		public void loginFromCreateAcctPage() throws InterruptedException {
		
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		boolean success = true;
		
		Thread.sleep(2000);
		boolean forgotPwUserPage = userPage.clickForgotPasswordLink();
		boolean backPage = forgotPwPage.validateForgotPWHeader();
		System.out.println("Get Forgot Password H2: " + forgotPwPage);
		if(forgotPwUserPage) {
			String x = forgotPwPage.getBackToLogInText();
			System.out.println("Log In Header: " +x + ", "+ forgotPwPage.clickBackToLogin());
			userPage.clickCreateAcctBtm();
			//Validate user is return to Log In Page
			logInFromCreateAcct.validateLogInBtn();
			
			//Login with Correct User
			userPage.inputEmail(user);
			userPage.inputPassword(creds);
			userPage.clickContinueBtn();
			
			success = homepage.validateHomeMenu();	
		}
		Assert.assertEquals(success, true);
	}
		
	
		
	 
		@Test (description="Logs in a user with an existing account \n"
				+"Validates the Home - Hudl page loads. \n"
				+ "The User is then logged out of the system. \n"
				+ "Validate the user is navigated back to the Hudl website.")
		public void loginValidUser() throws IOException, InterruptedException {
			driver.get(website);
			loginBtn.clickLoginBtn();
			loginBtn.clickHudlBtn();
			
			user = properties.getProperty("validEmail").toString();
			creds = properties.getProperty("validPW").toString();
			
			userPage.inputEmail(user);
			userPage.inputPassword(creds);
			userPage.clickContinueBtn();
		
			
			Thread.sleep(3000);
			boolean success = homepage.validateHomeMenu();
			AssertJUnit.assertEquals(homepage.clickFeedLink(), true);
			
			//Validate User is logged in and can click Trending link and Trending loads
			boolean trend = homepage.clickTrendingLink();
			AssertJUnit.assertEquals(trend, true);
		
			//Validates the "Hudl - Home" page loads
			AssertJUnit.assertEquals(success, true);	
			
			//Validate the user can click Logout from the dropdown menu
			//Validate the user is routed back to the https://www.hudl.com page
			profile.logoutUser();
			Assert.assertEquals(driver.getCurrentUrl(), website);
		}
	
}
