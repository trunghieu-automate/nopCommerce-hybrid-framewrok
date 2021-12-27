package actions.pageObjects.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static LoginPageObject loginPage;
    private static SearchProductPageObject searchProductPage;
    private static EditProductPageObject editProductPage;

    private PageGeneratorManager(){
    }

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static SearchProductPageObject getSearchProductPage(WebDriver driver){
        return new SearchProductPageObject(driver);
    }

    public static EditProductPageObject getEditProductPage(WebDriver driver){
        return new EditProductPageObject(driver);
    }

    public static CustomerPageObject getCustomerPage(WebDriver driver) {
        return new CustomerPageObject(driver);
    }
}
