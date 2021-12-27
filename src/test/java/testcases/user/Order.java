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

public class Order extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    ProductPageObject productPage;
    CartPageObject cartPage;
    CheckoutPageObject checkoutPage;
    MyAccountPageObject myAccountPage;
    OrderPageObject orderPage;
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

        Log.info("Pre-condition Step 04:  Enter to email textbox with: => [" + user.getEmail() + " ]");
        registerPage.sendKeysToElementById("Email", user.getEmail());

        Log.info("Pre-condition Step 05: Enter to password textbox with: => [" + user.getPassword() + " ]");
        registerPage.sendKeysToElementById("Password", user.getPassword());

        Log.info("Pre-condition Step 06: Enter to confirm Password textbox with:" + user.getPassword());
        registerPage.sendKeysToElementById("ConfirmPassword", user.getPassword());

        Log.info("Pre-condition Step 07: Click to register button");
        registerPage.clickToElementById("register-button");

        Log.info("Pre-condition Step 08: Navigate to Notebooks product page by clicking at product menu bar");
        productPage = registerPage.clickToProductMenuBarByTextName("Desktops");

        Log.info("Pre-condition Step 09: Click to Build your own computer link");
        productPage.clickToProductByName("Build your own computer");
    }

    @Test
    public void TC_01_Add_Product_To_Cart(Method method) {
        startTest(method.getName(), "Build your own computer, then add it to cart, " +
                "verify computer information is displayed correctly");

        Log.info(method.getName() + " - " + "Step 01: Select Processor dropdown list option: " +
                "'2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]'"); //product_attribute_1
        productPage.selectDropDownListById("product_attribute_1", "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");

        Log.info(method.getName() + " - " + "Step 02: Select RAM dropdown list option: '8GB [+$60.00]'");
        productPage.selectDropDownListById("product_attribute_2", "8GB [+$60.00]");

        Log.info(method.getName() + " - " + "Step 03: Click HDD options: '400 GB [+$100.00]'");
        productPage.selectRadioButtonById("product_attribute_3_7");

        Log.info(method.getName() + " - " + "Step 04: Click choose OS option: 'Vista Premium [+$60.00]'");
        productPage.selectRadioButtonById("product_attribute_4_9");

        Log.info(method.getName() + " - " + "Step 05: Check the checkbox Software: 'Microsoft Office [+$50.00]'");
        productPage.clickToCheckBoxById("product_attribute_5_10");

        Log.info(method.getName() + " - " + "Step 06: Check the checkbox Software: 'Acrobat Reader [+$10.00]");
        productPage.clickToCheckBoxById("product_attribute_5_11");

        Log.info(method.getName() + " - " + "Step 07: Check the checkbox Software: 'Total Commander [+$5.00]'");
        productPage.clickToCheckBoxById("product_attribute_5_12");

        Log.info(method.getName() + " - " + "Step 08: Click to Add to cart button");
        productPage.clickToButtonByTextName("Add to cart");

        Log.info(method.getName() + " - " + "Step 09: Verify add to cart success message displayed with text: " +
                "'The product has been added to your shopping cart'");
        verifyTrue(productPage.isTopNotificationGreenBarDisplayedWithText("The product has been added to your shopping cart"));

        Log.info(method.getName() + " - " + "Step 10: Close top notification green bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 11: Hover mouse to cart at top nav bar to show flyout cart");
        productPage.hoverToTopNavBarMenu("cart");

        Log.info(method.getName() + " - " + "Step 12: Verify number of item in cart at top nav bar is '1'");
        verifyTrue(productPage.isShoppingCartNumberEqualTo("1"));

        Log.info(method.getName() + " - " + "Step 13: Verify number of item displayed in flyout cart is as: 'There are 1 item(s) in your cart.'");
        verifyEquals(productPage.getNumberOfItemTextInFlyoutCart(), "There are 1 item(s) in your cart.");

        Log.info(method.getName() + " - " + "Step 14: Verify the processor option is as '2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]"));

        Log.info(method.getName() + " - " + "Step 15: Verify RAM options is 'RAM: 8GB [+$60.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "RAM: 8GB [+$60.00]"));

        Log.info(method.getName() + " - " + "Step 16: Verify HDD options is as : 'HDD: 400 GB [+$100.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "HDD: 400 GB [+$100.00]"));

        Log.info(method.getName() + " - " + "Step 17: Verify OS option is as: 'OS: Vista Premium [+$60.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "OS: Vista Premium [+$60.00]"));

        Log.info(method.getName() + " - " + "Step 18: Verify Software options have 'Software: Microsoft Office [+$50.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Microsoft Office [+$50.00]"));

        Log.info(method.getName() + " - " + "Step 19: Verify software options have 'Software: Acrobat Reader [+$10.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Acrobat Reader [+$10.00]"));

        Log.info(method.getName() + " - " + "Step 20: Verify Software options have 'Software: Total Commander [+$5.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Total Commander [+$5.00]"));

        Log.info(method.getName() + " - " + "Step 21: Verify unit price is: 'Unit price: $1,500.00'");
        verifyTrue(productPage.isPriceInFlyoutCartByProductName("Build your own computer", "Unit price: $1,500.00"));

        Log.info(method.getName() + " - " + "Step 22: Verify quantity is: 'Quantity: 1'");
        verifyTrue(productPage.isQuantityInFlyoutCartByProductName("Build your own computer", "Quantity: 1"));

        Log.info(method.getName() + " - " + "Step 23: Verify Sub-total is: '$1,500.00'");
        verifyTrue(productPage.isSubTotalInfoInFlyoutCartByProductName("Build your own computer", "Sub-Total: $1,500.00"));

        Log.info(method.getName() + " - " + "Step 24: Verify name of product is: 'Build your own computer'");
        verifyTrue(productPage.isProductContainsInFlyoutCartByProductName("Build your own computer"));

    }

    @Test
    public void TC_02_Edit_Product_In_Shopping_Cart(Method method) {
        startTest(method.getName(), "Edit product in cart page, verify unit price, sub-total, message and product information is correct");

        Log.info(method.getName() + " - " + "Step 01: Navigate to cart page");
        cartPage = (CartPageObject) productPage.clickToTopNavBar("cart");

        Log.info(method.getName() + " - " + "Step 02: Click to edit link at bottom of product information");
        productPage = cartPage.clickToEditLinkOfProductName("Build your own computer");

        Log.info(method.getName() + " - " + "Step 03: Select processor option : '2.2 GHz Intel Pentium Dual-Core E2200'");
        productPage.selectDropDownListById("product_attribute_1", "2.2 GHz Intel Pentium Dual-Core E2200");

        Log.info(method.getName() + " - " + "Step 04: Select RAM option: '4GB [+$20.00]'");
        productPage.selectDropDownListById("product_attribute_2", "4GB [+$20.00]");

        Log.info(method.getName() + " - " + "Step 05: Select HDD checkbox: '320 GB'");
        productPage.selectRadioButtonById("product_attribute_3_6");

        Log.info(method.getName() + " - " + "Step 06: Select OS checkbox: 'Vista Home [+$50.00]'");
        productPage.selectRadioButtonById("product_attribute_4_8");

        Log.info(method.getName() + " - " + "Step 07: Enter to quantity textbox: '2'");
        productPage.sendKeysToElementById("product_enteredQuantity_1", "2");

        Log.info(method.getName() + " - " + "Step 08: Click update button");
        productPage.clickToElementById("add-to-cart-button-1");

        Log.info(method.getName() + " - " + "Step 09: Verify add to cart success message displayed with text: " +
                "'The product has been added to your shopping cart'");
        verifyTrue(productPage.isTopNotificationGreenBarDisplayedWithText("The product has been added to your shopping cart"));

        Log.info(method.getName() + " - " + "Step 10: Close top notification green bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 11: Hover mouse to cart at top nav bar to show flyout cart");
        productPage.hoverToTopNavBarMenu("cart");

        Log.info(method.getName() + " - " + "Step 12: Verify number of item in cart at top nav bar is : '2'");
        verifyTrue(productPage.isShoppingCartNumberEqualTo("2"));

        Log.info(method.getName() + " - " + "Step 13: Verify number of item displayed in flyout cart is as: 'There are 2 item(s) in your cart.'");
        verifyEquals(productPage.getNumberOfItemTextInFlyoutCart(), "There are 2 item(s) in your cart.");

        Log.info(method.getName() + " - " + "Step 14: Verify the processor option is as '2.2 GHz Intel Pentium Dual-Core E2200'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "2.2 GHz Intel Pentium Dual-Core E2200"));

        Log.info(method.getName() + " - " + "Step 15: Verify RAM options is 'RAM: 4GB [+$20.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "RAM: 4GB [+$20.00]"));

        Log.info(method.getName() + " - " + "Step 16: Verify HDD options is as : 'HDD: 320 GB'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "HDD: 320 GB"));

        Log.info(method.getName() + " - " + "Step 17: Verify OS option is as: 'OS: Vista Home [+$50.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "OS: Vista Home [+$50.00]"));

        Log.info(method.getName() + " - " + "Step 18: Verify Software options have 'Software: Microsoft Office [+$50.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Microsoft Office [+$50.00]"));

        Log.info(method.getName() + " - " + "Step 19: Verify software options have 'Software: Acrobat Reader [+$10.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Acrobat Reader [+$10.00]"));

        Log.info(method.getName() + " - " + "Step 20: Verify Software options have 'Software: Total Commander [+$5.00]'");
        verifyTrue(productPage.isAttributeInFlyoutCartByProductName("Build your own computer", "Software: Total Commander [+$5.00]"));

        Log.info(method.getName() + " - " + "Step 21: Verify unit price is: 'Unit price: $1,335.00'");
        verifyTrue(productPage.isPriceInFlyoutCartByProductName("Build your own computer", "Unit price: $1,335.00"));

        Log.info(method.getName() + " - " + "Step 22: Verify quantity is: 'Quantity: 2'");
        verifyTrue(productPage.isQuantityInFlyoutCartByProductName("Build your own computer", "Quantity: 2"));

        Log.info(method.getName() + " - " + "Step 23: Verify Sub-total is: '$2,670.00'");
        verifyTrue(productPage.isSubTotalInfoInFlyoutCartByProductName("Build your own computer", "Sub-Total: $2,670.00"));

        Log.info(method.getName() + " - " + "Step 24: Verify name of product is: 'Build your own computer'");
        verifyTrue(productPage.isProductContainsInFlyoutCartByProductName("Build your own computer"));
    }

    @Test
    public void TC_03_Remove_From_Cart(Method method) {
        startTest(method.getName(), "Remove product in cart page then " +
                "verify message is display, product is removed completely in cart page and shopping cart");

        Log.info(method.getName() + " - " + "Step 01: Navigate to cart page");
        cartPage = (CartPageObject) productPage.clickToTopNavBar("cart");

        Log.info(method.getName() + " - " + "Step 02: Click to remove product");
        cartPage.clickToRemoveButtonByProductName("Build your own computer");

        Log.info(method.getName() + " - " + "Step 03: Verify product 'Build your own computer' is remove completely in cart page");
        verifyFalse(cartPage.isProductNameDisplayedInCartPage("Build your own computer"));

        Log.info(method.getName() + " - " + "Step 04: Verify message 'Your Shopping Cart is empty! is displayed at cart page'");
        verifyTrue(cartPage.isCartPageEmptyTextDisplayed()); //div[@class='no-data' and contains(.,'Your Shopping Cart is empty!')]

        Log.info(method.getName() + " - " + "Step 05: Move mouse to shopping cart at top nav bar");
        cartPage.hoverToTopNavBarMenu("cart");

        Log.info(method.getName() + " - " + "Step 06: Verify product 'Build your own computer' is undisplayed at shopping cart top nav bar");
        verifyFalse(cartPage.isProductContainsInFlyoutCartByProductName("Build your own computer"));

        Log.info(method.getName() + " - " + "Step 07: Verify number of product in shopping cart at top nav bar is : '0'");
        verifyTrue(cartPage.isShoppingCartNumberEqualTo("0"));

        Log.info(method.getName() + " - " + "Step 08: Verify text : 'You have no items in your shopping cart.' is displayed");
        verifyTrue(cartPage.isNoItemTextMessageInFlyoutCart());
    }

    @Test
    public void TC_04_Update_Shopping_Cart(Method method) {
        startTest(method.getName(), "Add a product to cart, update quantity then verify quantity is updated");

        Log.info(method.getName() + " - " + "Step 01: Navigate to desktop computer product page");
        productPage = cartPage.clickToProductMenuBarByTextName("Desktops");

        Log.info(method.getName() + " - " + "Step 02: Click to button to cart of 'Lenovo IdeaCentre 600 All-in-One PC' product ");
        productPage.clickToAddToCartByProductName("Lenovo IdeaCentre 600 All-in-One PC");

        Log.info(method.getName() + " - " + "Step 03: Close top notification green bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 04: Navigate to cart page by click to shopping cart at top nav bar");
        cartPage = (CartPageObject) productPage.clickToTopNavBar("cart");

        Log.info(method.getName() + " - " + "Step 05: Enter to quantity text box of 'Lenovo IdeaCentre 600 All-in-One PC' text: '5'");
        cartPage.enterToQuantityTextBoxByProductName("Lenovo IdeaCentre 600 All-in-One PC", "5");

        Log.info(method.getName() + " - " + "Step 06: Click to 'Update shopping cart' button");
        cartPage.clickToButtonByTextName("Update shopping cart");

        Log.info(method.getName() + " - " + "Step 07: Verify quantity of 'Lenovo IdeaCentre 600 All-in-One PC' is updated to '5'");
        verifyEquals(cartPage.getQuantityOfItemByProductName("Lenovo IdeaCentre 600 All-in-One PC"), "5");

        Log.info(method.getName() + " - " + "Step 08: Hover mouse to shopping cart to show flyout cart");
        cartPage.hoverToTopNavBarMenu("cart");

        Log.info(method.getName() + " - " + "Step 09: Verify quantity of 'Lenovo IdeaCentre 600 All-in-One PC' item is '5'");
        verifyTrue(cartPage.isQuantityInFlyoutCartByProductName("Lenovo IdeaCentre 600 All-in-One PC", "5"));

        Log.info(method.getName() + " - " + "Step 10: Verify number of items in shopping cart at nav bar is '5'");
        verifyTrue(cartPage.isShoppingCartNumberEqualTo("5"));

        Log.info(method.getName() + " - " + "Step 11: Verify text 'There are 5 item(s) in your cart.' is displayed in flyout cart");
        verifyEquals(cartPage.getNumberOfItemTextInFlyoutCart(), "There are 5 item(s) in your cart.");
        verifyTrue(cartPage.isNumberInNumberOfItemsInFlyoutCartEqualTo("5"));

        Log.info(method.getName() + " - " + "Step 12: Remove 'Lenovo IdeaCentre 600 All-in-One PC' product from cart");
        cartPage.clickToRemoveButtonByProductName("Lenovo IdeaCentre 600 All-in-One PC");
    }

    @Test
    public void TC_05_Checkout_Order_By_Cheque(Method method) {
        startTest(method.getName(), "Payment for a product by cheque successfully, verify order information is correct");

        Log.info(method.getName() + " - " + "Step 01: Navigate to notebooks page, by clicking at menu bar");
        productPage = cartPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to 'Apple MacBook Pro 13-inch' product");
        productPage.clickToProductByName("Apple MacBook Pro 13-inch");

        Log.info(method.getName() + " - " + "Step 03: Click add to cart button");
        productPage.clickToElementById("add-to-cart-button-4");

        Log.info(method.getName() + " - " + "Step 04: Close the top notification green bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 05: Navigate to cart page by clicking to top nav bar");
        cartPage = (CartPageObject) productPage.clickToTopNavBar("cart");

        Log.info(method.getName() + " - " + "Step 06: Select No option of Gift wrapping");
        cartPage.selectDropDownListById("checkout_attribute_1", "No");

        Log.info(method.getName() + " - " + "Step 07: Click to estimate shipping button");
        cartPage.clickToElementById("open-estimate-shipping-popup");

        Log.info(method.getName() + " - " + "Step 08: Select option 'Viet Nam' of Country dropdown list in estimate shipping popup");
        cartPage.selectDropDownListById("CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 09: Enter to Zip/ postal code text: '550000'");
        cartPage.sendKeysToElementById("ZipPostalCode", "550000");

        Log.info(method.getName() + " - " + "Step 10: Select option 'Next Day Air' radio button in Shipping method");
        cartPage.selectShippingMethodRadioButtonByName("Next Day Air");

        Log.info(method.getName() + " - " + "Step 11: Click Apply button");
        cartPage.clickToButtonByTextName("Apply");

        Log.info(method.getName() + " - " + "Step 12: Check the checkbox to agree term of service");
        cartPage.clickToCheckBoxById("termsofservice");

        Log.info(method.getName() + " - " + "Step 13: Click to Checkout button");
        checkoutPage = cartPage.clickToCheckoutButton();

        Log.info(method.getName() + " - " + "Step 14: Select to country dropdown list option: 'Viet Nam'");
        checkoutPage.selectDropDownListById("BillingNewAddress_CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 15: Select state option as: 'Other'");
        checkoutPage.selectDropDownListById("BillingNewAddress_StateProvinceId", "Other");

        Log.info(method.getName() + " - " + "Step 16: Enter to city textbox as: 'Da Nang'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_City", "Da Nang");

        Log.info(method.getName() + " - " + "Step 17: Enter to address 1 textbox as: '236 NHS'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_Address1", "236 NHS");

        Log.info(method.getName() + " - " + "Step 18: Enter zip code text box as: '550000'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_ZipPostalCode", "550000");

        Log.info(method.getName() + " - " + "Step 19: Enter phone number text box as: '0979797979'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_PhoneNumber", "0979797979");

        Log.info(method.getName() + " - " + "Step 20: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("Billing");
        DataHelper.overWriteAddressInfoToUserFile(Constants.mainResourcePath + "currentUser.json", user, "Viet Nam","", "Other", "Da Nang", "236 NHS", "", "550000", "0979797979", "");

        Log.info(method.getName() + " - " + "Step 21: Select Shipping method radio button as: 'Next Day Air ($0.00)'");
        checkoutPage.selectShippingMethodByName("Next Day Air");//label[contains(.,'Next Day Air')]//preceding-sibling::input

        Log.info(method.getName() + " - " + "Step 22: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("ShippingMethod");

        Log.info(method.getName() + " - " + "Step 23: Select payment type as: 'check/ money order'");
        checkoutPage.selectPaymentTypeByTextName("Check");

        Log.info(method.getName() + " - " + "Step 24: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentMethod");

        Log.info(method.getName() + " - " + "Step 25: Verify payment information is displayed"); //div[@class='section payment-info']//p[2]
        verifyEquals(checkoutPage.getPaymentInformationText(), "NOP SOLUTIONS\n" +
                "your address here,\n" +
                "New York, NY 10001\n" +
                "USA");

        Log.info(method.getName() + " - " + "Step 25: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentInfo");

        Log.info(method.getName() + " - " + "Step 26: Verify billing information is correct");
        Log.info(method.getName() + " - " + "Step 26 - 01: Verify billing receiver's name is correct");
        verifyEquals(checkoutPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName()); //div[@class='billing-info']//ul//li[@class='%s']

        Log.info(method.getName() + " - " + "Step 26 - 02: Verify billing receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 26 - 03: Verify billing receiver's phone number is: '0979797979'");
        verifyEquals(checkoutPage.getBillingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 26 - 04: Verify billing receiver's fax is empty");
        verifyEquals(checkoutPage.getBillingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 26 - 05: Verify billing receiver's address1 is: '236 NHS'");
        verifyEquals(checkoutPage.getBillingInfoByName("address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 26 - 06: Verify billing receiver's city, state, zip is: 'Da Nang,550000'");
        verifyEquals(checkoutPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 26 - 07: Verify billing receiver's country is: 'Viet Nam'");
        verifyEquals(checkoutPage.getBillingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 27: Verify Shopping address is correct");
        Log.info(method.getName() + " - " + "Step 27 - 01: Verify shipping receiver's name is correct");
        verifyEquals(checkoutPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());//div[@class='shipping-info']//ul//li[@class='%s']

        Log.info(method.getName() + " - " + "Step 27 - 02: Verify shipping receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 27 - 03: Verify shipping receiver's phone number is: '0979797979'");
        verifyEquals(checkoutPage.getShippingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 26 - 04: Verify shipping receiver's fax is empty");
        verifyEquals(checkoutPage.getShippingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 26 - 05: Verify shipping receiver's address1 is: '236 NHS'");
        verifyEquals(checkoutPage.getShippingInfoByName("address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 26 - 06: Verify shipping receiver's city, state, zip is: 'Da Nang,550000'");
        verifyEquals(checkoutPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 26 - 07: Verify shipping receiver's country is: 'Viet Nam'");
        verifyEquals(checkoutPage.getShippingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 28: Verify Payment method is correct"); //div[@class='payment-method-info']//ul/li/span[@class='value']
        verifyEquals(checkoutPage.getPaymentMethodText(), "Check / Money Order");

        Log.info(method.getName() + " - " + "Step 29: Verify Shipping method is correct"); //div[@class='shipping-method-info']//ul/li/span[@class='value']
        verifyEquals(checkoutPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 30: Verify product SKU is: 'AP_MBP_13'");
        verifyEquals(checkoutPage.getProductSKUByProductName("Apple MacBook Pro 13-inch"), "AP_MBP_13");
        //table[@class='cart']//td[@class='product']//a[text()='Apple MacBook Pro 13-inch']//parent::td//preceding-sibling::td[@class='sku']//span

        Log.info(method.getName() + " - " + "Step 31: Verify product name is as: 'Apple MacBook Pro 13-inch'");
        verifyTrue(checkoutPage.isProductInCheckoutItemTable("Apple MacBook Pro 13-inch"));
        //table[@class='cart']//td[@class='product']//a

        Log.info(method.getName() + " - " + "Step 32: Verify unit price is as: '$1,800.00'");
        verifyEquals(checkoutPage.getProductPriceByProductName("Apple MacBook Pro 13-inch"), "$1,800.00");

        Log.info(method.getName() + " - " + "Step 33: Verify quantity is: '2'");
        verifyEquals(checkoutPage.getProductQuantityByProductName("Apple MacBook Pro 13-inch"), "2");

        Log.info(method.getName() + " - " + "Step 34: Verify Total cost is: '$3,600.00'");
        verifyEquals(checkoutPage.getProductTotalCostByProductName("Apple MacBook Pro 13-inch"), "$3,600.00");

        Log.info(method.getName() + " - " + "Step 35: Verify gift wrapping option is: 'No'");
        verifyEquals(checkoutPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 36: Verify Sub-total: '$3,600.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Sub-Total"), "$3,600.00");

        Log.info(method.getName() + " - " + "Step 37: Verify Shipping cost is: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 38: Verify Tax cost: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 39: Verify Total Cost: '$3,600.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Total"), "$3,600.00");

        Log.info(method.getName() + " - " + "Step 40: Click to Confirm button");
        checkoutPage.clickToButtonByTextName("Confirm");

        Log.info(method.getName() + " - " + "Step 41: Verify checkout complete, Thank you page is displayed"); //div[@class='page-title']//h1
        verifyEquals(checkoutPage.getPageTitleText(), "Thank you");

        Log.info(method.getName() + " - " + "Step 42: Verify order successfully text is: 'Your order has been successfully processed!'");
        verifyEquals(checkoutPage.getOrderSuccessMessageText(), "Your order has been successfully processed!"); //div[@class='title']//strong

        Log.info(method.getName() + " - " + "Step 43: Verify order number is displayed, then overwrite it to file 'currentUser.json'"); //div[@class='order-number']//strong
        verifyTrue(checkoutPage.isOrderNumberDisplayed());
        DataHelper.overWriteOrderInfoToUserFile(Constants.mainResourcePath + "currentUser.json", user, checkoutPage.getOrderNumber());

        Log.info(method.getName() + " - " + "Step 44: Click to continue button");
        checkoutPage.clickToButtonByTextName("Continue");
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info(method.getName() + " - " + "Step 45: Click to My account menu to navigate to my account page");
        myAccountPage = (MyAccountPageObject) homePage.clickToTopNavBar("account");

        Log.info(method.getName() + " - " + "Step 46: Click to menu Orders at left menu bar to navigate to see order page");
        myAccountPage.clickLeftMenuBarByNameText("Orders");

        Log.info(method.getName() + " - " + "Step 47: Verify Order number is: '" + user.getOrder().get(0).getOrderNumber() + "'");//div[@class='page-body']//div[@class='title']//strong
        verifyEquals(myAccountPage.getOrderNumberAtMyAccountPage(), user.getOrder().get(0).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 48: Click to details button"); //button[text()='Details']
        orderPage = myAccountPage.clickToDetailButtonByOrderNumber(user.getOrder().get(0).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 49: Verify order number is: '" + user.getOrder().get(0).getOrderNumber() + "'"); //div[@class='order-number']//strong
        verifyEquals(orderPage.getOrderNumberAtOrderPage(), user.getOrder().get(0).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 50: Verify order date is correct"); //ul[@class='order-overview-content']//li[@class='order-date']
        verifyEquals(orderPage.getOrderOverviewInfoByName("date"), user.getOrder().get(0).getOrderDate());

        Log.info(method.getName() + " - " + "Step 51: Verify order status is: 'pending'"); //ul[@class='order-overview-content']//li[@class='order-status']
        verifyEquals(orderPage.getOrderOverviewInfoByName("status"), "Pending");

        Log.info(method.getName() + " - " + "Step 52: Verify Total cost at top information table is: '$3,600.00'");
        verifyEquals(orderPage.getOrderOverviewInfoByName("total"), "$3,600.00");//ul[@class='order-overview-content']//li[@class='order-total']

        Log.info(method.getName() + " - " + "Step 53: Verify billing address is correct"); //div[@class='billing-info']//ul//li[@class='%s']
        verifyEquals(orderPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName()); //div[@class='billing-info']//ul//li[@class='%s']
        verifyEquals(orderPage.getBillingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getBillingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());
        verifyEquals(orderPage.getBillingInfoByName("address1"), user.getAddress().get(0).getAddress1());
        verifyEquals(orderPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());
        verifyEquals(orderPage.getBillingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 54: Verify Shipping address is correct"); //div[@class='shipping-info']//ul//li[@class='%s']
        verifyEquals(orderPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());//div[@class='shipping-info']//ul//li[@class='%s']
        verifyEquals(orderPage.getShippingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getShippingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());
        verifyEquals(orderPage.getShippingInfoByName("address1"), user.getAddress().get(0).getAddress1());
        verifyEquals(orderPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());
        verifyEquals(orderPage.getShippingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 55: Verify Payment method is correct");
        verifyEquals(orderPage.getPaymentMethodText(), "Check / Money Order");

        Log.info(method.getName() + " - " + "Step 56: Verify Shipping method is correct");
        verifyEquals(orderPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 57: Verify item SKU is correct");
        verifyEquals(orderPage.getProductSKUByProductName("Apple MacBook Pro 13-inch"), "AP_MBP_13");

        Log.info(method.getName() + " - " + "Step 58: Verify item name is correct");
        verifyTrue(orderPage.isProductInCheckoutItemTable("Apple MacBook Pro 13-inch"));

        Log.info(method.getName() + " - " + "Step 59: Verify item unit price is correct");
        verifyEquals(orderPage.getProductPriceByProductName("Apple MacBook Pro 13-inch"), "$1,800.00");

        Log.info(method.getName() + " - " + "Step 60: Verify item quantity is correct");
        verifyEquals(orderPage.getProductQuantityByProductName("Apple MacBook Pro 13-inch"), "2");

        Log.info(method.getName() + " - " + "Step 61: Verify item total is correct");
        verifyEquals(orderPage.getProductTotalCostByProductName("Apple MacBook Pro 13-inch"), "$3,600.00");

        Log.info(method.getName() + " - " + "Step 62: Verify gift wrapping type is correct");
        verifyEquals(orderPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 63: Verify sub total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Sub-Total"), "$3,600.00");

        Log.info(method.getName() + " - " + "Step 64: Verify shipping cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 65: Verify Tax cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 66: Verify Order total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Order Total"), "$3,600.00");
    }

    @Test
    public void TC_06_Checkout_Order_By_Card(Method method) {
        startTest(method.getName(), "Payment for a product by cheque successfully, verify order information is correct");

        Log.info(method.getName() + " - " + "Step 01: Navigate to notebooks page, by clicking at menu bar");
        productPage = cartPage.clickToProductMenuBarByTextName("Notebooks");

        Log.info(method.getName() + " - " + "Step 02: Click to 'Lenovo Thinkpad X1 Carbon Laptop' product");
        productPage.clickToProductByName("Lenovo Thinkpad X1 Carbon Laptop");

        Log.info(method.getName() + " - " + "Step 03: Click add to cart button");
        productPage.clickToElementById("add-to-cart-button-9");

        Log.info(method.getName() + " - " + "Step 04: Close the top notification green bar");
        productPage.clickToCloseButtonAtTopNotificationGreenBar();

        Log.info(method.getName() + " - " + "Step 05: Navigate to cart page by clicking to top nav bar");
        cartPage = (CartPageObject) productPage.clickToTopNavBar("cart");

        Log.info(method.getName() + " - " + "Step 06: Select No option of Gift wrapping");
        cartPage.selectDropDownListById("checkout_attribute_1", "No");

        Log.info(method.getName() + " - " + "Step 07: Click to estimate shipping button");
        cartPage.clickToElementById("open-estimate-shipping-popup");

        Log.info(method.getName() + " - " + "Step 08: Select option 'Viet Nam' of Country dropdown list in estimate shipping popup");
        cartPage.selectDropDownListById("CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 09: Enter to Zip/ postal code text: '550000'");
        cartPage.sendKeysToElementById("ZipPostalCode", "550000");

        Log.info(method.getName() + " - " + "Step 10: Select option 'Next Day Air' radio button in Shipping method");
        cartPage.selectShippingMethodRadioButtonByName("Next Day Air");

        Log.info(method.getName() + " - " + "Step 11: Click Apply button");
        cartPage.clickToButtonByTextName("Apply");

        Log.info(method.getName() + " - " + "Step 12: Check the checkbox to agree term of service");
        cartPage.clickToCheckBoxById("termsofservice");

        Log.info(method.getName() + " - " + "Step 13: Click to Checkout button");
        checkoutPage = cartPage.clickToCheckoutButton();

        Log.info(method.getName() + " - " + "Step 14: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("Billing");

        Log.info(method.getName() + " - " + "Step 15: Select Shipping method radio button as: 'Next Day Air ($0.00)'");
        checkoutPage.selectShippingMethodByName("Next Day Air");

        Log.info(method.getName() + " - " + "Step 16:: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("ShippingMethod");

        Log.info(method.getName() + " - " + "Step 17: Select payment type as: 'Credit Card'");
        checkoutPage.selectPaymentTypeByTextName("Credit Card");

        Log.info(method.getName() + " - " + "Step 18: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentMethod");

        Log.info(method.getName() + " - " + "Step 19: Select credit card type: 'Master card'");
        checkoutPage.selectDropDownListById("CreditCardType", "Master card");

        Log.info(method.getName() + " - " + "Step 21: Enter to credit card holder name textbox: '"+ user.getFirstName() + " " + user.getLastName() +"'");
        checkoutPage.sendKeysToElementById("CardholderName", user.getFirstName() + " " + user.getLastName());

        Log.info(method.getName() + " - " + "Step 22: Enter to credit card number: '5555555555554444'");
        checkoutPage.sendKeysToElementById("CardNumber","5555555555554444");

        Log.info(method.getName() + " - " + "Step 23: Select expired month of card: '12'");
        checkoutPage.selectDropDownListById("ExpireMonth","12");

        Log.info(method.getName() + " - " + "Step 24: Select expired Year of credit card: '2024'");
        checkoutPage.selectDropDownListById("ExpireYear","2024");

        Log.info(method.getName() + " - " + "Step 25: Enter to CCV number: '6789'");
        checkoutPage.sendKeysToElementById("CardCode","6789");

        Log.info(method.getName() + " - " + "Step 26:: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentInfo");

        Log.info(method.getName() + " - " + "Step 26: Verify billing information is correct");
        Log.info(method.getName() + " - " + "Step 27 - 01: Verify billing receiver's name is correct");
        verifyEquals(checkoutPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName()); //div[@class='billing-info']//ul//li[@class='%s']

        Log.info(method.getName() + " - " + "Step 27 - 02: Verify billing receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 27 - 03: Verify billing receiver's phone number is: '" + user.getAddress().get(0).getPhoneNo() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 27 - 04: Verify billing receiver's fax is empty");
        verifyEquals(checkoutPage.getBillingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 27 - 05: Verify billing receiver's address1 is: '" + user.getAddress().get(0).getAddress1() +"'");
        verifyEquals(checkoutPage.getBillingInfoByName("address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 27 - 06: Verify billing receiver's city, state, zip is: '" + user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 27 - 07: Verify billing receiver's country is: '" + user.getAddress().get(0).getCountry() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 28: Verify Shopping address is correct");
        Log.info(method.getName() + " - " + "Step 28 - 01: Verify shipping receiver's name is correct");
        verifyEquals(checkoutPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());

        Log.info(method.getName() + " - " + "Step 28 - 02: Verify shipping receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 28 - 03: Verify shipping receiver's phone number is: '" + user.getAddress().get(0).getPhoneNo() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 28 - 04: Verify shipping receiver's fax is empty");
        verifyEquals(checkoutPage.getShippingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 28 - 05: Verify shipping receiver's address1 is: '" + user.getAddress().get(0).getAddress1() +"'");
        verifyEquals(checkoutPage.getShippingInfoByName("address1"), user.getAddress().get(0).getAddress1());

        Log.info(method.getName() + " - " + "Step 28 - 06: Verify shipping receiver's city, state, zip is: '" + user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());

        Log.info(method.getName() + " - " + "Step 28 - 07: Verify shipping receiver's country is: '" + user.getAddress().get(0).getCountry() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 29: Verify Payment method is correct");
        verifyEquals(checkoutPage.getPaymentMethodText(), "Credit Card");

        Log.info(method.getName() + " - " + "Step 30: Verify Shipping method is correct");
        verifyEquals(checkoutPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 31: Verify product SKU is: 'LE_TX1_CL'");
        verifyEquals(checkoutPage.getProductSKUByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "LE_TX1_CL");

        Log.info(method.getName() + " - " + "Step 32: Verify product name is as: 'Lenovo Thinkpad X1 Carbon Laptop'");
        verifyTrue(checkoutPage.isProductInCheckoutItemTable("Lenovo Thinkpad X1 Carbon Laptop"));

        Log.info(method.getName() + " - " + "Step 33: Verify unit price is as: '$1,360.00'");
        verifyEquals(checkoutPage.getProductPriceByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 34: Verify quantity is: '2'");
        verifyEquals(checkoutPage.getProductQuantityByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "1");

        Log.info(method.getName() + " - " + "Step 35: Verify Total cost is: '$1,360.00'");
        verifyEquals(checkoutPage.getProductTotalCostByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 36: Verify gift wrapping option is: 'No'");
        verifyEquals(checkoutPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 37: Verify Sub-total: '$1,360.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Sub-Total"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 38: Verify Shipping cost is: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 39: Verify Tax cost: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 40: Verify Total Cost: '$1,360.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Total"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 41: Click to Confirm button");
        checkoutPage.clickToConfirmBtn();

        Log.info(method.getName() + " - " + "Step 42: Verify checkout complete, Thank you page is displayed");
        verifyEquals(checkoutPage.getPageTitleText(), "Thank you");

        Log.info(method.getName() + " - " + "Step 43: Verify order successfully text is: 'Your order has been successfully processed!'");
        verifyEquals(checkoutPage.getOrderSuccessMessageText(), "Your order has been successfully processed!");

        Log.info(method.getName() + " - " + "Step 44: Verify order number is displayed, then overwrite it to file 'currentUser.json'");
        verifyTrue(checkoutPage.isOrderNumberDisplayed());
        DataHelper.overWriteOrderInfoToUserFile(Constants.mainResourcePath + "currentUser.json", user, checkoutPage.getOrderNumber());

        Log.info(method.getName() + " - " + "Step 45: Click to continue button");
        checkoutPage.clickToButtonByTextName("Continue");
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info(method.getName() + " - " + "Step 46: Click to My account menu to navigate to my account page");
        myAccountPage = (MyAccountPageObject) homePage.clickToTopNavBar("account");

        Log.info(method.getName() + " - " + "Step 47: Click to menu Orders at left menu bar to navigate to see order page");
        myAccountPage.clickLeftMenuBarByNameText("Orders");

        Log.info(method.getName() + " - " + "Step 48: Verify Order number is: '" + user.getOrder().get(1).getOrderNumber() + "'");
        verifyEquals(myAccountPage.getOrderNumberAtMyAccountPage(), user.getOrder().get(1).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 49: Click to details button");
        orderPage = myAccountPage.clickToDetailButtonByOrderNumber(user.getOrder().get(1).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 50: Verify order number is: '" + user.getOrder().get(1).getOrderNumber() + "'");
        verifyEquals(orderPage.getOrderNumberAtOrderPage(), user.getOrder().get(1).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 51: Verify order date is correct");
        verifyEquals(orderPage.getOrderOverviewInfoByName("date"), user.getOrder().get(1).getOrderDate());

        Log.info(method.getName() + " - " + "Step 52: Verify order status is: 'pending'");
        verifyEquals(orderPage.getOrderOverviewInfoByName("status"), "Pending");

        Log.info(method.getName() + " - " + "Step 53: Verify Total cost at top information table is: '$1,360.00'");
        verifyEquals(orderPage.getOrderOverviewInfoByName("total"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 54: Verify billing address is correct");
        verifyEquals(orderPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName());
        verifyEquals(orderPage.getBillingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getBillingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());
        verifyEquals(orderPage.getBillingInfoByName("address1"), user.getAddress().get(0).getAddress1());
        verifyEquals(orderPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());
        verifyEquals(orderPage.getBillingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 55: Verify Shipping address is correct");
        verifyEquals(orderPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());
        verifyEquals(orderPage.getShippingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getShippingInfoByName("phone"), user.getAddress().get(0).getPhoneNo());
        verifyEquals(orderPage.getShippingInfoByName("address1"), user.getAddress().get(0).getAddress1());
        verifyEquals(orderPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(0).getCity() + "," + user.getAddress().get(0).getZip());
        verifyEquals(orderPage.getShippingInfoByName("country"), user.getAddress().get(0).getCountry());

        Log.info(method.getName() + " - " + "Step 56: Verify Payment method is correct");
        verifyEquals(orderPage.getPaymentMethodText(), "Credit Card");

        Log.info(method.getName() + " - " + "Step 57: Verify Shipping method is correct");
        verifyEquals(orderPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 58: Verify item SKU is correct");
        verifyEquals(orderPage.getProductSKUByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "LE_TX1_CL");

        Log.info(method.getName() + " - " + "Step 59: Verify item name is correct");
        verifyTrue(orderPage.isProductInCheckoutItemTable("Lenovo Thinkpad X1 Carbon Laptop"));

        Log.info(method.getName() + " - " + "Step 60: Verify item unit price is correct");
        verifyEquals(orderPage.getProductPriceByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 61: Verify item quantity is correct");
        verifyEquals(orderPage.getProductQuantityByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "1");

        Log.info(method.getName() + " - " + "Step 62: Verify item total is correct");
        verifyEquals(orderPage.getProductTotalCostByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 63: Verify gift wrapping type is correct");
        verifyEquals(orderPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 64: Verify sub total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Sub-Total"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 65: Verify shipping cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 66: Verify Tax cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 67: Verify Order total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Order Total"), "$1,360.00");
    }

    @Test
    public void TC_07_ReOrder(Method method) {
        startTest(method.getName(), "Re-order function test, go to ordered detail then click Re-order button, " +
                "update quantity, address then verify at new order detail page and ");


        Log.info(method.getName() + " - " + "Step 01: Click to My account menu to navigate to my account page");
        myAccountPage = (MyAccountPageObject) homePage.clickToTopNavBar("account");

        Log.info(method.getName() + " - " + "Step 02: Click to menu Orders at left menu bar to navigate to see order page");
        myAccountPage.clickLeftMenuBarByNameText("Orders");

        Log.info(method.getName() + " - " + "Step 03: Click to details button of Order: [#" + user.getOrder().get(1).getOrderNumber() +"]" ); //button[text()='Details']
        orderPage = myAccountPage.clickToDetailButtonByOrderNumber(user.getOrder().get(1).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 04: Click to Re-order button");
        orderPage.clickToButtonByTextName("Re-order");
        cartPage = PageGeneratorManager.getCartPage(driver);

        Log.info(method.getName() + " - " + "Step 05: Enter '10 ' to quantity text box then click to update Shopping cart button");
        cartPage.enterToQuantityTextBoxByProductName("Lenovo Thinkpad X1 Carbon Laptop", "10");
        cartPage.clickToButtonByTextName("Update shopping cart");

        Log.info(method.getName() + " - " + "Step 06: Select No option of Gift wrapping");
        cartPage.selectDropDownListById("checkout_attribute_1", "No");

        Log.info(method.getName() + " - " + "Step 07: Click to estimate shipping button");
        cartPage.clickToElementById("open-estimate-shipping-popup");

        Log.info(method.getName() + " - " + "Step 08: Select option 'Viet Nam' of Country dropdown list in estimate shipping popup");
        cartPage.selectDropDownListById("CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 09: Enter to Zip/ postal code text: '560000'");
        cartPage.sendKeysToElementById("ZipPostalCode", "560000");

        Log.info(method.getName() + " - " + "Step 10: Select option 'Next Day Air' radio button in Shipping method");
        cartPage.selectShippingMethodRadioButtonByName("Next Day Air");

        Log.info(method.getName() + " - " + "Step 11: Click Apply button");
        cartPage.clickToButtonByTextName("Apply");

        Log.info(method.getName() + " - " + "Step 12: Check the checkbox to agree term of service");
        cartPage.clickToCheckBoxById("termsofservice");

        Log.info(method.getName() + " - " + "Step 13: Click to Checkout button");
        checkoutPage = cartPage.clickToCheckoutButton();

        Log.info(method.getName() + " - " + "Step 14: Select new address option in billing address dropdown list");
        checkoutPage.selectDropDownListById("billing-address-select", "New Address");

        Log.info(method.getName() + " - " + "Step 15: Enter to First name text box: '" + user.getFirstName() + "'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_FirstName", user.getFirstName());

        Log.info(method.getName() + " - " + "Step 16: Enter to Last name text box: '" + user.getLastName() + "'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_LastName", user.getLastName());

        Log.info(method.getName() + " - " + "Step 17: Enter to Email text box: '" + user.getEmail() + "'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_Email", user.getEmail());

        Log.info(method.getName() + " - " + "Step 18: Select to country dropdown list option: 'Viet Nam'");
        checkoutPage.selectDropDownListById("BillingNewAddress_CountryId", "Viet Nam");

        Log.info(method.getName() + " - " + "Step 19: Select state option as: 'Other'");
        checkoutPage.selectDropDownListById("BillingNewAddress_StateProvinceId", "Other");

        Log.info(method.getName() + " - " + "Step 20: Enter to city textbox as: 'Quang Nam'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_City", "Quang Nam");

        Log.info(method.getName() + " - " + "Step 21: Enter to address 1 textbox as: '999 Nguyen Van Linh'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_Address1", "999 Nguyen Van Linh");

        Log.info(method.getName() + " - " + "Step 22: Enter zip code text box as: '560000'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_ZipPostalCode", "560000");

        Log.info(method.getName() + " - " + "Step 23: Enter phone number text box as: '0939393939'");
        checkoutPage.sendKeysToElementById("BillingNewAddress_PhoneNumber", "0939393939");

        Log.info(method.getName() + " - " + "Step 24: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("Billing");
        DataHelper.overWriteAddressInfoToUserFile(Constants.mainResourcePath + "currentUser.json", user, "Viet Nam", "", "Other", "Quang Nam", "999 Nguyen Van Linh", "","560000", "0939393939", "");

        Log.info(method.getName() + " - " + "Step 25: Select Shipping method radio button as: 'Next Day Air ($0.00)'");
        checkoutPage.selectShippingMethodByName("Next Day Air");

        Log.info(method.getName() + " - " + "Step 26: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("ShippingMethod");

        Log.info(method.getName() + " - " + "Step 27: Select payment type as: 'Credit Card'");
        checkoutPage.selectPaymentTypeByTextName("Credit Card");

        Log.info(method.getName() + " - " + "Step 28: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentMethod");

        Log.info(method.getName() + " - " + "Step 29: Select credit card type: 'Master card'");
        checkoutPage.selectDropDownListById("CreditCardType", "Master card");

        Log.info(method.getName() + " - " + "Step 30: Enter to credit card holder name textbox: '"+ user.getFirstName() + " " + user.getLastName() +"'");
        checkoutPage.sendKeysToElementById("CardholderName", user.getFirstName() + " " + user.getLastName());

        Log.info(method.getName() + " - " + "Step 31: Enter to credit card number: '5555555555554444'");
        checkoutPage.sendKeysToElementById("CardNumber","5555555555554444");

        Log.info(method.getName() + " - " + "Step 32: Select expired month of card: '12'");
        checkoutPage.selectDropDownListById("ExpireMonth","12");

        Log.info(method.getName() + " - " + "Step 33: Select expired Year of credit card: '2024'");
        checkoutPage.selectDropDownListById("ExpireYear","2024");

        Log.info(method.getName() + " - " + "Step 34: Enter to CCV number: '6789'");
        checkoutPage.sendKeysToElementById("CardCode","6789");

        Log.info(method.getName() + " - " + "Step 35: Click to Continue button");
        checkoutPage.clickToContinueButtonAtStep("PaymentInfo");

        Log.info(method.getName() + " - " + "Step 36: Verify billing information is correct");
        Log.info(method.getName() + " - " + "Step 36 - 01: Verify billing receiver's name is correct");
        verifyEquals(checkoutPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName()); //div[@class='billing-info']//ul//li[@class='%s']

        Log.info(method.getName() + " - " + "Step 36 - 02: Verify billing receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 36 - 03: Verify billing receiver's phone number is: '" + user.getAddress().get(1).getPhoneNo() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("phone"), user.getAddress().get(1).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 36 - 04: Verify billing receiver's fax is empty");
        verifyEquals(checkoutPage.getBillingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 36 - 05: Verify billing receiver's address1 is: '" + user.getAddress().get(1).getAddress1() +"'");
        verifyEquals(checkoutPage.getBillingInfoByName("address1"), user.getAddress().get(1).getAddress1());

        Log.info(method.getName() + " - " + "Step 36 - 06: Verify billing receiver's city, state, zip is: '" + user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip());

        Log.info(method.getName() + " - " + "Step 36 - 07: Verify billing receiver's country is: '" + user.getAddress().get(1).getCountry() + "'");
        verifyEquals(checkoutPage.getBillingInfoByName("country"), user.getAddress().get(1).getCountry());

        Log.info(method.getName() + " - " + "Step 37: Verify Shopping address is correct");
        Log.info(method.getName() + " - " + "Step 37 - 01: Verify shipping receiver's name is correct");
        verifyEquals(checkoutPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());

        Log.info(method.getName() + " - " + "Step 37 - 02: Verify shipping receiver's email is: '" + user.getEmail() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("email"), user.getEmail());

        Log.info(method.getName() + " - " + "Step 37 - 03: Verify shipping receiver's phone number is: '" + user.getAddress().get(1).getPhoneNo() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("phone"), user.getAddress().get(1).getPhoneNo());

        Log.info(method.getName() + " - " + "Step 37 - 04: Verify shipping receiver's fax is empty");
        verifyEquals(checkoutPage.getShippingInfoByName("fax"), "");

        Log.info(method.getName() + " - " + "Step 37 - 05: Verify shipping receiver's address1 is: '" + user.getAddress().get(1).getAddress1() +"'");
        verifyEquals(checkoutPage.getShippingInfoByName("address1"), user.getAddress().get(1).getAddress1());

        Log.info(method.getName() + " - " + "Step 37 - 06: Verify shipping receiver's city, state, zip is: '" + user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip());

        Log.info(method.getName() + " - " + "Step 37 - 07: Verify shipping receiver's country is: '" + user.getAddress().get(1).getCountry() + "'");
        verifyEquals(checkoutPage.getShippingInfoByName("country"), user.getAddress().get(1).getCountry());

        Log.info(method.getName() + " - " + "Step 38: Verify Payment method is correct");
        verifyEquals(checkoutPage.getPaymentMethodText(), "Credit Card");

        Log.info(method.getName() + " - " + "Step 39: Verify Shipping method is correct");
        verifyEquals(checkoutPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 40: Verify product SKU is: 'LE_TX1_CL'");
        verifyEquals(checkoutPage.getProductSKUByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "LE_TX1_CL");

        Log.info(method.getName() + " - " + "Step 41: Verify product name is as: 'Lenovo Thinkpad X1 Carbon Laptop'");
        verifyTrue(checkoutPage.isProductInCheckoutItemTable("Lenovo Thinkpad X1 Carbon Laptop"));

        Log.info(method.getName() + " - " + "Step 42: Verify unit price is as: '$1,360.00'");
        verifyEquals(checkoutPage.getProductPriceByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 43: Verify quantity is: '10'");
        verifyEquals(checkoutPage.getProductQuantityByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "10");

        Log.info(method.getName() + " - " + "Step 44: Verify Total cost is: '$13,600.00'");
        verifyEquals(checkoutPage.getProductTotalCostByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 45: Verify gift wrapping option is: 'No'");
        verifyEquals(checkoutPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 46: Verify Sub-total: '$13,600.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Sub-Total"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 47: Verify Shipping cost is: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 48: Verify Tax cost: '$0.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 49: Verify Total Cost: '$13,600.00'");
        verifyEquals(checkoutPage.getTotalTableInfoByName("Total"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 50: Click to Confirm button");
        checkoutPage.clickToConfirmBtn();

        Log.info(method.getName() + " - " + "Step 51: Verify checkout complete, Thank you page is displayed");
        verifyEquals(checkoutPage.getPageTitleText(), "Thank you");

        Log.info(method.getName() + " - " + "Step 52: Verify order successfully text is: 'Your order has been successfully processed!'");
        verifyEquals(checkoutPage.getOrderSuccessMessageText(), "Your order has been successfully processed!");

        Log.info(method.getName() + " - " + "Step 53: Verify order number is displayed, then overwrite it to file 'currentUser.json'");
        verifyTrue(checkoutPage.isOrderNumberDisplayed());
        DataHelper.overWriteOrderInfoToUserFile(Constants.mainResourcePath + "currentUser.json", user, checkoutPage.getOrderNumber());

        Log.info(method.getName() + " - " + "Step 54: Click to continue button");
        checkoutPage.clickToButtonByTextName("Continue");
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info(method.getName() + " - " + "Step 55: Click to My account menu to navigate to my account page");
        myAccountPage = (MyAccountPageObject) homePage.clickToTopNavBar("account");

        Log.info(method.getName() + " - " + "Step 56: Click to menu Orders at left menu bar to navigate to see order page");
        myAccountPage.clickLeftMenuBarByNameText("Orders");

        Log.info(method.getName() + " - " + "Step 57: Verify Order number is: '" + user.getOrder().get(2).getOrderNumber() + "'");
        verifyEquals(myAccountPage.getOrderNumberAtMyAccountPage(), user.getOrder().get(2).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 58: Click to details button");
        orderPage = myAccountPage.clickToDetailButtonByOrderNumber(user.getOrder().get(2).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 59: Verify order number is: '" + user.getOrder().get(2).getOrderNumber() + "'");
        verifyEquals(orderPage.getOrderNumberAtOrderPage(), user.getOrder().get(2).getOrderNumber());

        Log.info(method.getName() + " - " + "Step 60: Verify order date is correct");
        verifyEquals(orderPage.getOrderOverviewInfoByName("date"), user.getOrder().get(2).getOrderDate());

        Log.info(method.getName() + " - " + "Step 52: Verify order status is: 'pending'");
        verifyEquals(orderPage.getOrderOverviewInfoByName("status"), "Pending");

        Log.info(method.getName() + " - " + "Step 53: Verify Total cost at top information table is: '$13,600.00'");
        verifyEquals(orderPage.getOrderOverviewInfoByName("total"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 54: Verify billing address is correct");
        verifyEquals(orderPage.getBillingInfoByName("name"), user.getFirstName() + " " + user.getLastName());
        verifyEquals(orderPage.getBillingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getBillingInfoByName("phone"), user.getAddress().get(1).getPhoneNo());
        verifyEquals(orderPage.getBillingInfoByName("address1"), user.getAddress().get(1).getAddress1());
        verifyEquals(orderPage.getBillingInfoByName("city-state-zip"), user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip());
        verifyEquals(orderPage.getBillingInfoByName("country"), user.getAddress().get(1).getCountry());

        Log.info(method.getName() + " - " + "Step 55: Verify Shipping address is correct");
        verifyEquals(orderPage.getShippingInfoByName("name"), user.getFirstName() + " " + user.getLastName());
        verifyEquals(orderPage.getShippingInfoByName("email"), user.getEmail());
        verifyEquals(orderPage.getShippingInfoByName("phone"), user.getAddress().get(1).getPhoneNo());
        verifyEquals(orderPage.getShippingInfoByName("address1"), user.getAddress().get(1).getAddress1());
        verifyEquals(orderPage.getShippingInfoByName("city-state-zip"), user.getAddress().get(1).getCity() + "," + user.getAddress().get(1).getZip());
        verifyEquals(orderPage.getShippingInfoByName("country"), user.getAddress().get(1).getCountry());

        Log.info(method.getName() + " - " + "Step 56: Verify Payment method is correct");
        verifyEquals(orderPage.getPaymentMethodText(), "Credit Card");

        Log.info(method.getName() + " - " + "Step 57: Verify Shipping method is correct");
        verifyEquals(orderPage.getShippingMethodInfoByName(), "Next Day Air");

        Log.info(method.getName() + " - " + "Step 58: Verify item SKU is correct");
        verifyEquals(orderPage.getProductSKUByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "LE_TX1_CL");

        Log.info(method.getName() + " - " + "Step 59: Verify item name is correct");
        verifyTrue(orderPage.isProductInCheckoutItemTable("Lenovo Thinkpad X1 Carbon Laptop"));

        Log.info(method.getName() + " - " + "Step 60: Verify item unit price is correct");
        verifyEquals(orderPage.getProductPriceByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$1,360.00");

        Log.info(method.getName() + " - " + "Step 61: Verify item quantity is correct");
        verifyEquals(orderPage.getProductQuantityByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "10");

        Log.info(method.getName() + " - " + "Step 62: Verify item total is correct");
        verifyEquals(orderPage.getProductTotalCostByProductName("Lenovo Thinkpad X1 Carbon Laptop"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 63: Verify gift wrapping type is correct");
        verifyEquals(orderPage.getGiftWrappingOption(), "No");

        Log.info(method.getName() + " - " + "Step 64: Verify sub total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Sub-Total"), "$13,600.00");

        Log.info(method.getName() + " - " + "Step 65: Verify shipping cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Shipping"), "$0.00");

        Log.info(method.getName() + " - " + "Step 66: Verify Tax cost is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Tax"), "$0.00");

        Log.info(method.getName() + " - " + "Step 67: Verify Order total is correct");
        verifyEquals(orderPage.getTotalTableInfoByName("Order Total"), "$13,600.00");
    }
    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
