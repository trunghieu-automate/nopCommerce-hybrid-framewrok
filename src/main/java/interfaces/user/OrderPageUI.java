package interfaces.user;


public class OrderPageUI{
    public static final String NUMBER_OF_ORDER = "//div[@class='order-number']//strong";
    public static final String PAYMENT_BILLING_ADDRESS_INFO_BY_NAME = "//div[@class='billing-info']//ul//li[@class='%s']";
    public static final String PAYMENT_SHIPPING_ADDRESS_INFO_BY_NAME = "//div[@class='shipping-info']//ul//li[@class='%s']";
    public static final String PAYMENT_METHOD_INFO = "//div[@class='payment-method-info']//ul/li/span[@class='value']";
    public static final String SHIPPING_METHOD_INFO = "//div[@class='shipping-method-info']//ul/li/span[@class='value']";

    public static final String ITEM_DATA_TABLE_SKU_INFO_BY_PRODUCT_NAME = "//table[@class='data-table']//td[@class='product']" +
            "//a[text()='%s']//ancestor::td//preceding-sibling::td//span";
    public static final String ITEM_DATA_TABLE_PRODUCT_NAME = "//table[@class='data-table']//td[@class='product']//a[text()='%s']";
    public static final String ITEM_DATA_TABLE_UNIT_PRICE_BY_PRODUCT_NAME = "//table[@class='data-table']//td[@class='product']" +
            "//a[text()='%s']//ancestor::td//following-sibling::td[@class='unit-price']//span";
    public static final String ITEM_DATA_TABLE_QUANTITY_BY_PRODUCT_NAME = "//table[@class='data-table']//td[@class='product']" +
            "//a[text()='%s']//ancestor::td//following-sibling::td[@class='quantity']//span";
    public static final String ITEM_DATA_TABLE_TOTAL_COST_BY_PRODUCT_NAME = "//table[@class='data-table']//td[@class='product']" +
            "//a[text()='%s']//ancestor::td//following-sibling::td[@class='total']//span";
    public static final String GIFT_WRAPPING_OPTION = "//div[@class='section options']/div";
    public static final String TOTAL_INFORMATION_BY_INFO_NAME = "//div[@class='total-info']//label[text()='%s:']//parent::td//following-sibling::td//span";
    public static final String ORDER_OVERVIEW_INFO_BY_NAME = "//ul[@class='order-overview-content']//li[@class='order-%s']";
}
