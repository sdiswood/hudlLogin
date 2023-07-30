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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HudlLoginLink;
import pages.LoginUserPage;
import pages.HudlHome;

public class InvalidUserTest {
WebDriver driver;

HudlLoginLink loginBtn;
LoginUserPage hudlBtn;
LoginUserPage userPage;
HudlHome homepage;
WebElement homeLink;
String user="";
String creds="";
Properties properties = new Properties();
String website="";


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
	//driver.get(website);
	loginBtn = new HudlLoginLink(driver);	
	hudlBtn = new LoginUserPage(driver);
	userPage = new LoginUserPage(driver);
	homepage = new HudlHome(driver);
	
	
}
@Test (description="Logs in a user with an invalid email/invalid email account \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void inputUserInValid() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("invalidUser").toString();
	creds = properties.getProperty("invalidCredentials").toString();
	
	userPage.inputEmail(user);
	userPage.inputPassword(creds);
	userPage.clickContinueBtn();
	//Thread.sleep(5000);
	boolean failed = homepage.validateError();
	String strExpected = properties.getProperty("invalidLoginMsg").toString();
	String strReturned = homepage.getErrorMsg();
	//Validates the Error Message loads via the Log In page
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(strReturned, strExpected);
	
}

@Test (description="Logs in a user with a valid email/invalid password \n"
		+"Validates the Home - Hudl login page throws an error and user"
		+ "is not logged in. \n"
		+ "NOTE: Test will navigate back to the https://www.hudl.com page.")
public void inputUserInValidPW() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("validEmail").toString();
	creds = properties.getProperty("invalidCredentials").toString();
	
	userPage.inputEmail(user);
	userPage.inputPassword(creds);

	userPage.clickContinueBtn();
	Thread.sleep(3000);
	String fail = homepage.getErrorMsg();
	//Validates the Error message and user is not logged ins
	AssertJUnit.assertEquals(fail, properties.getProperty("invalidLoginMsg").toString());
	
}


@Test (description="Logs in a user with an invalid email format \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void invalidEmailFormat() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("invalidEmailFormat").toString();
	creds = properties.getProperty("validPW").toString();
	
	userPage.inputEmail(user);
	userPage.inputPassword(creds);

	userPage.clickContinueBtn();
	Thread.sleep(5000);
	boolean failed = homepage.validateError();
	String strExpected = properties.getProperty("invalidLoginMsg").toString();
	String strReturned = homepage.getErrorMsg();
	//Validates the Error Message loads via the Log In page
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(strReturned, strExpected);
	
}

@Test (description="Logs in a user with an invalid email/valid password \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void invalidPasswordValidEmail() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("invalidUser").toString();
	creds = properties.getProperty("validPW").toString();
	
	userPage.inputEmail(user);
	userPage.inputPassword(creds);

	userPage.clickContinueBtn();
	Thread.sleep(3000);
	boolean failed = homepage.validateError();
	String strExpected = properties.getProperty("invalidLoginMsg").toString();
	String strReturned = homepage.getErrorMsg();
	
	//Validates the Error Message loads via the Log In page
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(strReturned, strExpected);
	
}

@Test (description="Logs in a user with an valid email and a "
		+ "valid password. The case of the pw is incorrect. \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void validPasswordUCValidEmail() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("validEmail");
	creds = properties.getProperty("validPW").toString().toUpperCase();
	
	userPage.inputEmail(user);
	userPage.inputPassword(creds);

	userPage.clickContinueBtn();
	Thread.sleep(3000);
	boolean failed = homepage.validateError();
	String strExpected = properties.getProperty("invalidLoginMsg").toString();
	String strReturned = homepage.getErrorMsg();
	
	//Validates the Error Message loads via the Log In page
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(strReturned, strExpected);
	
	}

@Test (description="Logs in a user with an valid email and a "
		+ "valid password. The case of the pw is incorrect. \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void emptyEmail() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	//user = properties.getProperty("validEmail").toString();
	creds = properties.getProperty("validPW").toString().toUpperCase();
	
	userPage.inputEmail("");
	userPage.inputPassword(creds);
	userPage.clickContinueBtn();
	Thread.sleep(3000);
	boolean failed = homepage.validateError();
	String emailExpected = properties.getProperty("requiredMsg").toString();
	emailExpected = emailExpected.replace("\n", "");
	String requiredReturned = homepage.getReqErrorMsg().toString();
	requiredReturned = requiredReturned.replace("\n", "");

	String errExpected = properties.getProperty("requiredMsg").toString();
	String errReturned = homepage.getErrorMsg();
	
	//Validate the error message is shown for Required Fields
	//Validates the Error Message for all required fields text is correct
	//Validates 'Email' is the field returned as empty
	//Validate the email field is empty
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(errReturned, errExpected);
	AssertJUnit.assertEquals(properties.get("emptyEmail").toString(), "Email");
	
}

@Test (description="Logs in a user with an valid email and a "
		+ "missing password. The case of the email is incorrect. \n"
		+"Validates the error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void emptyPassword() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	user = properties.getProperty("validEmail").toString();
	//creds = properties.getProperty("validPW").toString().toUpperCase();
	
	userPage.inputEmail(user);
	userPage.inputPassword("");
	userPage.clickContinueBtn();
	//Thread.sleep(5000);
	boolean failed = homepage.validateError();
	String emailExpected = properties.getProperty("requiredMsg").toString();
	emailExpected = emailExpected.replace("\n", "");
	String requiredReturned = homepage.getReqErrorMsg().toString();
	requiredReturned = requiredReturned.replace("\n", "");

	String errExpected = properties.getProperty("requiredMsg").toString();
	String errReturned = homepage.getErrorMsg();
	
	//Validate the error message is shown for Required Fields
	//Validates the Error Message for all required fields text is correct
	//Validates 'Email' is the field returned as empty
	//Validate the email field is empty
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(errReturned, errExpected);
	AssertJUnit.assertEquals(properties.get("emptyEmail").toString(), "Email");
	
}

@Test (description="Logs in a user with an empty email and a "
		+ "empty password. Validates the Required fields "
		+ "error message appears and the user is not logged in. \n"
		+ "NOTE: Test does not logout. Browser closes.")
public void emptyFields() throws IOException, InterruptedException {
	driver.get(website);
	loginBtn.clickLoginBtn();
	loginBtn.clickHudlBtn();
	
	//user = properties.getProperty("validEmail").toString();
	//creds = properties.getProperty("validPW").toString().toUpperCase();
	
	userPage.inputEmail("");
	userPage.inputPassword("");
	userPage.clickContinueBtn();
	Thread.sleep(3000);
	boolean failed = homepage.validateError();
	String emailExpected = properties.getProperty("requiredMsg").toString();
	emailExpected = emailExpected.replace("\n", "");
	String requiredReturned = homepage.getReqErrorMsg().toString();
	requiredReturned = requiredReturned.replace("\n", "");

	String errExpected = properties.getProperty("requiredMsg").toString();
	String errReturned = homepage.getErrorMsg();
	
	//Validate the error message is shown for Required Fields
	//Validates the Error Message for all required fields text is correct
	//Validates 'Email' is the field returned as empty
	//Validate the email field is empty
	AssertJUnit.assertEquals(failed, true);	
	AssertJUnit.assertEquals(errReturned, errExpected);
	AssertJUnit.assertEquals(properties.get("emptyEmail").toString(), "Email");
	
}
	@AfterClass
	public void afterTest() {		
		driver.close();
		driver.quit();
	}

}
