package actions.pageObjects.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private PageGeneratorManager() {
    }
    private static HomePageObject homePage;
    private static RegisterPageObject registerPage;
    private static LoginPageObject loginPage;
    private static MyAccountPageObject myAccountPage;
    private static ProductPageObject productPage;
    private static SearchPageObject searchPage;
    private static WishlistPageObject wishlistPage;
    private static CartPageObject cartPage;
    private static CompareProductPageObject compareProductPage;
    private static RecentViewPageObject recentViewPage;
    private static CheckoutPageObject checkoutPage;
    private static OrderPageObject orderPage;


    public static HomePageObject getHomePage(WebDriver driver) {
        if (homePage == null) {
            return new HomePageObject(driver);
        }
        return homePage;
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        if (registerPage == null) {
            return new RegisterPageObject(driver);
        }
        return registerPage;
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        if (loginPage == null) {
            return new LoginPageObject(driver);
        }
        return loginPage;
    }

    public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
        if(myAccountPage == null){
            return new MyAccountPageObject(driver);
        }
        return myAccountPage;
    }

    public static ProductPageObject getProductPage(WebDriver driver) {
        if(productPage == null){
            return new ProductPageObject(driver);
        }
        return productPage;
    }

    public static SearchPageObject getSearchPage(WebDriver driver) {
        if(searchPage == null){
            return new SearchPageObject(driver);
        }
        return searchPage;
    }

    public static WishlistPageObject getWishlistPage(WebDriver driver) {
        if(wishlistPage == null){
            return new WishlistPageObject(driver);
        }
        return wishlistPage;
    }

    public static CartPageObject getCartPage(WebDriver driver) {
        if(cartPage == null){
            return new CartPageObject(driver);
        }
        return cartPage;
    }

    public static CompareProductPageObject getCompareProductPage(WebDriver driver) {
        if(compareProductPage == null){
            return new CompareProductPageObject(driver);
        }
        return compareProductPage;
    }

    public static RecentViewPageObject getRecentViewPage(WebDriver driver) {
        if(recentViewPage == null){
            return new RecentViewPageObject(driver);
        }
        return recentViewPage;
    }

    public static CheckoutPageObject getCheckoutPage(WebDriver driver) {
        if(checkoutPage == null){
            return new CheckoutPageObject(driver);
        }
        return checkoutPage;
    }
    public static OrderPageObject getOrderPage(WebDriver driver) {
        if (orderPage == null) {
            return new OrderPageObject(driver);
        }
        return orderPage;
    }

}
