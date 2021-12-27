package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.BasePageUI;
import interfaces.user.MyAccountPageUI;
import org.openqa.selenium.WebDriver;


public class MyAccountPageObject extends BasePage {
    public MyAccountPageObject(WebDriver driver) {
        super(driver);
    }

    public void selectGenderCheckboxByTextName(String gender) {
        checkRadioBtn(getDynamicXpath(MyAccountPageUI.GENDER_CHECKBOX_BY_TYPE, gender.toLowerCase()));
    }

    public void selectDobDropdownListByTextName(String dropdownListName, String itemName) {
        selectItemDefaultDropdownListByName(getDynamicXpath(MyAccountPageUI.DOB_DROPDOWN_LIST_BY_NAME, dropdownListName), itemName);
    }

    public String getValueFromTextboxById(String textboxId) {
        return getElementAttribute(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, textboxId), "value");
    }

    public boolean isCheckboxSelectedByName(String gender) {
        if (isRadioChecked(getDynamicXpath(MyAccountPageUI.GENDER_CHECKBOX_BY_TYPE, gender))) {
            return true;
        }
        return false;
    }

    public String getCurrentOptionDropdownListByName(String dropdownListName) {
        return getCurrentOptionTextDefaultDropDownList(getDynamicXpath(MyAccountPageUI.DOB_DROPDOWN_LIST_BY_NAME, dropdownListName));
    }

    public void clickLeftMenuBarByNameText(String menuName) {
        clickToElement(getDynamicXpath(MyAccountPageUI.LEFT_MENU_BAR_BY_NAME, menuName));
    }

    public String getNewAddressInfoResultByClassname(String classname) {
        return getElementText(getDynamicXpath(MyAccountPageUI.NEW_ADDRESS_RESULT_INFO_BY_CLASS_NAME, classname));
    }

    public boolean isPasswordChangedSuccessMessageDisplayed() {
        try {
            waitElementsVisible(MyAccountPageUI.PASSWORD_CHANGED_SUCCESS_MESSAGE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clickToCloseMessageBar() {
        clickToElement(MyAccountPageUI.PASSWORD_CHANGED_SUCCESS_MESSAGE_BAR_CLOSE_BUTTON);

    }

    public String getProductReviewRatingStars() {
        String star = getElementCssValue(MyAccountPageUI.PRODUCT_REVIEW_RATING_STAR, "width");
        switch(star){
            case "95px":
                return "5";
            case "76px":
                return "4";
            case "57px":
                return "3";
            case "38px":
                return "2";
            case "19px":
                return "1";
        }
        return null;
    }

    public String getProductReviewProductName() {
        return getElementText(MyAccountPageUI.PRODUCT_REVIEW_FOR_PRODUCT_NAME);
    }

    public String getProductReviewContentText() {
        return getElementText(MyAccountPageUI.PRODUCT_REVIEW_CONTENT_TEXT);
    }

    public String getProductReviewTitle() {
        return getElementText(MyAccountPageUI.PRODUCT_REVIEW_TITLE);
    }

    public String getOrderNumberAtMyAccountPage() {
        String orderNumber = getElementText(MyAccountPageUI.ORDER_PAGE_ORDER_NUMBER).strip();
        return orderNumber.substring(orderNumber.indexOf(":")+1).strip();
    }

    public OrderPageObject clickToDetailButtonByOrderNumber(String orderNumber) {
        clickToElement(getDynamicXpath(MyAccountPageUI.ORDER_PAGE_DETAIL_BUTTON_BY_ORDER_NUMBER, orderNumber));
        return PageGeneratorManager.getOrderPage(driver);
    }
}
