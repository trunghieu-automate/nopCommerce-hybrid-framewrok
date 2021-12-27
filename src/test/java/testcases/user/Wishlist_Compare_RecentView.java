package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.jsonHelper.DataHelper;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Wishlist_Compare_RecentView extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    ProductPageObject productPage;
    CartPageObject cartPage;
    WishlistPageObject wishlistPage;
    CompareProductPageObject compareProductPage;
    RecentViewPageObject recentViewPage;
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

        Log.info("Pre-condition Step 08: Navigate to Notebooks product page by clicking at product menu bar");
        productPage = registerPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info("Pre-condition Step 09: Click to Lenovo Thinkpad X1 Carbon Laptop product");
        productPage.clickToProductByName("Lenovo Thinkpad X1 Carbon Laptop");
    }

    @Test
    public void TC_01_Add_To_Wishlist(Method method) {
        startTest(method.getName(), "Add product Lenovo Thinkpad X1 Carbon Laptop to wishlist, " +
                "verify product is displayed in Wishlist Page");

        Log.info(method.getName() + " - " + "Step 01: Click to Add to Wishlist button at product page");
        productPage.clickToElementById("add-to-wishlist-button-9");

        Log.info(method.getName() + " - " + "Step 02: Verify add to wishlist success green notification bar displayed" +
                "with text: The product has been added to your wishlist");
        verifyTrue(productPage.isTopNotificationGreenBarDisplayedWithText("The product has been added to your wishlist"));

        Log.info(method.getName() + " - " + "Step 03: Click to close button at notification bar"); //...//span
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 04: Navigate to wishlist page by clicking wishlist menu at top nav bar");
        wishlistPage = (WishlistPageObject) productPage.clickToTopNavBar("wishlist");

        Log.info(method.getName() + " - " + "Step 05: Verify product 'Lenovo Thinkpad X1 Carbon Laptop' is displayed in wishlist page");
        verifyTrue(wishlistPage.isProductNameDisplayedInWishlistPage("Lenovo Thinkpad X1 Carbon Laptop"));
        //td[@class='product']//a[text()='%s']

        Log.info(method.getName() + " - " + "Step 06: Click to link below 'Your wishlist URL for sharing'");
        wishlistPage.clickToYourSharingLink(); //div[@class='share-info']/a

        Log.info(method.getName() + " - " + "Step 07: Verify the Table title text is 'Wishlist of "
                + user.getFirstName() + " " + user.getLastName() + "'");
        verifyEquals(wishlistPage.getWishlistTableTitleText(), "Wishlist of " + user.getFirstName() + " " + user.getLastName());
    }

    @Test
    public void TC_02_Add_To_Cart_From_WishlistPage(Method method) {
        startTest(method.getName(), "Add product at wishlist to cart, verify product is displayed at your cart" +
                "and remove from wishlist page");

        Log.info(method.getName() + " - " + "Step 01: Navigate back to Home page by clicking the logo");
        homePage = wishlistPage.clickToHeaderLogoImg();

        Log.info(method.getName() + " - " + "Step 02: Navigate to wishlist page by clicking menu on top nav bar");
        wishlistPage = (WishlistPageObject) homePage.clickToTopNavBar("wishlist");

        Log.info(method.getName() + " - " + "Step 03: Check the add to cart checkbox for 'Lenovo Thinkpad X1 Carbon Laptop' product");
        wishlistPage.checkTheAddToCartCheckboxByProductName("Lenovo Thinkpad X1 Carbon Laptop");

        Log.info(method.getName() + " - " + "Step 04: Click to add to cart button");
        wishlistPage.clickToButtonByTextName("Add to cart");
        cartPage = PageGeneratorManager.getCartPage(driver);

        Log.info(method.getName() + " - " + "Step 05: Verify 'Lenovo Thinkpad X1 Carbon Laptop' product is displayed at cart page");
        verifyTrue(cartPage.isProductNameDisplayedInCartPage("Lenovo Thinkpad X1 Carbon Laptop"));

        Log.info(method.getName() + " - " + "Step 06: Navigate to wishlist page by clicking at top nav bar");
        wishlistPage = (WishlistPageObject) cartPage.clickToTopNavBar("wishlist");

        Log.info(method.getName() + " - " + "Step 07: Verify 'Lenovo Thinkpad X1 Carbon Laptop' product is remove from wishlist page");
        verifyFalse(wishlistPage.isProductNameDisplayedInWishlistPage("Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void TC_03_Remove_Product_From_WishlistPage(Method method) {
        startTest(method.getName(), "Remove product form wishlist page, verify message displayed with text: 'The wishlist is empty!'");

        Log.info(method.getName() + " - " + "Step 01: Navigate to notebooks product page by clicking to product menu bar");
        productPage = wishlistPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to 'Apple MacBook Pro 13-inch' product");
        productPage.clickToProductByName("Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 03: Click to 'Add to wishlist' button");
        productPage.clickToElementById("add-to-wishlist-button-4");

        Log.info(method.getName() + " - " + "Step 04: CLick to Close button to close green bar notification bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 05: Navigate to wishlist page, by clicking to wishlist menu at top nav bar");
        wishlistPage = (WishlistPageObject) productPage.clickToTopNavBar("wishlist");

        Log.info(method.getName() + " - " + "Step 06: Click to red cross button at column Remove to remove product from wishlist");
        wishlistPage.clickRemoveButtonToRemoveProductByName("Apple MacBook Pro 13-inch");
        //a[text()='Lenovo Thinkpad X1 Carbon Laptop']//parent::td//following-sibling::td[@class='remove-from-cart']/button

        Log.info(method.getName() + " - " + "Step 07: Verify text 'The wishlist is empty!' displayed");
        verifyTrue(wishlistPage.isWishlistEmptyTextDisplayed());
        //div[@class='no-data']

        Log.info(method.getName() + " - " + "Step 08: Verify product is remove from wishlist page");
        verifyFalse(wishlistPage.isProductNameDisplayedInWishlistPage());
    }

    @Test
    public void TC_04_Add_Product_To_Compare(Method method) {
        startTest(method.getName(), "add 2 product to compare list, verify name, price, and  other information is displayed");

        Log.info(method.getName() + " - " + "Step 01: Navigate to notebooks product page by clicking to product menu bar");
        productPage = wishlistPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to Add to compare list of 'Apple MacBook Pro 13-inch' product ");
        productPage.clickToAddToCompareListByProductName("Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 03: Verify top green notification bar is displayed");
        verifyTrue(productPage.isTopNotificationGreenBarDisplayedWithText("The product has been added to your product comparison"));

        Log.info(method.getName() + " - " + "Step 04: Click to close button at notification bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 05: Click to Add to compare list of 'Lenovo Thinkpad X1 Carbon Laptop' product ");
        productPage.clickToAddToCompareListByProductName("Lenovo Thinkpad X1 Carbon Laptop");

        Log.info(method.getName() + " - " + "Step 06: Verify top green notification bar is displayed");
        verifyTrue(productPage.isTopNotificationGreenBarDisplayedWithText("The product has been added to your product comparison"));

        Log.info(method.getName() + " - " + "Step 07: Click to close button at notification bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 08: Navigate to compare product list page by clicking Compare product list menu at footer");
        compareProductPage  = (CompareProductPageObject) productPage.clickToFooterLinkByTextName("Compare products list");

        Log.info(method.getName() + " - " + "Step 09: Verify button clear list is displayed");
        verifyTrue(compareProductPage.isClearButtonDisplayed()); //a[@class='clear-list']

        Log.info(method.getName() + " - " + "Step 10: Verify name of product 'Lenovo Thinkpad X1 Carbon Laptop' is displayed");
        verifyTrue(compareProductPage.isProductDisplayedByName("Lenovo Thinkpad X1 Carbon Laptop")); //tr[@class='product-name']/td/a

        Log.info(method.getName() + " - " + "Step 11: Verify name of product 'Apple MacBook Pro 13-inch' is displayed");
        verifyTrue(compareProductPage.isProductDisplayedByName("Apple MacBook Pro 13-inch"));

        Log.info(method.getName() + " - " + "Step 12: Verify price of product 'Lenovo Thinkpad X1 Carbon Laptop' is displayed as '$1,360.00'");
        verifyTrue(compareProductPage.isProductPriceDisplayedByProductName("Lenovo Thinkpad X1 Carbon Laptop", "$1,360.00"));
        //tr[@class='product-name']/td/a[text()='Lenovo Thinkpad X1 Carbon Laptop']//ancestor::tr//following-sibling::tr[@class='product-price']/td[text()='$1,360.00']

        Log.info(method.getName() + " - " + "Step 13: Verify price of product 'Apple MacBook Pro 13-inch' is displayed as '$1,800.00'");
        verifyTrue(compareProductPage.isProductPriceDisplayedByProductName("Apple MacBook Pro 13-inch", "$1,800.00"));

        Log.info(method.getName() + " - " + "Step 14: Verify number of Removal button is equals to number of product displayed");
        verifyTrue(compareProductPage.isNumberOfRemovalButtonSameAsNumberOfProductDisplayed());
        //tr[@class='product-name']/td/a[text()='Lenovo Thinkpad X1 Carbon Laptop']//ancestor::tr//preceding-sibling::tr[@class='remove-product']/td/button[text()='Remove']

        Log.info(method.getName() + " - " + "Step 14: Click to clear list button"); //a[@class='clear-list']
        compareProductPage.clickToClearListButton();

        Log.info(method.getName() + " - " + "Step 15: Verify 'Apple MacBook Pro 13-inch' and 'Lenovo Thinkpad X1 Carbon Laptop' product isd undisplayed in compare page");
        verifyFalse(compareProductPage.isProductDisplayedByName("Apple MacBook Pro 13-inch", "Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void TC_05_Recently_Viewed_Products(Method method) {
        startTest(method.getName(), "View 5 products then verify only last 3 products are displayed in recent views menu");

        Log.info(method.getName() + " - " + "Step 01: Navigate to notebooks product menu by clicking at product menu bar");
        productPage = compareProductPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to product 'Apple MacBook Pro 13-inch'");
        productPage.clickToProductByName("Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 03: Navigate back");
        productPage.navigate("back");

        Log.info(method.getName() + " - " + "Step 04: Click to product 'Asus N551JK-XO076H Laptop'");
        productPage.clickToProductByName("Asus N551JK-XO076H Laptop");

        Log.info(method.getName() + " - " + "Step 05: Navigate back");
        productPage.navigate("back");

        Log.info(method.getName() + " - " + "Step 06: Click to product 'Samsung Series 9 NP900X4C Premium Ultrabook");
        productPage.clickToProductByName("Samsung Series 9 NP900X4C Premium Ultrabook");

        Log.info(method.getName() + " - " + "Step 07: Navigate back");
        productPage.navigate("back");

        Log.info(method.getName() + " - " + "Step 08: Click to product 'HP Spectre XT Pro UltraBook'");
        productPage.clickToProductByName("HP Spectre XT Pro UltraBook");

        Log.info(method.getName() + " - " + "Step 09: Navigate back");
        productPage.navigate("back");

        Log.info(method.getName() + " - " + "Step 10: Click to product 'HP Envy 6-1180ca 15.6-Inch Sleekbook'");
        productPage.clickToProductByName("HP Envy 6-1180ca 15.6-Inch Sleekbook");

        Log.info(method.getName() + " - " + "Step 11: Navigate to recent view menu by click at the footer");
        recentViewPage = (RecentViewPageObject) productPage.clickToFooterLinkByTextName("Recently viewed products");

        Log.info(method.getName() + " - " + "Step 12: Verify that only last 3 products are displayed");
        verifyTrue(recentViewPage.isProductDisplayedByName("HP Envy 6-1180ca 15.6-Inch Sleekbook", "HP Spectre XT Pro UltraBook", "Samsung Series 9 NP900X4C Premium Ultrabook"));

    }

    @AfterClass
    public void afterClass() {
    }
}
