package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.HomePageObject;
import actions.pageObjects.user.PageGeneratorManager;
import actions.pageObjects.user.RegisterPageObject;
import actions.pageObjects.user.SearchPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Search extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    SearchPageObject searchPage;
    DataHelper.User user;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser) {
        driver = getDriver(browser, Constants.homePageUrl);
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

        Log.info("Pre-condition Step 08: Navigate to Search page by click search menu at footer");
        searchPage = (SearchPageObject) registerPage.clickToFooterLinkByTextName("Search");
    }

    @Test
    public void TC_01_Search_With_Empty_Data(Method method) {
        startTest(method.getName(), "Try to search without any data, verify error message");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with empty data");
        searchPage.sendKeysToElementById("q", "");

        Log.info(method.getName() + " - " + "Step 02: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 03: Verify search result displayed with text: 'Search term minimum length is 3 characters'");
        verifyEquals(searchPage.getSearchResultText(), "Search term minimum length is 3 characters");

    }

    @Test
    public void TC_02_Search_With_Data_Not_Exist(Method method) {
        startTest(method.getName(), "Try to search with data doesn't exist, verify error message");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Macbook pro 2050'" );
        searchPage.sendKeysToElementById("q", "Macbook pro 2050");

        Log.info(method.getName() + " - " + "Step 02: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 03: Verify search result displayed with text: 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getSearchResultText(), "No products were found that matched your criteria.");

    }

    @Test
    public void TC_03_Search_With_Product_Name_Relative(Method method) {
        startTest(method.getName(), "Try to search with product name relatively, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Lenovo'");
        searchPage.sendKeysToElementById("q", "Lenovo");

        Log.info(method.getName() + " - " + "Step 02: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 03: Verify search result displayed with 2 item that have Lenovo on its name'");
        verifyEquals(searchPage.getNumberOfResultItemWithText("Lenovo"), 2);
    }

    @Test
    public void TC_04_Search_with_Product_Name_Absolute(Method method) {
        startTest(method.getName(), "Try to search with product name absolutely, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'ThinkPad X1 Carbon'");
        searchPage.sendKeysToElementById("q", "ThinkPad X1 Carbon");

        Log.info(method.getName() + " - " + "Step 02: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 03: Verify search result displayed only 1 item that have ThinkPad X1 Carbon on its name");
        verifyEquals(searchPage.getNumberOfResultItemWithText("ThinkPad X1 Carbon"), 1);
    }

    @Test
    public void TC_05_Advanced_Search_With_Parent_Categories(Method method) {
        startTest(method.getName(), "Advanced search with parent categories, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Apple MacBook Pro'");
        searchPage.sendKeysToElementById("q", "Apple MacBook Pro");

        Log.info(method.getName() + " - " + "Step 02: Check the advanced search checkbox");
        searchPage.setCheckboxStatusById("checked", "advs");

        Log.info(method.getName() + " - " + "Step 03: Select computer options at category dropdown list");
        searchPage.selectDropDownListById("cid", "Computers");

        Log.info(method.getName() + " - " + "Step 04: Uncheck the Automatically search sub categories checkbox");
        searchPage.setCheckboxStatusById("unchecked","isc");

        Log.info(method.getName() + " - " + "Step 05: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 06: Verify search result displayed with text: 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getSearchResultText(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_06_Advanced_Search_With_Sub_Categories(Method method) {
        startTest(method.getName(), "Advanced search with sub category, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Apple MacBook Pro'");
        searchPage.sendKeysToElementById("q", "Apple MacBook Pro");

        Log.info(method.getName() + " - " + "Step 02: Check the advanced search checkbox");
        searchPage.setCheckboxStatusById("checked","advs");

        Log.info(method.getName() + " - " + "Step 03: Select computer options at category dropdown list");
        searchPage.selectDropDownListById("cid", "Computers");

        Log.info(method.getName() + " - " + "Step 04: Check the Automatically search sub categories checkbox");
        searchPage.setCheckboxStatusById("checked","isc");

        Log.info(method.getName() + " - " + "Step 05: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 06: Verify search result displayed with text: 'Search term minimum length is 3 characters'");
        verifyEquals(searchPage.getNumberOfResultItemWithText("Apple MacBook Pro"), 1);
    }

    @Test
    public void TC_07_Advanced_Search_With_Incorrect_Manufacturer(Method method) {
        startTest(method.getName(), "Advanced search with incorrect manufacturer, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Apple MacBook Pro'");
        searchPage.sendKeysToElementById("q", "Apple MacBook Pro");

        Log.info(method.getName() + " - " + "Step 02: Check the advanced search checkbox");
        searchPage.setCheckboxStatusById("checked", "advs");

        Log.info(method.getName() + " - " + "Step 03: Select computer options at category dropdown list");
        searchPage.selectDropDownListById("cid", "Computers");

        Log.info(method.getName() + " - " + "Step 04: Check the Automatically search sub categories checkbox");
        searchPage.setCheckboxStatusById("checked","isc");

        Log.info(method.getName() + " - " + "Step 05: Select HP options at Manufacturer dropdown list");
        searchPage.selectDropDownListById("mid", "HP");

        Log.info(method.getName() + " - " + "Step 06: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 07: Verify search result displayed with text: 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getSearchResultText(), "No products were found that matched your criteria.");
    }

    @Test
    public void TC_08_Advanced_Search_With_Correct_Manufacturer(Method method) {
        startTest(method.getName(), "Advanced search with correct manufacturer, verify search result");

        Log.info(method.getName() + " - " + "Step 01: Enter to search keyword textbox with text: 'Apple MacBook Pro'");
        searchPage.sendKeysToElementById("q", "Apple MacBook Pro");

        Log.info(method.getName() + " - " + "Step 02: Check the advanced search checkbox");
        searchPage.setCheckboxStatusById("checked","advs");

        Log.info(method.getName() + " - " + "Step 03: Select computer options at category dropdown list");
        searchPage.selectDropDownListById("cid", "Computers");

        Log.info(method.getName() + " - " + "Step 04: Check the Automatically search sub categories checkbox");
        searchPage.setCheckboxStatusById("checked","isc");

        Log.info(method.getName() + " - " + "Step 05: Select Apple options at Manufacturer dropdown list");
        searchPage.selectDropDownListById("mid", "Apple");

        Log.info(method.getName() + " - " + "Step 06: click search button");
        searchPage.clickToSearchButton();

        Log.info(method.getName() + " - " + "Step 06: Verify search result displayed with text: 'Search term minimum length is 3 characters'");
        verifyEquals(searchPage.getNumberOfResultItemWithText("Apple MacBook Pro"), 1);
    }

    @AfterClass
    public void afterClass(){
        //closeBrowserAndDriver();
    }

}
