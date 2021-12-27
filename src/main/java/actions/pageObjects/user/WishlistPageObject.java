package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.WishlistPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishlistPageObject extends BasePage {
    public WishlistPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameDisplayedInWishlistPage(String... productNames) {
        List<WebElement> elements = waitElementsVisible(WishlistPageUI.WISHLIST_TABLE_COLUMN_BY_PRODUCT_NAME, 3);
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


    public void clickToYourSharingLink() {
        clickToElement(WishlistPageUI.SHARING_LINK);
    }

    public Object getWishlistTableTitleText() {
        return getElementText(WishlistPageUI.WISHLIST_TABLE_TITLE);
    }

    public void checkTheAddToCartCheckboxByProductName(String productName) {
        checkToCheckbox(getDynamicXpath(WishlistPageUI.ADD_TO_CART_CHECK_BOX_BY_PRODUCT_NAME, productName));
    }

    public void clickRemoveButtonToRemoveProductByName(String productName) {
        clickToElement(getDynamicXpath(WishlistPageUI.WISHLIST_TABLE_REMOVAL_ROW_BY_PRODUCT_NAME, productName));
    }

    public boolean isWishlistEmptyTextDisplayed() {
        return getElementText(WishlistPageUI.WISHLIST_EMPTY_NOTE_TEXT).equals("The wishlist is empty!");
    }
}
