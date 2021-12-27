package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.HomePageObject;
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

public class Register extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    DataHelper.User user;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver("chrome", Constants.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        user = DataHelper.getRandomUserInfo(Constants.mainResourcePath + "userData.json");
        registerPage = (RegisterPageObject) homePage.clickToTopNavBar("register"); //a[@class='ico-%s']
    }

    @Test
    public void TC_01_Empty_Data(Method method) {
        startTest(method.getName(), "Register with empty input, verify error message every field is displayed properly");
        Log.info(method.getName() + " - " + "Step 01: Click to Register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 02: Verify First Name field error message");
        verifyEquals(registerPage.getErrorMessageByFieldID("FirstName"), "First name is required.");

        Log.info(method.getName() + " - " + "Step 03: Verify Last Name field error message");
        verifyEquals(registerPage.getErrorMessageByFieldID("LastName"), "Last name is required.");

        Log.info(method.getName() + " - " + "Step 04: Verify email field error message");
        verifyEquals(registerPage.getErrorMessageByFieldID("Email"), "Email is required.");

        Log.info(method.getName() + " - " + "Step 05: Verify password field error message");
        verifyEquals(registerPage.getErrorMessageByFieldID("Password"), "Password is required.");

        Log.info(method.getName() + " - " + "Step 06: Verify confirm password field error message");
        verifyEquals(registerPage.getErrorMessageByFieldID("ConfirmPassword"), "Password is required.");
    }

    @Test
    public void TC_02_Invalid_Email(Method method) {
        startTest(method.getName(), "Register with invalid email then verify Email field error message is displayed");

        Log.info(method.getName() + " - " + "Step 01: Enter invalid email" + "abc @ 123");
        registerPage.sendKeysToElementById("Email", "expectedText");

        Log.info(method.getName() + " - " + "Step 02: Click to Register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 03: Verify email validation message");
        verifyEquals(registerPage.getErrorMessageByFieldID("Email"), "Wrong email");
    }

    @Test
    public void TC_03_Valid_Info(Method method) {
        startTest(method.getName(), "Register successfully with valid information");

        Log.info(method.getName() + " - " + "Step 01: Enter to first name text box with: " + user.getFirstName());
        registerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 02: Enter to last name textbox with:" + user.getLastName());
        registerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 03:  Enter to email textbox with:" + user.getEmail());
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 04: Enter to password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Step 05: Enter to confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info(method.getName() + " - " + "Step 06: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 07: Verify register success message");
        verifyEquals(registerPage.getRegisterSuccessMsg(), "Your registration completed");
    }

    @Test
    public void TC_04_Email_Exists(Method method) {
        startTest(method.getName(), "Register with already registered email, then verify error message");

        Log.info(method.getName() + " - " + "Pre-condition Step 01: Log out by click logout at top nav bar");
        homePage = (HomePageObject) registerPage.clickToTopNavBar("logout");

        Log.info(method.getName() + " - " + "Pre-condition Step 02: Click register button on top nav bar");
        registerPage = (RegisterPageObject) homePage.clickToTopNavBar("register");

        Log.info(method.getName() + " - " + "Step 01: Enter to first name text box with: " + user.getFirstName());
        registerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 02: Enter to last name textbox with:" + user.getLastName());
        registerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 03:  Enter to Email textbox with:" + user.getEmail());
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 04: Enter to Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Step 05: Enter to Confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info(method.getName() + " - " + "Step 06: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 07: Verify error message email text box is \"The specified email already exists\"");
        verifyEquals(registerPage.getRegisteredEmailMessage(), "The specified email already exists");
    }

    @Test
    public void TC_05_Password_Less_Than_6_Chars(Method method) {
        startTest(method.getName(), "Register with password less than 6 characters, verify error message");

        Log.info(method.getName() + " - " + "Step 01: Enter to Password textbox with:" + "12345");
        registerPage.sendKeysToElementById("Password", "12345");

        Log.info(method.getName() + " - " + "Step 02: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 03: Verify password validation error message displayed with text " +
                "\"Password must meet the following rules:" + "\n" + "must have at least 6 characters\"");
        verifyEquals(registerPage.getErrorMessageByFieldID("Password"), "Password must meet the following rules:" +
                "\n" +
                "must have at least 6 characters");
    }

    @Test
    public void TC_06_ConfirmPassword_Not_Match(Method method) {
        startTest(method.getName(), "Register with confirm password doesn't match with password, verify error message");

        Log.info(method.getName() + " - " + "Step 01: Enter to Password textbox with:" + "123456");
        registerPage.sendKeysToElementById("Password", "123456");

        Log.info(method.getName() + " - " + "Step 02: Enter to confirm password textbox with:" + "12345");
        registerPage.sendKeysToElementById("ConfirmPassword", "12345");

        Log.info(method.getName() + " - " + "Step 03: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() + " - " + "Step 04: Verify confirm password validation error message displayed with" +
                " text 'The password and confirmation password do not match.'");
        verifyEquals(registerPage.getErrorMessageByFieldID("ConfirmPassword"),
                "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }

}
