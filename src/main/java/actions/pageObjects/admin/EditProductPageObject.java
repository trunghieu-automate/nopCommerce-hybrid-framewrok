package actions.pageObjects.admin;

import actions.common.BasePage;
import interfaces.admin.EditProductPageUI;
import org.openqa.selenium.WebDriver;

public class EditProductPageObject extends BasePage {
    public EditProductPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isEditPageHeadingTextContainProductName(String s) {
        return isElementsDisplayed(EditProductPageUI.PAGE_HEADER_TEXT_WITH_PRODUCT_NAME);
    }

    public String getTextboxValueByMenuName(String menuName) {
        return getElementAttribute(getDynamicXpath(EditProductPageUI.TEXTBOX_VALUE_BY_MENU_NAME, menuName), "value");
    }
}
