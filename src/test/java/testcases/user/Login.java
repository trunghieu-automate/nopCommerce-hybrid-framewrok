package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.HomePageObject;
import actions.pageObjects.user.LoginPageObject;
import actions.pageObjects.user.PageGeneratorManager;
import actions.pageObjects.user.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Login extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    DataHelper.User user;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver("chrome", Constants.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        loginPage = (LoginPageObject) homePage.clickToTopNavBar("login");
        user = DataHelper.getRandomUserInfo(Constants.mainResourcePath + "userData.json");
    }

    @Test
    public void TC_01_Login_With_Empty_Data(Method method){
        startTest(method.getName(), "Login with empty data, verify validation message");

        Log.info(method.getName() +" - " + "Step 01: Click to Login button");
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() +" - " + "Step 02: Verify error message at email field is 'Please enter your email'");
        verifyEquals(loginPage.getErrorMessageByFieldID("Email"), "Please enter your email");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email(Method method){
        startTest(method.getName(), "Login with invalid email, verify validation message");

        Log.info(method.getName() + " - " + "Step 01: Enter to email field with text: " + "abc@ 12");
        loginPage.sendKeysToElementById("Email", "abc@ 12");

        Log.info(method.getName() + " - " + "Step 02: Click to login button ");
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() + " - " + "Step 03: verify error message at email field is 'Wrong email'");
        verifyEquals(loginPage.getErrorMessageByFieldID("Email"), "Wrong email");

    }

    @Test
    public void TC_03_Login_With_Unregistered_Email(Method method){
        startTest(method.getName(), "Login with unregistered email, verify validation message");

        Log.info(method.getName() + " - " + "Step 01: Enter to email field with text: " + "abc@ab.cpc");
        loginPage.sendKeysToElementById("Email", "abc@ab.cpc");

        Log.info(method.getName() + " - " + "Step 02: Click to login button ");
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() + " - " + "Step 03: verify error message appear with text 'Login was unsuccessful. " +
                "Please correct the errors and try again.\nNo customer account found'");
        verifyEquals(loginPage.getSummaryLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }

    @Test
    public void TC_04_Login_With_Registered_Email_Without_Password(Method method){
        startTest(method.getName(), "Login with registered email but leave password empty, verify password field error message");

        Log.info(method.getName() + " - " + "Pre-condition Step 01: Click to register top nav bar");
        registerPage = (RegisterPageObject) loginPage.clickToTopNavBar("register");

        Log.info(method.getName() + " - " + "Pre-condition Step 02: Enter to first name text box with: " + user.getFirstName());
        registerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Pre-condition Step 03: Enter to last name textbox with:" + user.getLastName());
        registerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Pre-condition Step 04:  Enter to email textbox with:" + user.getEmail());
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Pre-condition Step 05: Enter to password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Pre-condition Step 06: Enter to confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info(method.getName() + " - " + "Pre-condition Step 07: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Pre-condition Step 08: Log out by click logout at top nav bar");
        homePage = (HomePageObject) registerPage.clickToTopNavBar("logout");

        Log.info(method.getName() + " - " + "Pre-condition Step 09: Click to login button at top nav bar");
        loginPage = (LoginPageObject) homePage.clickToTopNavBar("login");

        Log.info(method.getName() + " - " + "Step 01: Enter to email field with valid registered email: " + user.getEmail());
        loginPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 02: Click to login button");
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() + " - " + "Step 03: Verify login unsuccessfully and error message displayed: " +
                "'Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect'");
        verifyEquals(loginPage.getSummaryLoginErrorMessage(),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect");
    }

    @Test
    public void TC_05_Login_With_Registered_Email_With_Wrong_Password(Method method){
        startTest(method.getName(), "Login with registered email but wrong password, verify login unsuccessfully with error message");

        Log.info(method.getName() + " - " + "Step 01: Enter to email field with valid registered email: " + user.getEmail());
        loginPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 02: Enter to password field with valid registered email: " + user.getPassword() + "1234");
        loginPage.sendKeysToElementById("Password", user.getPassword() + "1234");

        Log.info(method.getName() + " - " + "Step 03: Click to login button");
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() + " - " + "Step 04: Verify login unsuccessfully and error message displayed: " +
                "'Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect'");
        verifyEquals(loginPage.getSummaryLoginErrorMessage(),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                        "The credentials provided are incorrect");
    }

    @Test
    public void TC_06_Login_Successfully(Method method){
        startTest(method.getName(), "Login with registered email and password, verify home page is displayed");

        Log.info(method.getName() + " - " + "Step 01: Enter to email field with valid registered email: " + user.getEmail());
        loginPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 02: Enter to password field with valid registered email: " + user.getPassword());
        loginPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Step 03: Click to login button");
        loginPage.clickToButtonByTextName("Log in");
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info(method.getName() + " - " + "Step 04: verify home page's slider is displayed");
        verifyTrue(homePage.isHomePageSliderIsDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }

}
