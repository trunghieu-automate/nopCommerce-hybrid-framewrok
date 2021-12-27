package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class MyAccount extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    MyAccountPageObject myAccountPage;
    LoginPageObject loginPage;
    ProductPageObject productPage;
    DataHelper.User user;
    String newMail = "trunghieu.automate."+getRandomNumber()+"@gmail.com";

    @BeforeClass
    public void beforeClass() {
        driver = getDriver("chrome", Constants.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        user = DataHelper.getRandomUserInfo(Constants.mainResourcePath + "userData.json");

        Log.info("Pre-condition Step 01: Click to register top nav bar");
        registerPage = (RegisterPageObject) homePage.clickToTopNavBar("register");

        Log.info("Pre-condition Step 02: Enter to first name text box with: " + user.getFirstName());
        registerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info("Pre-condition Step 03: Enter to last name textbox with:" + user.getLastName());
        registerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info("Pre-condition Step 04:  Enter to email textbox with:" + user.getEmail());
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info("Pre-condition Step 05: Enter to password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info("Pre-condition Step 06: Enter to confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info("Pre-condition Step 07: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info("Pre-condition Step 08: Click to My account button at footer");
        myAccountPage = (MyAccountPageObject) registerPage.clickToFooterLinkByTextName("My account");

    }


    @Test
    public void TC_01_Customer_Info(Method method){
        startTest(method.getName(), "Update customer info, then verify information are updated");

        Log.info(method.getName() + " - " + "Step 01: Select female gender radio button");
        myAccountPage.selectGenderCheckboxByTextName("female");

        Log.info(method.getName() + " - " + "Step 02: Enter to first name textbox with text: 'trunghieu'");
        myAccountPage.sendKeysToElementById("FirstName", "trunghieu");

        Log.info(method.getName() + " - " + "Step 03: Enter to last name textbox with text: 'automate'");
        myAccountPage.sendKeysToElementById("LastName", "automate");

        Log.info(method.getName() + " - " + "Step 04: Enter to email textbox with text: '"+newMail+"'");
        myAccountPage.sendKeysToElementById("Email", newMail);

        Log.info(method.getName() + " - " + "Step 05: Select date of birth dropdown list with: '01/01/1990'");
        myAccountPage.selectDobDropdownListByTextName("Day", "1");
        myAccountPage.selectDobDropdownListByTextName("Month", "January");
        myAccountPage.selectDobDropdownListByTextName("Year", "1990");

        Log.info(method.getName() + " - " + "Step 06: Enter company name textbox with text: 'Automation FC'");
        myAccountPage.sendKeysToElementById("Company", "Automation FC");

        Log.info(method.getName() + " - " + "Step 07: Click to Save button");
        myAccountPage.clickToButtonByTextName("Save");

        Log.info(method.getName() + " - " + "Step 08: Verify first name is updated as 'Automation'");
        verifyEquals(myAccountPage.getValueFromTextboxById("FirstName"), "trunghieu");

        Log.info(method.getName() + " - " + "Step 09: Verify last name is updated as 'FC'");
        verifyEquals(myAccountPage.getValueFromTextboxById("LastName"), "automate");

        Log.info(method.getName() + " - " + "Step 10: Verify email is updated as '"+newMail+"'");
        verifyEquals(myAccountPage.getValueFromTextboxById("Email"), newMail);

        Log.info(method.getName() + " - " + "Step 11: Verify Company is updated as 'Automation FC'");
        verifyEquals(myAccountPage.getValueFromTextboxById("Company"), "Automation FC");

        Log.info(method.getName() + " - " + "Step 12: Verify day of birth day is updated as '1'");
        verifyEquals(myAccountPage.getCurrentOptionDropdownListByName("Day"), "1");

        Log.info(method.getName() + " - " + "Step 13: Verify day of birth month is updated as 'January'");
        verifyEquals(myAccountPage.getCurrentOptionDropdownListByName("Month"), "January");

        Log.info(method.getName() + " - " + "Step 14: Verify day of birth year is updated as '1990'");
        verifyEquals(myAccountPage.getCurrentOptionDropdownListByName("Year"), "1990");

        Log.info(method.getName() + " - " + "Step 15: Verify gender female is selected");
        verifyTrue(myAccountPage.isCheckboxSelectedByName("female"));
    }

    @Test
    public void TC_02_Addresses(Method method){
        startTest(method.getName(), "Updated Addresses information, then verify addresses are updated");

        Log.info(method.getName() + " - " + "Step 01: Click to Addresses link at left menu bar");
        myAccountPage.clickLeftMenuBarByNameText("Addresses");

        Log.info(method.getName() + " - " + "Step 02: Click to Add new button");
        myAccountPage.clickToButtonByTextName("Add new");

        Log.info(method.getName() + " - " + "Step 03: Enter to First Name textbox with text: 'trunghieu'");
        myAccountPage.sendKeysToElementById("Address_FirstName", "trunghieu");

        Log.info(method.getName() + " - " + "Step 04: Enter to  Last Name textbox with text: 'FC'");
        myAccountPage.sendKeysToElementById("Address_LastName", "automate");

        Log.info(method.getName() + " - " + "Step 05: Enter to email textbox with text: '"+newMail+";");
        myAccountPage.sendKeysToElementById("Address_Email", newMail);

        Log.info(method.getName() + " - " + "Step 06: Enter to Company textbox with text: 'Automation FC'");
        myAccountPage.sendKeysToElementById("Address_Company", "Automation FC");

        Log.info(method.getName() + " - " + "Step 07: Select Country dropdown list option: 'Viet Nam'");
        myAccountPage.selectDropDownListById("Address_CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 08: Select state dropdown list option: 'Other'");
        myAccountPage.selectDropDownListById("Address_StateProvinceId", "Other");

        Log.info(method.getName() + " - " + "Step 09: Enter to city textbox with text: 'Da Nang'");
        myAccountPage.sendKeysToElementById("Address_City", "Da Nang");

        Log.info(method.getName() + " - " + "Step 10: Enter to address 1 textbox with text: '39 Phan Chau Trinh'");
        myAccountPage.sendKeysToElementById("Address_Address1", "39 Phan Chau Trinh");

        Log.info(method.getName() + " - " + "Step 11: Enter to address 2 textbox with text: '79 Le Loi'");
        myAccountPage.sendKeysToElementById("Address_Address2", "79 Le Loi");

        Log.info(method.getName() + " - " + "Step 12: Enter to Zip/ postal cod text box with text: '550000'");
        myAccountPage.sendKeysToElementById("Address_ZipPostalCode", "550000");

        Log.info(method.getName() + " - " + "Step 13: Enter to phone number textbox with text: '0979797979'");
        myAccountPage.sendKeysToElementById("Address_PhoneNumber", "0979797979");

        Log.info(method.getName() + " - " + "Step 14: Enter to fax number textbox with text: '0939393939'");
        myAccountPage.sendKeysToElementById("Address_FaxNumber", "0939393939");

        Log.info(method.getName() + " - " + "Step 15: click to save button");
        myAccountPage.clickToButtonByTextName("Save");

        Log.info(method.getName() + " - " + "Step 16: Verify name is updated as: 'trunghieu automate'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("name"), "trunghieu automate");

        Log.info(method.getName() + " - " + "Step 17: Verify email is updated as: '"+newMail+"'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("email"), "Email: " + newMail);

        Log.info(method.getName() + " - " + "Step 18: Verify phone number is updated as: '0979797979'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("phone"), "Phone number: 0979797979");

        Log.info(method.getName() + " - " + "Step 19: Verify fax number is updated as: '0939393939'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("fax"), "Fax number: 0939393939");

        Log.info(method.getName() + " - " + "Step 20: Verify company is updated as: 'Automation FC'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("company"), "Automation FC");

        Log.info(method.getName() + " - " + "Step 21: Verify address1 is updated as: '39 Phan Chau Trinh'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("address1"), "39 Phan Chau Trinh");

        Log.info(method.getName() + " - " + "Step 22: Verify address2 is updated as: '79 Le Loi'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("address2"), "79 Le Loi");

        Log.info(method.getName() + " - " + "Step 23: Verify city state zip code as: 'Da Nang, 550000'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("city-state-zip"), "Da Nang, 550000");

        Log.info(method.getName() + " - " + "Step 24: Verify country is updated as: 'Viet Nam'");
        verifyEquals(myAccountPage.getNewAddressInfoResultByClassname("country"), "Viet Nam");
    }

    @Test
    public void TC_03_Change_Password(Method method){
        startTest(method.getName(), "Change password, then login with new password successfully");
        myAccountPage.clickLeftMenuBarByNameText("Change password");

        Log.info(method.getName() + " - " + "Step 01: Enter to old password textbox with: " + user.getPassword()); //OldPassword
        myAccountPage.sendKeysToElementById("OldPassword", user.getPassword());

        Log.info(method.getName() + " - " + "Step 02: Enter to new password textbox with: '123456'"); //NewPassword
        myAccountPage.sendKeysToElementById("NewPassword", "123456");

        Log.info(method.getName() + " - " + "Step 03: Enter to confirm password textbox with: '123456'"); //ConfirmNewPassword
        myAccountPage.sendKeysToElementById("ConfirmNewPassword", "123456");

        Log.info(method.getName() + " - " + "Step 04: click to Change password button");
        myAccountPage.clickToButtonByTextName("Change password");

        Log.info(method.getName() + " - " + "Step 05: Verify change password successfully with a message green bar displayed"); //div[@class='bar-notification success']//p
        verifyTrue(myAccountPage.isPasswordChangedSuccessMessageDisplayed());

        Log.info(method.getName() + " - " + "Step 06: Click to close button to close success message green bar"); //div[@class='bar-notification success']//span
        myAccountPage.clickToCloseMessageBar();

        Log.info(method.getName() + " - " + "Step 07: Logout by click logout button at top nav bar");
        homePage = (HomePageObject) myAccountPage.clickToTopNavBar("logout");

        Log.info(method.getName() + " - " + "Step 08: Navigate to login page by click to login button at top nav bar");
        loginPage = (LoginPageObject) homePage.clickToTopNavBar("login");

        Log.info(method.getName() + " - " + "Step 09: Try to login by old password, enter email and password as: '"+newMail+", " +user.getPassword()+"'");
        loginPage.sendKeysToElementById("Email", newMail);
        loginPage.sendKeysToElementById("Password", user.getPassword());
        loginPage.clickToButtonByTextName("Log in");

        Log.info(method.getName() + " - " + "Step 10: Verify login unsuccessfully with error message displayed: " +
                "'Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect'");
        verifyEquals(loginPage.getSummaryLoginErrorMessage(),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                        "The credentials provided are incorrect");

        Log.info(method.getName() + " - " + "Step 11: Try to login with new password: '123456'");
        loginPage.sendKeysToElementById("Password", "123456");
        loginPage.clickToButtonByTextName("Log in");
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info(method.getName() + " - " + "Step 12: Verify login successfully then homepage's slider and logout button are displayed");
        verifyTrue(homePage.isHomePageSliderIsDisplayed() && homePage.isLogoutButtonOnTopNavBarDisplayed());
    }

    @Test
    public void TC_04_My_Product_Reviews(Method method){
        startTest(method.getName(), "Add review for a product, then verify reviews is displayed at My Account page");

        Log.info(method.getName() + " - " + "Step 01: Click to notebooks menu from computers menu at Product menu bar");
        productPage = homePage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to Apple macbook Pro 13-inch product");
        productPage.clickToProductByName("Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 03: Click to Add your review link");
        productPage.clickToLinkTagByTextName("Add your review");

        Log.info(method.getName() + " - " + "Step 04: Enter to review title textbox with text: 'trunghieu-automate review'");
        productPage.sendKeysToElementById("AddProductReview_Title", "trunghieu-automate review");

        Log.info(method.getName() + " - " + "Step 05: Enter to review text textbox with text: 'trunghieu-automate review text'");
        productPage.sendKeysToElementById("AddProductReview_ReviewText", "trunghieu-automate review text");

        Log.info(method.getName() + " - " + "Step 06: Click to rating option: 'Good'");
        productPage.selectRadioButtonById("addproductrating_5");

        Log.info(method.getName() + " - " + "Step 07: Click to Submit review button");
        productPage.clickToButtonByTextName("Submit review");

        Log.info(method.getName() + " - " + "Step 08: click to My account button at top nav bar to navigate to My Account Page");
        myAccountPage = (MyAccountPageObject) productPage.clickToTopNavBar("account");

        Log.info(method.getName() + " - " + "Step 09: Click to My product reviews menu at left nav bar.");
        myAccountPage.clickLeftMenuBarByNameText("My product reviews");

        Log.info(method.getName() + " - " + "Step 10: Verify review title is 'trunghieu-automate review'.");
        verifyEquals(myAccountPage.getProductReviewTitle(), "trunghieu-automate review");

        Log.info(method.getName() + " - " + "Step 11: Verify review text is 'trunghieu-automate review text'");
        verifyEquals(myAccountPage.getProductReviewContentText(), "trunghieu-automate review text");

        Log.info(method.getName() + " - " + "Step 12: Verify review product is 'Apple MacBook Pro 13-inch'");
        verifyEquals(myAccountPage.getProductReviewProductName(), "Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 13: Verify review rating is '5 stars'");
        verifyEquals(myAccountPage.getProductReviewRatingStars(), "5");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
