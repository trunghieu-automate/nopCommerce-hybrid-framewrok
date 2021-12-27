package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.CompareProductPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CompareProductPageObject extends BasePage {
    public CompareProductPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isClearButtonDisplayed() {
        return isElementsDisplayed(CompareProductPageUI.COMPARE_PRODUCT_PAGE_CLEAR_BUTTON);
    }

    public void clickToClearListButton() {
        clickToElement(CompareProductPageUI.COMPARE_PRODUCT_PAGE_CLEAR_BUTTON);
    }

    public boolean isProductDisplayedByName(String... productNames) {
        List<WebElement> elements = waitElementsVisible(CompareProductPageUI.COMPARE_TABLE_PRODUCT_NAME_ROW, 3);
        boolean result = false;
        if(elements.size() != 0){
            for(WebElement element : elements){
                for(String productName: productNames){
                    if(element.getText().equals(productName)){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean isProductPriceDisplayedByProductName(String... productInfo) {
        return isElementsDisplayed(getDynamicXpath(CompareProductPageUI.COMPARE_PRODUCT_PAGE_PRICE_BY_PRODUCT_NAME, productInfo));
    }

    public boolean isNumberOfRemovalButtonSameAsNumberOfProductDisplayed() {
        return waitElementsVisible(CompareProductPageUI.COMPARE_TABLE_PRODUCT_NAME_ROW).size() ==
                waitElementsVisible(CompareProductPageUI.COMPARE_TABLE_REMOVAL_BUTTON_ROW).size();
    }
}
