package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.OrderPageUI;
import org.openqa.selenium.WebDriver;

public class OrderPageObject extends BasePage {
    public OrderPageObject(WebDriver driver) {
        super(driver);
    }
    public String getBillingInfoByName(String infoName) {
        String info = getElementText(getDynamicXpath(OrderPageUI.PAYMENT_BILLING_ADDRESS_INFO_BY_NAME, infoName)).strip();
        try{
            return info.substring(info.indexOf(":")+1).strip();
        } catch (Exception e){
            return info;
        }
    }

    public String getShippingInfoByName(String infoName) {
        String info = getElementText(getDynamicXpath(OrderPageUI.PAYMENT_SHIPPING_ADDRESS_INFO_BY_NAME, infoName)).strip();
        try{
            return info.substring(info.indexOf(":")+1).strip();
        } catch (Exception e){
            return info;
        }
    }

    public String getPaymentMethodText() {
        return getElementText(OrderPageUI.PAYMENT_METHOD_INFO).strip();
    }

    public Object getShippingMethodInfoByName() {
        return getElementText(OrderPageUI.SHIPPING_METHOD_INFO).strip();
    }

    public String getProductSKUByProductName(String productName) {
        return getElementText(getDynamicXpath(OrderPageUI.ITEM_DATA_TABLE_SKU_INFO_BY_PRODUCT_NAME, productName));
    }

    public boolean isProductInCheckoutItemTable(String productName) {
        return isElementsDisplayed(getDynamicXpath(OrderPageUI.ITEM_DATA_TABLE_PRODUCT_NAME, productName));
    }

    public String getProductPriceByProductName(String productName) {
        return getElementText(getDynamicXpath(OrderPageUI.ITEM_DATA_TABLE_UNIT_PRICE_BY_PRODUCT_NAME, productName));
    }

    public String getProductQuantityByProductName(String productName) {
        return getElementText(getDynamicXpath(OrderPageUI.ITEM_DATA_TABLE_QUANTITY_BY_PRODUCT_NAME, productName));

    }

    public String getProductTotalCostByProductName(String productName) {
        return getElementText(getDynamicXpath(OrderPageUI.ITEM_DATA_TABLE_TOTAL_COST_BY_PRODUCT_NAME, productName));
    }

    public String getOrderNumberAtOrderPage() {
        String orderNumber = getElementText(OrderPageUI.NUMBER_OF_ORDER).strip();
        return orderNumber.substring(orderNumber.indexOf("#")+1).strip();
    }

    public String getTotalTableInfoByName(String infoName) {
        return getElementText(getDynamicXpath(OrderPageUI.TOTAL_INFORMATION_BY_INFO_NAME, infoName));
    }

    public String getGiftWrappingOption() {
        String wrappingOpt = getElementText(OrderPageUI.GIFT_WRAPPING_OPTION).strip();
        return wrappingOpt.substring(wrappingOpt.indexOf(":")+1).strip();
    }

    public String getOrderOverviewInfoByName(String infoName) {
        String info = getElementText(getDynamicXpath(OrderPageUI.ORDER_OVERVIEW_INFO_BY_NAME, infoName));
        return info.substring(info.indexOf(":")+1).strip();
    }
}
