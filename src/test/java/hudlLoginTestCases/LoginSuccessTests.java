package hudlLoginTestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginUserPage;
import pages.ForgotPasswordPage;
import pages.HudlHome;
import pages.profileMenuOptions;
import pages.HudlLoginLink;

public class LoginSuccessTests {
	
	WebDriver driver;
	
	HudlLoginLink loginBtn;
	LoginUserPage hudlBtn;
	LoginUserPage userPage;
	HudlHome homepage;
	ForgotPasswordPage pwPage;
	WebElement homeLink;
	profileMenuOptions profile;
	profileMenuOptions profileEmail;
	String user="";
	String creds="";
	String website;
	String email="";
	String actualInitials="";
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
	//	driver.get(website);
		loginBtn = new HudlLoginLink(driver);	
		hudlBtn = new LoginUserPage(driver);
		userPage = new LoginUserPage(driver);
		homepage = new HudlHome(driver);
		profile = new profileMenuOptions(driver);
		profileEmail = new profileMenuOptions(driver);
		pwPage = new ForgotPasswordPage(driver);
		
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
		AssertJUnit.assertEquals(driver.getCurrentUrl(), website);
		
	}
	
	@Test (description="Logs inputs valid email/valid password \n"
			+ "Clicking Enter via Keyboard instead of click Submit \n"
			+"Validates the Home - Hudl page loads. \n"
			+ "The User is then logged out of the system. \n"
			+ "Validate the user is navigated back to the Hudl website.")
	public void validateCursorEmailField() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		
		userPage.inputEmail(user);
	}
	
	@Test (description="Inputs valid email, leave Password blank \n"
			+ "Validate the Submit button is not enabled. \n"
			+ "FALSE should be returned, so test will fail until addressed. \n"
			+ "NOTE: I'm not sure if this is a requirement, but the submit \n"
			+ "button should not be clickable until all required fields are \n"
			+ "filled out.")
	public void submitBtnNotEnabledPW() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		//userPage.inputPassword(creds);
		AssertJUnit.assertEquals(userPage.isContinueBtnEnabled(), false);

	}
	
	@Test (description="Inputs empty email, empty Password  \n"
			+ "Validate the Continue button is not enabled. \n"
			+ "FALSE should be returned, so test will fail until addressed. \n"
			+ "NOTE: I'm not sure if this is a requirement, but the Continue \n"
			+ "button should not be clickable until all required fields are \n"
			+ "filled out.")
	public void emptyFieldsContBtnCheck() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = "";
		creds = "";
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		AssertJUnit.assertEquals(userPage.isContinueBtnEnabled(), false);

	}
	
	@Test (description="Inputs valid email, leave Password blank \n"
			+ "Validate the Continue button is not enabled. \n"
			+ "FALSE should be returned, so test will fail until addressed. \n"
			+ "NOTE: I'm not sure if this is a requirement, but the Continue \n"
			+ "button should not be clickable until all required fields are \n"
			+ "filled out.")
	public void contBtnNotEnabledEmail() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		//userPage.inputEmail(user);
		userPage.inputPassword(creds);
		AssertJUnit.assertEquals(userPage.isContinueBtnEnabled(), false);

	}
	
	
	
	@Test (description="Logs inputs valid email/valid password \n"
			+ "Clicking Enter via Keyboard instead of click Submit \n"
			+"Validates the Home - Hudl page loads. \n"
			+ "The User is then logged out of the system. \n"
			+ "Validate the user is navigated back to the Hudl website.")
	public void loginValidUserEnterKey() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		userPage.clickEnterKey();
		
		Thread.sleep(3000);
		
		//Validate User is logged in and can click Trending link and Trending loads
		boolean trend = homepage.clickTrendingLink();
		AssertJUnit.assertEquals(trend, true);
	
		//Validates the "Hudl - Home" page loads
		AssertJUnit.assertEquals(homepage.validateHomeMenu(), true);	
		
		//Validate the user can click Logout from the dropdown menu
		//Validate the user is routed back to the https://www.hudl.com page
		profile.logoutUser();
		AssertJUnit.assertEquals(driver.getCurrentUrl(), website);
		
	}
	
	@Test (description="Logs inputs valid email/valid password \n"
			+ "Clicking RETURN via Keyboard instead of click Submit \n"
			+"Validates the Home - Hudl page loads. \n"
			+ "The User is then logged out of the system. \n"
			+ "Validate the user is navigated back to the Hudl website.")
	public void loginValidUserReturnKey() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		userPage.clickReturnKey();
		
		Thread.sleep(3000);
		
		//Validate User is logged in and can click Trending link and Trending loads
		boolean trend = homepage.clickTrendingLink();
		AssertJUnit.assertEquals(trend, true);
	
		//Validates the "Hudl - Home" page loads
		AssertJUnit.assertEquals(homepage.validateHomeMenu(), true);	
		
		//Validate the user can click Logout from the dropdown menu
		//Validate the user is routed back to the https://www.hudl.com page
		profile.logoutUser();
		AssertJUnit.assertEquals(driver.getCurrentUrl(), website);
	}	
	
	@Test (description="Logs in a user with an existing account \n"
			+"Validates the Home - Hudl page loads. \n"
			+ "Click Logout. Note Homepage loads. \n"
			+ "Click Browser's back button, \n"
			+ "Validate user is still logged out. \n"
			+ "Currently intermittently clicking back after Logout, still has \n"
			+ "the user logged in and links are accessible and clickable. \n")
	public void loginValidUserLogout() throws IOException, InterruptedException {
		website = "https://www.hudl.com/";
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		userPage.clickContinueBtn();
		Thread.sleep(3000);
		
		//Validates the "Hudl - Home" page loads after successful Login
		boolean success = homepage.validateHomeMenu();		
		AssertJUnit.assertEquals(success, true);	
		//Logout via the dropdown menu and validate URL is https://www.hudl.com
		profile.clickAvatarBtn();
		
		boolean loggedOut = profile.clickLogout();	
		String currentUrl = driver.getCurrentUrl();
			
		//Click the Browser's back button and validate the User is still logged out
		driver.navigate().back();
		String backUrl = driver.getCurrentUrl();
		System.out.println("URLS: " + currentUrl + ", "+  website + ", " + backUrl);
		Assert.assertEquals(loggedOut, true);
		Assert.assertEquals(currentUrl, website);
		Assert.assertEquals(backUrl, website);
	}
	
	@Test (description="Logs in a user with an existing account "
			+ "Email will be UPPPERCASE \n"
			+ "Password will be correct \n"
			+ "Validates the Home - Hudl page loads regardless of the email's case. \n"
			+ "The User is then logged out of the system.")
	public void loginUserEmailAllCaps() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString().toUpperCase();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
	
		userPage.clickContinueBtn();
		Thread.sleep(3000);
		boolean success = homepage.validateHomeMenu();
			
		//Validates the "Hudl - Home" page loads
		AssertJUnit.assertEquals(success, true);	
		boolean avatar = profile.clickAvatarBtn();
		boolean logged = profile.clickLogout();	
		System.out.println(avatar + " " + logged);
		
		//Validates the Avatar is loaded and the user is successfully logged in
		AssertJUnit.assertEquals(avatar, true);	
	}
	
	

	@Test (description="Logs in a user with an existing account "
			+ "Email will be Valid \n"
			+ "Password will be correct \n"
			+ "Validates the Home - Hudl page loads and"
			+ "Validates the email used to login matches the profile's email. \n"
			+ "The User is then logged out of the system and the browser closes.")
	public void loginValidateEmail() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
	
		userPage.clickContinueBtn();
		//Thread.sleep(5000);
		boolean success = homepage.validateHomeMenu();
		//Validates the "Hudl - Home" page loads after Submit is clicked
		AssertJUnit.assertEquals(success, true);
				
		profile.clickAvatarBtn();
		profile.clickProfileLink();
		email = profile.getProfileEmail();
		System.out.println("Emails: " + user + " " + email);
		int emailsMatch = user.compareToIgnoreCase(email);
		
		//validates the homepage loads when successfully logged in
		AssertJUnit.assertEquals(success, true);	
		//Validate a '0' is returned to confirm email logged in is correct
		AssertJUnit.assertEquals(emailsMatch, 0);
		//Validates the Avatar icon is clicked and the user can logout
		boolean avatar = profile.clickAvatarBtn();
		boolean logged = profile.clickLogout();	
		AssertJUnit.assertEquals(avatar, true);
		AssertJUnit.assertEquals(logged, true);
	}
	
	@Test (description="Logs in a user with an existing account "
			+ "Email will be Valid but in Uppercase \n"
			+ "Password will be correct \n"
			+ "Validates the Home - Hudl page loads and"
			+ "Validates the email used to login matches the profile's email. \n"
			+ "The User is then logged out of the system and the browser closes.")
	public void loginValidateEmailAllCaps() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString().toUpperCase();
		creds = properties.getProperty("validPW").toString();
		
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
	
		userPage.clickContinueBtn();
		//Thread.sleep(5000);
		boolean success = homepage.validateHomeMenu();
		//Validates the "Hudl - Home" page loads after Submit is clicked
		AssertJUnit.assertEquals(success, true);
				
		profile.clickAvatarBtn();
		profile.clickProfileLink();
		email = profile.getProfileEmail();
		System.out.println("Emails: " + user + " " + email);
		int emailsMatch = user.compareToIgnoreCase(email);
		
		//validates the homepage loads when successfully logged in
		AssertJUnit.assertEquals(success, true);	
		//Validate a '0' is returned to confirm email logged in is correct
		AssertJUnit.assertEquals(emailsMatch, 0);
		//Validates the Avatar icon is clicked and the user can logout
		boolean avatar = profile.clickAvatarBtn();
		boolean logged = profile.clickLogout();	
		AssertJUnit.assertEquals(avatar, true);
		AssertJUnit.assertEquals(logged, true);
	}
	
	@Test (description="Logs in a user with an existing account "
			+ "Email will be Valid \n"
			+ "Password will be correct \n"
			+ "Validates the Home - Hudl page loads and \n"
			+ "Validates the avatar's initials are the first letter of the firstname \n"
			+ "and the first letter of the lastname from the profile page's fields \n"
			+ "The User is then logged out of the system and the browser closes.")
	public void validateAvatarInitials() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		user = properties.getProperty("validEmail").toString().toUpperCase();
		creds = properties.getProperty("validPW").toString();
		String initials = properties.getProperty("avatarInitials").toUpperCase().toString().trim();
		userPage.inputEmail(user);
		userPage.inputPassword(creds);
		userPage.clickContinueBtn();
		Thread.sleep(3000);
		String homeUrl = driver.getCurrentUrl();
		//Validate the /home link is loaded after successful login
		profile.clickAvatarBtn();
		profile.clickProfileLink();
		actualInitials = profile.getAvatarInitials().toUpperCase().trim();
		int avatarInitialsMatch = initials.compareTo(actualInitials);
		System.out.println("Initials vs actuals: " + initials+", " +actualInitials + ", " + avatarInitialsMatch);
		//Assert that the Avatar's initials match the following:
		//First char of firstname via Profile Page
		//First char of lastname via Profile Page
		AssertJUnit.assertEquals(avatarInitialsMatch, 0);
		AssertJUnit.assertEquals(homeUrl, properties.getProperty("hudlWebsiteHome").toString());
		
				
		
		profile.logoutUser();
		String url = driver.getCurrentUrl();
		System.out.println("Current Url: " + url);
		AssertJUnit.assertEquals(url, website);
		
		
	}
	
	@Test (description="Validates the Login User page loads Dont have an account? text. \n")
	public void validateNoAcctText() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		String visible = userPage.getNoAcctText();
		String pageText = properties.getProperty("noAcctText").toString();
		AssertJUnit.assertEquals(visible, pageText);
	}
	
	@Test (description="Validates the Create Account exist on the bottom of the Log In page \n"
			+ "Validates when clicked, user is navigated to the Signup Page. \n"
			+ "User is then logged out.")
	public void validateCreateAcctBtm() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		boolean userPageLoads = userPage.clickCreateAcctBtm();
		boolean signup = userPage.validateSignupPage();
		AssertJUnit.assertEquals(userPageLoads, true);
		//Validate the Sign Up Page loads
		AssertJUnit.assertEquals(signup, true);
	}
	
	@Test (description="Validates the Create Account exist on the Top of the Log In page. \n"
			+ "Validates when clicked, user is navigated to the Signup Page. \n"
			+ "User is then logged out.")
	public void validateCreateAcctTop() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		boolean createAccount = userPage.clickCreateAcctTop();
		boolean signupPage = userPage.validateSignupPage();
		AssertJUnit.assertEquals(createAccount, true);
		//Validate the Sign Up Page loads
		AssertJUnit.assertEquals(signupPage, true);
	}
	
	@Test (description="Validates the Forgot Password Link exist. \n"
			+ "Validates that Reset Page is loaded when clicked. "
			+ "Browser is then closed")
	public void clickForgotPW() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		boolean pwLoads = userPage.clickForgotPasswordLink();
		boolean resetLoads = userPage.validateResetPage();
		//Validate the Forgot Password Link isDisplayed() and then clicked
		AssertJUnit.assertEquals(pwLoads, true);
		//Validate the Sign Up Page loads
		AssertJUnit.assertEquals(resetLoads, true);
	}
	
	@Test (description="Validates the Facebook Login Link exist. \n"
			+ "Validates that FB Login Request Page is loaded when clicked. "
			+ "Browser is then closed")
	public void clickGoogle() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		boolean fbElementLoads = userPage.clickFacebookLink();
		boolean fbLoads = userPage.validateFacebookPage();
		//Validate the Forgot Password Link isDisplayed() and then clicked
		AssertJUnit.assertEquals(fbElementLoads, true);
		//Validate the Facebook Login Page loads
		AssertJUnit.assertEquals(fbLoads, true);
	}
	
	
	@Test (description="Validates the Google Login Link exist. \n"
			+ "Validates that Google Login Request Page is loaded when clicked. "
			+ "Browser is then closed")
	public void clickFacebook() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		boolean googleElementLoads = userPage.clickGoogleLink();
		boolean googleLoads = userPage.validateGooglePage();
		//Validate the Google Login Btn isDisplayed() and then clicked
		AssertJUnit.assertEquals(googleElementLoads, true);
		//Validate the Google Login Page loads
		AssertJUnit.assertEquals(googleLoads, true);
	}
	
	
	@Test (description="Validates the Apple Login Link exist. \n"
			+ "Validates that Apple Login Request Page is loaded when clicked. "
			+ "Browser is then closed")
	public void clickApple() throws IOException, InterruptedException {
		driver.get(website);
		loginBtn.clickLoginBtn();
		loginBtn.clickHudlBtn();
		
		Thread.sleep(3000);
		boolean appleElementLoads = userPage.clickAppleLink();
		boolean appleLoads = userPage.validateApplePage();
		//Validate the Apple Login Page isDisplayed() and then clicked
		AssertJUnit.assertEquals(appleElementLoads, true);
		//Validate the Apple Login Page loads
		AssertJUnit.assertEquals(appleLoads, true);
	}
	
	
	
	@AfterClass
	public void afterTest() {		
		driver.close();
		driver.quit();
	}
	
}
