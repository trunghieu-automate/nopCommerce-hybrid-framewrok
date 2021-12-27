package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.RecentViewPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RecentViewPageObject extends BasePage {
    public RecentViewPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDisplayedByName(String... productNames) {
        List<WebElement> elements = waitElementsVisible(RecentViewPageUI.RECENT_VIEW_RESULT_BY_PRODUCT_NAME, 3);
        boolean result = false;
        for(WebElement element : elements) {
            for(String productName : productNames) {
                result = element.getText().equals(productName);
            }
        } return result;
    }
}
