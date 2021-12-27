package preTest;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.HomePageObject;
import actions.pageObjects.user.PageGeneratorManager;
import actions.pageObjects.user.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class RegisterUser extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    DataHelper.User user;

    @Test(groups = "preparation")
    public void registerRandomUser(Method method){
        startTest(method.getName(), "Register a new random user for manual test in headless chrome, store data in main resource");

        driver = getDriver("H_CHROME", Constants.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        user = DataHelper.getRandomUserInfo(Constants.mainResourcePath + "userData.json");

        Log.info(method.getName() + " - " + "Step 01: Click to register top nav bar");
        registerPage = (RegisterPageObject) homePage.clickToTopNavBar("register");

        Log.info(method.getName() + " - " + "Step 02: Enter to first name text box with: " + user.getFirstName());
        registerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 03: Enter to last name textbox with:" + user.getLastName());
        registerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 04:  Enter to email textbox with:" + user.getEmail());
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 05: Enter to password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Step 06: Enter to confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info(method.getName() + " - " + "Step 07: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info(method.getName() +" - " + "Step 08: Write user to filename: " + "currentUser.json"  + " in main/resources folder");
        DataHelper.writeUserInfoToFile(Constants.mainResourcePath + "currentUser.json", user);

        verifyTrue(true);
    }
}
