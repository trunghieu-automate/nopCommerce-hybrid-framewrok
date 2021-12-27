package interfaces.user;

public class CheckoutPageUI {
    public static final String CONTINUE_BUTTON_BY_STEP = "//button[@onclick='%s.save()' and text()='Continue']";
    public static final String ORDER_NUMBER = "//div[@class='order-number']//strong";
    public static final String RADIO_BUTTON_BY_LABEL_TEXT_NAME = "//label[contains(.,'%s')]//preceding-sibling::input";
    public static final String PAYMENT_TO_INFORMATION_TEXT = "//div[@class='section payment-info']//p[2]";
    public static final String PAYMENT_BILLING_ADDRESS_INFO_BY_NAME = "//div[@class='billing-info']//ul//li[@class='%s']";
    public static final String PAYMENT_SHIPPING_ADDRESS_INFO_BY_NAME = "//div[@class='shipping-info']//ul//li[@class='%s']";
    public static final String PAYMENT_METHOD_INFO = "//div[@class='payment-method-info']//ul/li/span[@class='value']";
    public static final String SHIPPING_METHOD_INFO = "//div[@class='shipping-method-info']//ul/li/span[@class='value']";
    public static final String ITEM_INFO_TABLE_SKU_INFO_BY_PRODUCT_NAME = "//table[@class='cart']//td[@class='product']" +
            "//a[text()='%s']//parent::td//preceding-sibling::td[@class='sku']//span";
    public static final String ITEM_INFO_TABLE_PRODUCT_NAME = "//table[@class='cart']//td[@class='product']//a[text()='%s']";
    public static final String ITEM_INFO_TABLE_UNIT_PRICE_BY_PRODUCT_NAME = "//table[@class='cart']//td[@class='product']" +
            "//a[text()='%s']//parent::td//following-sibling::td[@class='unit-price']//span";
    public static final String ITEM_INFO_TABLE_QUANTITY_BY_PRODUCT_NAME = "//table[@class='cart']//td[@class='product']" +
            "//a[text()='%s']//parent::td//following-sibling::td[@class='quantity']//span";
    public static final String ITEM_INFO_TABLE_TOTAL_COST_BY_PRODUCT_NAME = "//table[@class='cart']//td[@class='product']" +
            "//a[text()='%s']//parent::td//following-sibling::td[@class='subtotal']//span";
    public static final String GIFT_WRAPPING_OPTION = "//div[@class='cart-options']/div[@class='selected-checkout-attributes']";
    public static final String TOTAL_INFORMATION_BY_INFO_NAME = "//div[@class='total-info']//label[text()='%s:']//parent::td//following-sibling::td//span";
    public static final String CHECKOUT_SUCCESS_PAGE_TITLE = "//div[@class='page-title']//h1";
    public static final String CHECKOUT_SUCCESS_PAGE_SUCCESS_MESSAGE = "//div[@class='title']//strong";

    public static final String CONFIRM_BUTTON = "//button[@onClick='ConfirmOrder.save()']";
}
