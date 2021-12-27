package interfaces.user;

public class WishlistPageUI {
    public static final String WISHLIST_TABLE_ROW_BY_PRODUCT_NAME = "//td[@class='product']//a[text()='%s']";
    public static final String SHARING_LINK = "//div[@class='share-info']/a";
    public static final String WISHLIST_TABLE_TITLE = "//div[@class='page-title']/h1";
    public static final String ADD_TO_CART_CHECK_BOX_BY_PRODUCT_NAME = "//a[text()='%s']//parent::td//preceding-sibling::td[@class='add-to-cart']/input";
    public static final String WISHLIST_TABLE_REMOVAL_ROW_BY_PRODUCT_NAME = "//a[text()='%s']//parent::td//following-sibling::td[@class='remove-from-cart']/button";
    public static final String WISHLIST_EMPTY_NOTE_TEXT = "//div[@class='no-data']";
    public static final String WISHLIST_TABLE_COLUMN_BY_PRODUCT_NAME = "//td[@class='product']//a";
}
