package interfaces.user;

public class ProductPageUI {
    public static final String PRODUCT_BY_NAME = "//div[@class='product-item']//h2//a[text()='%s']";
    public static final String PRODUCT_DISPLAY_NAME = "//div[@class='product-item']//h2//a";
    public static final String PRODUCT_DISPLAY_PRICE = "//div[@class='product-item']//div[@class='prices']//span";
    public static final String TOP_NOTIFICATION_GREEN_BAR = "//div[@class='bar-notification success']/p";
    public static final String TOP_NOTIFICATION_GREEN_BAR_CLOSE_BUTTON = "//div[@class='bar-notification success']//span";
    public static final String ADD_TO_COMPARE_LIST_BY_PRODUCT_NAME = "//div[@class='products-container']//a[text()='%s']" +
            "//parent::h2//following-sibling::div[@class='add-info']//div[@class='buttons']//button[text()='Add to compare list']";
    public static final String ADD_TO_CART_BY_PRODUCT_NAME = "//div[@class='products-container']//a[text()='%s']" +
            "//parent::h2//following-sibling::div[@class='add-info']//div[@class='buttons']//button[text()='Add to cart']";
}
