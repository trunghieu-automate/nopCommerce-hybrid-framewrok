package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.BasePageUI;
import interfaces.user.ProductPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPageObject extends BasePage {
    public ProductPageObject(WebDriver driver) {
        super(driver);
    }

    public void selectRadioButtonById(String buttonId) {
        clickToElement(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, buttonId));
    }


    public void clickToProductByName(String productName) {
        clickToElement(getDynamicXpath(ProductPageUI.PRODUCT_BY_NAME, productName));
    }

    public boolean isNumberOfProductsDisplayedLessThanOrEqualTo(int expectedNumberOfProduct) {
        areJQueryAndJSLoadedSuccess(5);
        return waitElementsVisible(ProductPageUI.PRODUCT_DISPLAY_NAME).size() <= expectedNumberOfProduct;
    }

    public boolean isProductsSortedByNameAsOrder(String order) {
        areJQueryAndJSLoadedSuccess(5);
        List<WebElement> elements = waitElementsVisible(ProductPageUI.PRODUCT_DISPLAY_NAME);
        ArrayList<String> origin = new ArrayList<>();
        for (WebElement element : elements) {
            origin.add(element.getText());
        }

        ArrayList<String> ascending = new ArrayList<>(origin);
        Collections.sort(ascending);

        List<String> descending = new ArrayList<>(origin);
        descending.sort(Collections.reverseOrder());

        return (ascending.equals(origin) && order.equalsIgnoreCase("ascending"))
                || (descending.equals(origin) && order.equalsIgnoreCase("descending"));
    }

    public boolean isProductsSortByPriceAsOrder(String order) {
        areJQueryAndJSLoadedSuccess(5);
        List<WebElement> elements = waitElementsVisible(ProductPageUI.PRODUCT_DISPLAY_PRICE);
        ArrayList<Integer> origin = new ArrayList<>();
        for (WebElement element : elements) {
            String pattern = "[$,.]";
            String stock = element.getText();
            String refine = stock.replaceAll(pattern, "");
            int num = Integer.parseInt(refine);
            origin.add(num);
        }

        ArrayList<Integer> ascending = new ArrayList<>(origin);
        Collections.sort(ascending);

        List<Integer> descending = new ArrayList<>(origin);
        descending.sort(Collections.reverseOrder());

        return (ascending.equals(origin) && order.equalsIgnoreCase("ascending"))
                || (descending.equals(origin) && order.equalsIgnoreCase("descending"));
    }

    public boolean isTopNotificationGreenBarDisplayedWithText(String notificationText) {
        return getElementText(ProductPageUI.TOP_NOTIFICATION_GREEN_BAR).equalsIgnoreCase(notificationText);
    }

    public void clickToCloseButtonAtTopNotificationGreenBar() {
        clickToElement(ProductPageUI.TOP_NOTIFICATION_GREEN_BAR_CLOSE_BUTTON);
    }

    public void clickToAddToCompareListByProductName(String productName) {
        clickToElement(getDynamicXpath(ProductPageUI.ADD_TO_COMPARE_LIST_BY_PRODUCT_NAME, productName));
        areJQueryAndJSLoadedSuccess(5);
    }


    public boolean isAttributeInFlyoutCartByProductName(String productName, String attributeValue) {
        return getElementText(getDynamicXpath(BasePageUI.FLYOUT_CART_ITEM_ATTRIBUTE_BY_PRODUCT_NAME, productName))
                .contains(attributeValue);
    }

    public void clickToAddToCartByProductName(String productName) {
        clickToElement(getDynamicXpath(ProductPageUI.ADD_TO_CART_BY_PRODUCT_NAME, productName));
    }
}
