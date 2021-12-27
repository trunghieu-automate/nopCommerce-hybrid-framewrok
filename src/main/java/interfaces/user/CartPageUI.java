package interfaces.user;

public class CartPageUI {
    public static final String CART_TABLE_PRODUCT_ROW = "//td[@class='product']/a";
    public static final String CART_TABLE_EDIT_BUTTON_BY_PRODUCT_NAME = "//a[text()='%s']//following-sibling::div[@class='edit-item']//a";
    public static final String CART_PAGE_EMPTY_ITEM_MESSAGE = "//div[@class='no-data' and contains(.,'Your Shopping Cart is empty!')]";
    public static final String CART_PAGE_REMOVE_ITEM_BUTTON = "//a[@class='product-name' and text()='%s']//parent::td//following-sibling::td[@class='remove-from-cart']/button";
    public static final String CART_PAGE_QUANTITY_TEXT_BOX = "//a[@class='product-name' and text()='%s']//parent::td//following-sibling::td[@class='quantity']/input";
    public static final String ESTIMATE_SHIPPING_METHOD_RADIO_BUTTON_BY_TEXT_NAME = "//div[@class='shipping-options']//div[text()='%s']";
}
