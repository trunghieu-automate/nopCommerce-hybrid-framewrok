package testcases.admin;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.admin.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.dateTimeHelper.DateTimeHelper;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;


public class Admin extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    SearchProductPageObject searchProductPage;
    EditProductPageObject editProductPage;
    CustomerPageObject customerPage;
    DataHelper.User user;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver("chrome", Constants.adminPageLogin);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        user = DataHelper.getRandomUserInfo(Constants.mainResourcePath + "userData.json");
        loginPage.clickToButtonByTextName("Log in"); //button[text()='Log in']
    }

    @Test
    public void TC_01_Search_With_Product_Name(Method method) {
        startTest(method.getName(), "Search by product name");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Catalog > Products to navigate to Product search");
        loginPage.clickToLeftNavBarByMenuName("Catalog", "Products");
        searchProductPage = PageGeneratorManager.getSearchProductPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to Product name textbox text: 'Lenovo IdeaCentre 600 All-in-One PC'");
        searchProductPage.sendKeysToElementById("SearchProductName", "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 03: Click to search button");
        searchProductPage.clickToElementById("search-products");

        Log.info(method.getName() + " - " + "Step 04: Verify only 1 item is displayed at grid info");
        verifyEquals(searchProductPage.getNumberOfItemDisplayedAtGridInfo(), "1");

        Log.info(method.getName() + " - " + "Step 05: Verify product name is displayed Product table");
        verifyTrue(searchProductPage.isItemNameInProductTableDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));

        Log.info(method.getName() + " - " + "Step 06: Verify product SKU 'LE_IC_600' is displayed");
        verifyEquals(searchProductPage.getProductSKUByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "LE_IC_600");

        Log.info(method.getName() + " - " + "Step 07: Verify product price '500' is displayed");
        verifyEquals(searchProductPage.getProductUnitPriceByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "500");

        Log.info(method.getName() + " - " + "Step 08: Verify product stock quantity '10000' is displayed");
        verifyEquals(searchProductPage.getProductStockQuantityByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "10000");

    }

    @Test
    public void TC_02_Search_With_Product_Name_And_Category_Unchecked_Subcategory(Method method) {
        startTest(method.getName(), "Search by product name and category, unchecked subcategory checkbox");

        Log.info(method.getName() + " - " + "Step 01: Enter to Product name textbox text: 'Lenovo IdeaCentre 600 All-in-One PC'");
        searchProductPage.sendKeysToElementById("SearchProductName", "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 02: Select options 'Computers' in Category dropdown list");
        searchProductPage.selectDropDownListById("SearchCategoryId", "Computers");

        Log.info(method.getName() + " - " + "Step 03: Unchecked Search subcategories checkbox");
        searchProductPage.checkTheSubcategoriesCheckbox(false);

        Log.info(method.getName() + " - " + "Step 04: Click to search button");
        searchProductPage.clickToElementById("search-products");

        Log.info(method.getName() + " - " + "Step 05: Verify no data in table text is displayed");
        verifyTrue(searchProductPage.isProductTableNoDataTextDisplayed());
    }

   @Test
    public void TC_03_Search_With_Product_Name_And_Category_Checked_Subcategory(Method method) {
        startTest(method.getName(), "Search by product name and category, checked subcategory checkbox");

        Log.info(method.getName() + " - " + "Step 01: Enter to Product name textbox text: 'Lenovo IdeaCentre 600 All-in-One PC'");
        searchProductPage.sendKeysToElementById("SearchProductName", "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 02: Select options 'Computers' in Category dropdown list");
        searchProductPage.selectDropDownListById("SearchCategoryId", "Computers");

        Log.info(method.getName() + " - " + "Step 03: Unchecked Search subcategories checkbox");
        searchProductPage.checkTheSubcategoriesCheckbox(true);

        Log.info(method.getName() + " - " + "Step 04: Click to search button");
        searchProductPage.clickToElementById("search-products");

        Log.info(method.getName() + " - " + "Step 05: Verify only 1 item is displayed at grid info");
        verifyEquals(searchProductPage.getNumberOfItemDisplayedAtGridInfo(), "1");

        Log.info(method.getName() + " - " + "Step 07: Verify product name is displayed Product table");
        verifyTrue(searchProductPage.isItemNameInProductTableDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));

        Log.info(method.getName() + " - " + "Step 08: Verify product SKU 'LE_IC_600' is displayed");
        verifyEquals(searchProductPage.getProductSKUByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "LE_IC_600");

        Log.info(method.getName() + " - " + "Step 09: Verify product price '500' is displayed");
        verifyEquals(searchProductPage.getProductUnitPriceByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "500");

        Log.info(method.getName() + " - " + "Step 10: Verify product stock quantity '10000' is displayed");
        verifyEquals(searchProductPage.getProductStockQuantityByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "10000");
    }

    @Test
    public void TC_04_Search_With_Product_Name_And_Child_Category(Method method) {
        startTest(method.getName(), "Search by product name and category with child");

        Log.info(method.getName() + " - " + "Step 01: Enter to Product name textbox text: 'Lenovo IdeaCentre 600 All-in-One PC'");
        searchProductPage.sendKeysToElementById("SearchProductName", "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 02: Select options 'Computers' in Category dropdown list");
        searchProductPage.selectDropDownListById("SearchCategoryId", "Computers >> Desktops");

        Log.info(method.getName() + " - " + "Step 03: Unchecked Search subcategories checkbox");
        searchProductPage.checkTheSubcategoriesCheckbox(false);

        Log.info(method.getName() + " - " + "Step 04: Click to search button");
        searchProductPage.clickToElementById("search-products");

        Log.info(method.getName() + " - " + "Step 05: Verify only 1 item is displayed at grid info");
        verifyEquals(searchProductPage.getNumberOfItemDisplayedAtGridInfo(), "1");

        Log.info(method.getName() + " - " + "Step 07: Verify product name is displayed Product table");
        verifyTrue(searchProductPage.isItemNameInProductTableDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));

        Log.info(method.getName() + " - " + "Step 08: Verify product SKU 'LE_IC_600' is displayed");
        verifyEquals(searchProductPage.getProductSKUByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "LE_IC_600");

        Log.info(method.getName() + " - " + "Step 09: Verify product price '500' is displayed");
        verifyEquals(searchProductPage.getProductUnitPriceByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "500");

        Log.info(method.getName() + " - " + "Step 10: Verify product stock quantity '10000' is displayed");
        verifyEquals(searchProductPage.getProductStockQuantityByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "10000");
    }

    @Test
    public void TC_05_Search_Product_With_Product_Name_And_Manufacturer(Method method) {
        startTest(method.getName(), "Search by product name, all category, Uncheck subcategory, and Manufacturer");

        Log.info(method.getName() + " - " + "Step 01: Enter to Product name textbox text: 'Lenovo IdeaCentre 600 All-in-One PC'");
        searchProductPage.sendKeysToElementById("SearchProductName", "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 02: Select options 'Computers' in Category dropdown list");
        searchProductPage.selectDropDownListById("SearchCategoryId", "All");

        Log.info(method.getName() + " - " + "Step 03: Unchecked Search subcategories checkbox");
        searchProductPage.checkTheSubcategoriesCheckbox(false);

        Log.info(method.getName() + " - " + "Step 04: Select options 'Apple' in Manufacturer dropdown list");
        searchProductPage.selectDropDownListById("SearchManufacturerId", "Apple");

        Log.info(method.getName() + " - " + "Step 05: Click to search button");
        searchProductPage.clickToElementById("search-products");

        Log.info(method.getName() + " - " + "Step 05: Verify no data in table text is displayed");
        verifyTrue(searchProductPage.isProductTableNoDataTextDisplayed());
    }

    @Test
    public void TC_06_Go_Directly_To_Product_SKU(Method method) {
        startTest(method.getName(), "Search by product name, all category, Uncheck subcategory, and Manufacturer");

        Log.info(method.getName() + " - " + "Step 01: Enter to Go directly to SKU textbox: 'LE_IC_600'");
        searchProductPage.sendKeysToElementById("GoDirectlyToSku", "LE_IC_600");

        Log.info(method.getName() + " - " + "Step 02: Click to Go button");
        editProductPage = searchProductPage.clickToGoDirectlyToEditProductPage();

        Log.info(method.getName() + " - " + "Step 03: CLick to open product info card");
        editProductPage.clickToCardById("product-info");

        Log.info(method.getName() + " - " + "Step 03: Verify edit product page is displayed with heading text: '" +
                "Edit product details - Lenovo IdeaCentre 600 All-in-One PC'");
        verifyTrue(editProductPage.isEditPageHeadingTextContainProductName("Lenovo IdeaCentre 600 All-in-One PC"));

        Log.info(method.getName() + " - " + "Step 04: Verify Product info card, product name textbox value is correct");
        verifyEquals(editProductPage.getTextboxValueByMenuName("Product name"), "Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 05: Verify Product info card, SKU textbox value is correct");
        verifyEquals(editProductPage.getTextboxValueByMenuName("SKU"), "LE_IC_600");
    }

    @Test
    public void TC_07_Create_New_Customer(Method method) {
        startTest(method.getName(), "Create new customer");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Click to Add new button");
        customerPage.clickToAddNewButton();

        Log.info(method.getName() + " - " + "Step 03: Enter to Email textbox: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 04: Enter to Password textbox: [" + user.getPassword() + "]");
        customerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info(method.getName() + " - " + "Step 05: Enter to Firstname textbox: [" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 06: Enter to Lastname textbox: [" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 07: Check to Gender radio button: [Male]");
        customerPage.clickToGenderRadioButton("Male");

        Log.info(method.getName() + " - " + "Step 08: Enter to Date of Birth textbox: [" + DateTimeHelper.convertToUSDateFormat(user.getDob()) + "]");
        customerPage.sendKeysToElementById("DateOfBirth", DateTimeHelper.convertToUSDateFormat(user.getDob()));

        Log.info(method.getName() + " - " + "Step 09: Enter to Company name textbox: [Apple Inc]");
        customerPage.sendKeysToElementById("Company", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 10: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 11: Select Role dropdown list: [Guests]");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 12: Check to Active checkbox");
        customerPage.clickToCheckBoxById("Active");

        Log.info(method.getName() + " - " + "Step 13: Enter to Admin comment textarea: [Add new customer [Guests]]");
        customerPage.sendKeysToElementById("AdminComment", "Add new customer [Guests]");

        Log.info(method.getName() + " - " + "Step 14: Click to Add new button");
        customerPage.clickToButtonByTextName("Save and Continue Edit");
        DataHelper.overWriteCompanyInfoToFile(Constants.mainResourcePath + "currentUser.json", user, "Apple Inc");

        Log.info(method.getName() + " - " + "Step 15: Verify create customer successfully alert is displayed");
        verifyTrue(customerPage.isCustomerCreationSuccessfullyAlertDisplayed());

        Log.info(method.getName() + " - " + "Step 16: Verify Email is saved as [" + user.getEmail() + "]");
        verifyEquals(customerPage.getTextboxValueById("Email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 17: Verify Password is saved as [" + user.getPassword() + "]");
        verifyEquals(customerPage.getTextboxValueById("Password"), "");

        Log.info(method.getName() + " - " + "Step 18: Verify FirstName is saved as [N/A]");
        verifyEquals(customerPage.getTextboxValueById("FirstName"), user.getFirstName());

        Log.info(method.getName() + " - " + "Step 19: Verify LastName is save as [" + user.getLastName() + "]");
        verifyEquals(customerPage.getTextboxValueById("LastName"), user.getLastName());

        Log.info(method.getName() + " - " + "Step 20: Verify Male radio button is checked");
        verifyTrue(customerPage.isGenderRadioButtonSelected("Male"));

        Log.info(method.getName() + " - " + "Step 21: Verify Date of birth textbox is save as [" + DateTimeHelper.convertToUSDateFormat(user.getDob()) + "]");
        verifyEquals(customerPage.getTextboxValueById("DateOfBirth"), DateTimeHelper.convertToUSDateFormat(user.getDob()));

        Log.info(method.getName() + " - " + "Step 22: Verify Company is save as [Apple Inc]");
        verifyEquals(customerPage.getTextboxValueById("Company"), user.getCompany());

        Log.info(method.getName() + " - " + "Step 23: Verify Role is save as [Guests]");
        verifyTrue(customerPage.isCustomerRoleDisplayed("Guests"));

        Log.info(method.getName() + " - " + "Step 24: Verify Active checkbox is checked");
        verifyTrue(customerPage.isActiveCheckboxChecked());

        Log.info(method.getName() + " - " + "Step 25: Verify Admin comment textarea is save as [Add new customer [Guests]]");
        verifyEquals(customerPage.getTextAreaValueById("AdminComment"), "Add new customer [Guests]");

    }

    @Test
    public void TC_08_Search_Customer_With_Email(Method method) {
        startTest(method.getName(), "Search customer with email");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 04: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 05: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 06: Verify only 1 item is displayed at grid info");
        verifyEquals(customerPage.getNumberOfItemDisplayedAtGridInfo(), 1);

        Log.info(method.getName() + " - " + "Step 07: Verify customer name is displayed at the result table");
        verifyTrue(customerPage.isCustomerNameInCustomerTableDisplayed(user.getFullName()));

        Log.info(method.getName() + " - " + "Step 08: Verify customer role is [Guests]");
        verifyEquals(customerPage.getCustomerRoleInCustomerTableByFullName(user.getFullName()), "Guests");

        Log.info(method.getName() + " - " + "Step 09: Verify customer company is [Apple Inc]");
        verifyEquals(customerPage.getCustomerCompanyInCustomerTableByFullName(user.getFullName()), user.getCompany());
    }

    @Test
    public void TC_09_Search_Customer_With_FirstName_And_LastName(Method method) {
        startTest(method.getName(), "Search customer by enter firstname and lastname textbox then click search button, verify result");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to firstname textbox with text: [" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 03: Enter to lastname textbox with text: [" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 04: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 05: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 06: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 07: Verify only 1 item is displayed at grid info");
        verifyEquals(customerPage.getNumberOfItemDisplayedAtGridInfo(), 1);

        Log.info(method.getName() + " - " + "Step 08: Verify customer name is displayed at the result table");
        verifyTrue(customerPage.isCustomerNameInCustomerTableDisplayed(user.getFullName()));

        Log.info(method.getName() + " - " + "Step 09: Verify customer role is [Guests]");
        verifyEquals(customerPage.getCustomerRoleInCustomerTableByFullName(user.getFullName()), "Guests");

        Log.info(method.getName() + " - " + "Step 10: Verify customer company is [Apple Inc]");
        verifyEquals(customerPage.getCustomerCompanyInCustomerTableByFullName(user.getFullName()), user.getCompany());
    }

    @Test
    public void TC_10_Search_With_Company(Method method) {
        startTest(method.getName(), "Search customer with email");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to company textbox with text: [Apple Inc]");
        customerPage.sendKeysToElementById("SearchCompany", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 03: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 04: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 05: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 06: Verify having at least  1 item is displayed at grid info");
        verifyTrue(customerPage.getNumberOfItemDisplayedAtGridInfo()>=1);

        Log.info(method.getName() + " - " + "Step 07: Verify customer name is displayed at the result table");
        verifyTrue(customerPage.isCustomerNameInCustomerTableDisplayed(user.getFullName()));

        Log.info(method.getName() + " - " + "Step 08: Verify customer role is [Guests]");
        verifyEquals(customerPage.getCustomerRoleInCustomerTableByFullName(user.getFullName()), "Guests");

        Log.info(method.getName() + " - " + "Step 09: Verify customer company is [Apple Inc]");
        verifyEquals(customerPage.getCustomerCompanyInCustomerTableByFullName(user.getFullName()), user.getCompany());
    }

    @Test
    public void TC_11_Search_With_Full_Data(Method method) {
        startTest(method.getName(), "Search customer with email, first name, last name, Company, role, date of birth, then verify result");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 04: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 05: Enter to company textbox with text: [Apple Inc]");
        customerPage.sendKeysToElementById("SearchCompany", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 06: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 07: Select Day of birth month dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 08: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 09: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 10: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 11: Verify only 1 item is displayed at grid info");
        verifyEquals(customerPage.getNumberOfItemDisplayedAtGridInfo(), 1);

        Log.info(method.getName() + " - " + "Step 12: Verify customer email is displayed at the result table");
        verifyTrue(customerPage.isCustomerNameInCustomerTableDisplayed(user.getFullName()));

        Log.info(method.getName() + " - " + "Step 13: Verify customer role is [Guests]");
        verifyEquals(customerPage.getCustomerRoleInCustomerTableByFullName(user.getFullName()), "Guests");

        Log.info(method.getName() + " - " + "Step 14: Verify customer company is [Apple Inc]");
        verifyEquals(customerPage.getCustomerCompanyInCustomerTableByFullName(user.getFullName()), user.getCompany());
    }

    @Test
    public void TC_12_Edit_Customer(Method method) {
        startTest(method.getName(), "search customer with full data (email, first name, last name, dat of birth, month of birth, company, role," +
                "then edit information, then verify edited information is correct.");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 04: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 05: Enter to company textbox with text: [Apple Inc]");
        customerPage.sendKeysToElementById("SearchCompany", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 06: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 07: Select Day of birth day dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 08: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 09: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 10: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 11: Click to edit button by customer name: [" + user.getFullName() + "]");
        customerPage.clickToEditButtonByCustomerFullName(user.getFullName());

        Log.info(method.getName() + " - " + "Step 12: Enter to Email textbox: [edit_" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("Email", "edit_" + user.getEmail());

        Log.info(method.getName() + " - " + "Step 13: Enter to Firstname textbox: [Edit " + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("FirstName", "Edit " + user.getFirstName());

        Log.info(method.getName() + " - " + "Step 14: Enter to Lastname textbox: [Edit " + user.getLastName() + "]");
        customerPage.sendKeysToElementById("LastName", "Edit " + user.getLastName());

        Log.info(method.getName() + " - " + "Step 15: Check to Gender radio button: [Female]");
        customerPage.clickToGenderRadioButton("Female");

        Log.info(method.getName() + " - " + "Step 16: Enter to Date of Birth textbox: [12/12/1912]");
        customerPage.sendKeysToElementById("DateOfBirth", "12/12/1912");

        Log.info(method.getName() + " - " + "Step 17: Enter to Company name textbox: [Edit Apple Inc]");
        customerPage.sendKeysToElementById("Company", "Edit Apple Inc");

        Log.info(method.getName() + " - " + "Step 18: Enter to Admin comment textarea: [Edit customer [Guests]]");
        customerPage.sendKeysToElementById("AdminComment", "Edit customer [Guests]");

        Log.info(method.getName() + " - " + "Step 19: Enter to Admin comment textarea: [Edit customer [Guests]]");
        customerPage.clickToButtonByTextName("Save");
        DataHelper.overWriteUserInfoToFile(Constants.mainResourcePath + "currentUser.json", user, "Edit " + user.getFirstName(),
                "Edit " + user.getLastName(), "edit_" + user.getEmail(), "12/12/1912", "Female", "Edit Apple Inc");

        Log.info(method.getName() + " - " + "Step 20: Verify create customer successfully alert is displayed");
        verifyTrue(customerPage.isCustomerUpdatedSuccessfullyAlertDisplayed());

        Log.info(method.getName() + " - " + "Step 21: Click to left nav bar Customers > Customers to navigate to Customer page");
        customerPage.clickToLeftNavBarByMenuName("Customers", "Customers");

        Log.info(method.getName() + " - " + "Step 22: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 23: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 24: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 25: Enter to company textbox with text: [" + user.getCompany() + "]");
        customerPage.sendKeysToElementById("SearchCompany", user.getCompany());

        Log.info(method.getName() + " - " + "Step 26: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 27: Select Day of birth month dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 28: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 29: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 30: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 31: Verify only 1 item is displayed at grid info");
        verifyEquals(customerPage.getNumberOfItemDisplayedAtGridInfo(), 1);

        Log.info(method.getName() + " - " + "Step 32: Verify customer email is displayed at the result table");
        verifyTrue(customerPage.isCustomerNameInCustomerTableDisplayed(user.getFullName()));
        //table[@id='products-grid']//tbody//tr//td[text()='%s']

        Log.info(method.getName() + " - " + "Step 33: Verify customer role is [Guests]");
        verifyEquals(customerPage.getCustomerRoleInCustomerTableByFullName(user.getFullName()), "Guests");

        Log.info(method.getName() + " - " + "Step 34: Verify customer company is [Edit Apple Inc]");
        verifyEquals(customerPage.getCustomerCompanyInCustomerTableByFullName(user.getFullName()), user.getCompany());
    }

    @Test
    public void TC_13_Add_New_Address_In_Customer_Detail(Method method) {
        startTest(method.getName(), "Open customer search page, search with full data a registered customer, " +
                "add new address, then verify information");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 04: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 05: Enter to company textbox with text: [" + user.getCompany() + "]");
        customerPage.sendKeysToElementById("SearchCompany", user.getCompany());

        Log.info(method.getName() + " - " + "Step 06: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 07: Select Day of birth month dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 08 Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 09: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 10: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 11: Click to edit button of customer name: [" + user.getFullName() + "]");
        customerPage.clickToEditButtonByCustomerFullName(user.getFullName());

        Log.info(method.getName() + " - " + "Step 12: Click to addresses info card");
        customerPage.clickToCardById("customer-address");

        Log.info(method.getName() + " - " + "Step 13: Click to Add new Address button");
        customerPage.clickToButtonByTextName("Add new address");

        Log.info(method.getName() + " - " + "Step 14: Enter to First Name textbox: [" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("Address_FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 15: Enter to Last Name textbox: [" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("Address_LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 16: Enter to Email textbox: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("Address_Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 17: Enter to Company textbox: [" + user.getCompany() + "]");
        customerPage.sendKeysToElementById("Address_Company", user.getCompany());

        Log.info(method.getName() + " - " + "Step 18: Select option [Viet Nam] at Country dropdown list");
        customerPage.selectDropDownListById("Address_CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 19: Select option [Other] at State dropdown list");
        customerPage.selectDropDownListById("Address_StateProvinceId", "Other");

        Log.info(method.getName() + " - " + "Step 20: Enter to County/ region textbox: [Central]");
        customerPage.sendKeysToElementById("Address_County", "Central");

        Log.info(method.getName() + " - " + "Step 21: Enter to City textbox: [Da Nang]");
        customerPage.sendKeysToElementById("Address_City", "Da Nang");

        Log.info(method.getName() + " - " + "Step 22: Enter to Adrress 1 textbox: [321 Nguyen Van Linh]");
        customerPage.sendKeysToElementById("Address_Address1", "321 Nguyen Van Linh");

        Log.info(method.getName() + " - " + "Step 23: Enter to Address 2 textbox: [123 Hung Vuong]");
        customerPage.sendKeysToElementById("Address_Address2", "123 Hung Vuong");

        Log.info(method.getName() + " - " + "Step 24: Enter to Zip Postal code textbox: [550000]");
        customerPage.sendKeysToElementById("Address_ZipPostalCode", "550000");

        Log.info(method.getName() + " - " + "Step 25: Enter to Phone number textbox: [0979797979]");
        customerPage.sendKeysToElementById("Address_PhoneNumber", "0979797979");

        Log.info(method.getName() + " - " + "Step 26: Enter to Fax number textbox: [0939393939]");
        customerPage.sendKeysToElementById("Address_FaxNumber", "0939393939");

        Log.info(method.getName() + " - " + "Step 27: Click to Save button");
        customerPage.clickToButtonByTextName("Save");
        DataHelper.overWriteAddressInfoToUserFile(Constants.mainResourcePath + "admin-address.json", user, "Viet Nam",
                "Central", "Other", "Da Nang", "321 Nguyen Van Linh", "123 Hung Vuong",
                "550000", "0979797979", "0939393939");

        Log.info(method.getName() + " - " + "Step 28: Verify first name textbox value is: [" + user.getFirstName() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_FirstName"), user.getFirstName());

        Log.info(method.getName() + " - " + "Step 29: Verify last name textbox value is: [" + user.getLastName() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_LastName"), user.getLastName());

        Log.info(method.getName() + " - " + "Step 30: Verify email textbox value is: [" + user.getEmail() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 31: Verify company textbox value is: [" + user.getCompany() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Company"), user.getCompany());

        Log.info(method.getName() + " - " + "Step 32: Verify country dropdown list selected option is: [" + user.getAddress().get(0).getCountry() + "]");
        verifyEquals(customerPage.getDropdownListOptionById("Address_CountryId"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 33: Verify state dropdown list selected option is: [" + user.getAddress().get(0).getState() + "]");
        verifyEquals(customerPage.getDropdownListOptionById("Address_StateProvinceId"), user.getAddress().get(0).getState());

        Log.info(method.getName() + " - " + "Step 34: Verify country/ region textbox value is: [" + user.getAddress().get(0).getRegion() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_County"), user.getAddress().get(0).getRegion());

        Log.info(method.getName() + " - " + "Step 35: Verify city textbox value is: [" + user.getAddress().get(0).getCity() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_City"), user.getAddress().get(0).getCity());

        Log.info(method.getName() + " - " + "Step 36: Verify address 1 textbox value is: [" + user.getAddress().get(0).getAddress1() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 34: Verify address 2 textbox value is: [" + user.getAddress().get(0).getAddress2() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Address2"), user.getAddress().get(0).getAddress2());

        Log.info(method.getName() + " - " + "Step 34: Verify zip code textbox value is: [" + user.getAddress().get(0).getZip() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_ZipPostalCode"), user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 34: Verify phone number textbox value is: [" + user.getAddress().get(0).getPhoneNo() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_PhoneNumber"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 34: Verify fax number textbox value is: [" + user.getAddress().get(0).getFaxNo() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_FaxNumber"), user.getAddress().get(0).getFaxNo());
    }

    @Test
    public void TC_14_Edit_Address_In_Customer_Detail(Method method) {
        startTest(method.getName(), "Open customer page, search current user, then edit address, verify information are edited");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 04: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 05: Enter to company textbox with text: [Apple Inc]");
        customerPage.sendKeysToElementById("SearchCompany", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 06: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 07: Select Day of birth month dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 08: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 09: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 10: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 11: Click to edit button of customer email: [" + user.getFullName() + "]");
        customerPage.clickToEditButtonByCustomerFullName(user.getFullName());

        Log.info(method.getName() + " - " + "Step 12: Click to addresses info card");
        customerPage.clickToCardById("customer-address");

        Log.info(method.getName() + " - " + "Step 13: Click to edit button in address info card by email: [" + user.getEmail() + "]");
        customerPage.clickEditButtonInAddressInfoCardByEmail(user.getEmail());

        Log.info(method.getName() + " - " + "Step 14: Enter to First Name textbox: [" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("Address_FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 15: Enter to Last Name textbox: [" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("Address_LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 16: Enter to Email textbox: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("Address_Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 17: Enter to Company textbox: [" + user.getCompany() + "]");
        customerPage.sendKeysToElementById("Address_Company", user.getCompany());

        Log.info(method.getName() + " - " + "Step 18: Select option [United States] at Country dropdown list");
        customerPage.selectDropDownListById("Address_CountryId", "United States");

        Log.info(method.getName() + " - " + "Step 19: Select option [California] at State dropdown list");
        customerPage.selectDropDownListById("Address_StateProvinceId", "California");

        Log.info(method.getName() + " - " + "Step 20: Enter to County/ region textbox: [Western United States]");
        customerPage.sendKeysToElementById("Address_County", "Western United States");

        Log.info(method.getName() + " - " + "Step 21: Enter to City textbox: [Los Angeles]");
        customerPage.sendKeysToElementById("Address_City", "Los Angeles");

        Log.info(method.getName() + " - " + "Step 22: Enter to Adrress 1 textbox: [200 Santa Monica Pier, Santa Monica]");
        customerPage.sendKeysToElementById("Address_Address1", "200 Santa Monica Pier, Santa Monica");

        Log.info(method.getName() + " - " + "Step 23: Enter to Address 2 textbox: [2800 E Observatory Rd]");
        customerPage.sendKeysToElementById("Address_Address2", "2800 E Observatory Rd");

        Log.info(method.getName() + " - " + "Step 24: Enter to Zip Postal code textbox: [936979]");
        customerPage.sendKeysToElementById("Address_ZipPostalCode", "936979");

        Log.info(method.getName() + " - " + "Step 25: Enter to Phone number textbox: [0123456799]");
        customerPage.sendKeysToElementById("Address_PhoneNumber", "0123456799");

        Log.info(method.getName() + " - " + "Step 26: Enter to Fax number textbox: [0939123569]");
        customerPage.sendKeysToElementById("Address_FaxNumber", "0939123569");

        Log.info(method.getName() + " - " + "Step 27: Click to Save button");
        customerPage.clickToButtonByTextName("Save");
        DataHelper.overWriteCurrentAddressInfoToUserFile(Constants.mainResourcePath + "admin-address.json", user, "United States",
                "Western United States", "California", "Los Angeles", "200 Santa Monica Pier, Santa Monica", "2800 E Observatory Rd",
                "936979", "0123456799", "0939123569");

        Log.info(method.getName() + " - " + "Step 28: Verify first name textbox value is: [" + user.getFirstName() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_FirstName"), user.getFirstName());

        Log.info(method.getName() + " - " + "Step 29: Verify last name textbox value is: [" + user.getLastName() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_LastName"), user.getLastName());

        Log.info(method.getName() + " - " + "Step 30: Verify email textbox value is: [" + user.getEmail() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 31: Verify company textbox value is: [" + user.getCompany() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Company"), user.getCompany());

        Log.info(method.getName() + " - " + "Step 32: Verify country dropdown list selected option is: [" + user.getAddress().get(0).getCountry() + "]");
        verifyEquals(customerPage.getDropdownListOptionById("Address_CountryId"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 33: Verify state dropdown list selected option is: [" + user.getAddress().get(0).getState() + "]");
        verifyEquals(customerPage.getDropdownListOptionById("Address_StateProvinceId"), user.getAddress().get(0).getState());

        Log.info(method.getName() + " - " + "Step 34: Verify country/ region textbox value is: [" + user.getAddress().get(0).getRegion() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_County"), user.getAddress().get(0).getRegion());

        Log.info(method.getName() + " - " + "Step 35: Verify city textbox value is: [" + user.getAddress().get(0).getCity() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_City"), user.getAddress().get(0).getCity());

        Log.info(method.getName() + " - " + "Step 36: Verify address 1 textbox value is: [" + user.getAddress().get(0).getAddress1() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 34: Verify address 2 textbox value is: [" + user.getAddress().get(0).getAddress2() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_Address2"), user.getAddress().get(0).getAddress2());

        Log.info(method.getName() + " - " + "Step 34: Verify zip code textbox value is: [" + user.getAddress().get(0).getZip() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_ZipPostalCode"), user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 34: Verify phone number textbox value is: [" + user.getAddress().get(0).getPhoneNo() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_PhoneNumber"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 34: Verify fax number textbox value is: [" + user.getAddress().get(0).getFaxNo() + "]");
        verifyEquals(customerPage.getTextboxValueById("Address_FaxNumber"), user.getAddress().get(0).getFaxNo());
    }

    @Test
    public void TC_15_Delete_Address_In_Customer_Detail(Method method) {
        startTest(method.getName(), "Go to customer page, search current user, click to Edit button, then at the address info card, click to delete button");

        Log.info(method.getName() + " - " + "Step 01: Click to left nav bar Customers > Customers sub-menu");
        loginPage.clickToLeftNavBarByMenuName("Customers", "Customers");
        customerPage = PageGeneratorManager.getCustomerPage(driver);

        Log.info(method.getName() + " - " + "Step 02: Enter to email textbox with text: [" + user.getEmail() + "]");
        customerPage.sendKeysToElementById("SearchEmail", user.getEmail());

        Log.info(method.getName() + " - " + "Step 03: Enter to first name textbox with text:[" + user.getFirstName() + "]");
        customerPage.sendKeysToElementById("SearchFirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 04: Enter to last name textbox with text:[" + user.getLastName() + "]");
        customerPage.sendKeysToElementById("SearchLastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 05: Enter to company textbox with text: [Apple Inc]");
        customerPage.sendKeysToElementById("SearchCompany", "Apple Inc");

        Log.info(method.getName() + " - " + "Step 06: Select Day of birth month dropdown list option: [" + DateTimeHelper.getMonth(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchMonthOfBirth", DateTimeHelper.getMonth(user.getDob()));

        Log.info(method.getName() + " - " + "Step 07: Select Day of birth month dropdown list option: [" + DateTimeHelper.getDay(user.getDob()) + "]");
        customerPage.selectDropDownListById("SearchDayOfBirth", DateTimeHelper.getDay(user.getDob()));

        Log.info(method.getName() + " - " + "Step 08: Click to close button at the registered role");
        customerPage.clickToRoleDeleteButtonByName("Registered");

        Log.info(method.getName() + " - " + "Step 09: Select quests option at the role dropdown list");
        customerPage.selectRoleDropdownList("Guests");

        Log.info(method.getName() + " - " + "Step 10: Click to search button");
        customerPage.clickToButtonByTextName("Search");

        Log.info(method.getName() + " - " + "Step 11: Click to edit button of customer email: [" + user.getEmail() + "]");
        customerPage.clickToEditButtonByCustomerFullName(user.getFullName());

        Log.info(method.getName() + " - " + "Step 12: Click to addresses info card");
        customerPage.clickToCardById("customer-address");

        Log.info(method.getName() + " - " + "Step 13: Click to delete button in address info card by email: [" + user.getEmail() + "]");
        customerPage.clickDeleteButtonInAddressInfoCardByEmail(user.getEmail());

        Log.info(method.getName() + " - " + "Step 15: Verify address table has no data");
        verifyTrue(customerPage.isAddressTableDataIsEmptyWithTextDisplayed());
    }
}
