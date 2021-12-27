package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.CheckoutPageUI;
import org.openqa.selenium.WebDriver;


public class CheckoutPageObject extends BasePage {
    public CheckoutPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isOrderNumberDisplayed() {
        return isElementsDisplayed(CheckoutPageUI.ORDER_NUMBER);
    }

    public String getOrderNumber() {
        String orderNumber = getElementText(CheckoutPageUI.ORDER_NUMBER);
        return orderNumber.substring(orderNumber.indexOf(":") + 1).strip();
    }

    public void selectShippingMethodByName(String option) {
        checkRadioBtn(getDynamicXpath(CheckoutPageUI.RADIO_BUTTON_BY_LABEL_TEXT_NAME, option));
    }

    public void selectPaymentTypeByTextName(String option) {
        checkRadioBtn(getDynamicXpath(CheckoutPageUI.RADIO_BUTTON_BY_LABEL_TEXT_NAME, option));
    }

    public String getPaymentInformationText() {
        return getElementText(CheckoutPageUI.PAYMENT_TO_INFORMATION_TEXT).strip();
    }

    public String getBillingInfoByName(String infoName) {
        String info = getElementText(getDynamicXpath(CheckoutPageUI.PAYMENT_BILLING_ADDRESS_INFO_BY_NAME, infoName)).strip();
        try {
            return info.substring(info.indexOf(":") + 1).strip();
        } catch (Exception e) {
            return info;
        }
    }

    public String getShippingInfoByName(String infoName) {
        String info = getElementText(getDynamicXpath(CheckoutPageUI.PAYMENT_SHIPPING_ADDRESS_INFO_BY_NAME, infoName)).strip();
        try {
            return info.substring(info.indexOf(":") + 1).strip();
        } catch (Exception e) {
            return info;
        }
    }

    public String getPaymentMethodText() {
        return getElementText(CheckoutPageUI.PAYMENT_METHOD_INFO).strip();
    }

    public Object getShippingMethodInfoByName() {
        return getElementText(CheckoutPageUI.SHIPPING_METHOD_INFO).strip();
    }

    public String getProductSKUByProductName(String productName) {
        return getElementText(getDynamicXpath(CheckoutPageUI.ITEM_INFO_TABLE_SKU_INFO_BY_PRODUCT_NAME, productName));
    }

    public boolean isProductInCheckoutItemTable(String productName) {
        return isElementsDisplayed(getDynamicXpath(CheckoutPageUI.ITEM_INFO_TABLE_PRODUCT_NAME, productName));
    }

    public String getProductPriceByProductName(String productName) {
        return getElementText(getDynamicXpath(CheckoutPageUI.ITEM_INFO_TABLE_UNIT_PRICE_BY_PRODUCT_NAME, productName));
    }

    public String getProductQuantityByProductName(String productName) {
        return getElementText(getDynamicXpath(CheckoutPageUI.ITEM_INFO_TABLE_QUANTITY_BY_PRODUCT_NAME, productName));

    }

    public String getProductTotalCostByProductName(String productName) {
        return getElementText(getDynamicXpath(CheckoutPageUI.ITEM_INFO_TABLE_TOTAL_COST_BY_PRODUCT_NAME, productName));
    }

    public String getGiftWrappingOption() {
        String wrappingOpt = getElementText(CheckoutPageUI.GIFT_WRAPPING_OPTION).strip();
        return wrappingOpt.substring(wrappingOpt.indexOf(":") + 1).strip();
    }


    public String getTotalTableInfoByName(String infoName) {
        return getElementText(getDynamicXpath(CheckoutPageUI.TOTAL_INFORMATION_BY_INFO_NAME, infoName));
    }

    public String getPageTitleText() {
        return getElementText(CheckoutPageUI.CHECKOUT_SUCCESS_PAGE_TITLE);
    }

    public Object getOrderSuccessMessageText() {
        return getElementText(CheckoutPageUI.CHECKOUT_SUCCESS_PAGE_SUCCESS_MESSAGE);
    }

    public void clickToContinueButtonAtStep(String onStep) {
        clickToElement(getDynamicXpath(CheckoutPageUI.CONTINUE_BUTTON_BY_STEP, onStep));
    }

    public void clickToConfirmBtn() {
        for (int i = 1; i<=10; i++){
            if(!getElementText(CheckoutPageUI.CHECKOUT_SUCCESS_PAGE_TITLE).equals("Thank you")){
                jsClickToElement(CheckoutPageUI.CONFIRM_BUTTON);
                try{
                    closeAlert();
                } catch (Exception ignored) {}
                sleepInSecond(1);
            } else break;
        }
        areJQueryAndJSLoadedSuccess(5);
    }
}