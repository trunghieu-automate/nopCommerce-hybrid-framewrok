package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.CartPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPageObject extends BasePage {
    public CartPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameDisplayedInCartPage(String... productNames) {
        List<WebElement> elements = waitElementsVisible(CartPageUI.CART_TABLE_PRODUCT_ROW, 3);
        boolean result = false;
        if (elements.size() != 0) {
            for (WebElement element : elements) {
                for (String productName : productNames) {
                    if (element.getText().equalsIgnoreCase(productName)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public ProductPageObject clickToEditLinkOfProductName(String productName) {
        clickToElement(getDynamicXpath(CartPageUI.CART_TABLE_EDIT_BUTTON_BY_PRODUCT_NAME, productName));
        return PageGeneratorManager.getProductPage(driver);
    }

    public boolean isCartPageEmptyTextDisplayed() {
        return isElementsDisplayed(CartPageUI.CART_PAGE_EMPTY_ITEM_MESSAGE);
    }

    public void clickToRemoveButtonByProductName(String productName) {
        clickToElement(getDynamicXpath(CartPageUI.CART_PAGE_REMOVE_ITEM_BUTTON, productName));
    }

    public void enterToQuantityTextBoxByProductName(String productName, String quantity) {
        sendKeysToElement(getDynamicXpath(CartPageUI.CART_PAGE_QUANTITY_TEXT_BOX, productName), quantity);
    }

    public String getQuantityOfItemByProductName(String productName) {
        return getElementAttribute(getDynamicXpath(CartPageUI.CART_PAGE_QUANTITY_TEXT_BOX, productName), "value");
    }

    public void selectShippingMethodRadioButtonByName(String optionName) {
        clickToElement(getDynamicXpath(CartPageUI.ESTIMATE_SHIPPING_METHOD_RADIO_BUTTON_BY_TEXT_NAME, optionName));
    }

    public CheckoutPageObject clickToCheckoutButton() {
        clickToElementById("checkout");
        return PageGeneratorManager.getCheckoutPage(driver);
    }
}
