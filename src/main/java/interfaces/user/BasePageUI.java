package interfaces.user;

public class BasePageUI {
    public static final String TOP_NAV_BAR_MENU_BY_NAME = "//*[@class='ico-%s']";
    public static final String ELEMENT_BY_ID = "//*[@id='%s']";
    public static final String BUTTON_BY_TEXT_NAME = "//button[normalize-space()='%s']";
    public static final String VALIDATION_ERROR_MESSAGE_BY_FIELD_ID = "//*[@id='%s']//following-sibling::span[@class='field-validation-error']//span";
    public static final String FOOTER_MENU_LINK_BY_TEXT_NAME = "//div[@class='footer']//a[text()='%s']";
    public static final String LINK_BY_TEXT_NAME = "//a[text()='%s']";
    public static final String HEADER_LOGO_IMG = "//div[@class='header-logo']//img";
    public static final String PRODUCT_MENU_BAR_LINK = "//ul[@class='top-menu notmobile']//a[contains(.,'%s')]";
    public static final String SHOPPING_CART_NUMBER_OF_ITEM = "//li[@id='topcartlink']//span[@class='cart-qty']";

    // flyout cart
    public static final String FLYOUT_CART_NUMBER_OF_ITEM = "//div[@id='flyout-cart']//div[@class='count']";
    public static final String FLYOUT_CART_ITEM_ATTRIBUTE_BY_PRODUCT_NAME = "//div[@id='flyout-cart']//div[@class='name']" +
            "/a[text()='%s']//parent::div//following-sibling::div[@class='attributes']";
    public static final String FLYOUT_CART_ITEM_PRICE_BY_PRODUCT_NAME = "//div[@id='flyout-cart']//div[@class='name']" +
            "/a[text()='%s']//parent::div//following-sibling::div[@class='price']";
    public static final String FLYOUT_CART_ITEM_QUANTITY_BY_PRODUCT_NAME = "//div[@id='flyout-cart']//div[@class='name']" +
            "/a[text()='%s']//parent::div//following-sibling::div[@class='quantity']";
    public static final String FLYOUT_CART_ITEM_SUBTOTAL = "//div[@id='flyout-cart']//div[@class='totals']";
    public static final String FLYOUT_CART_CONTAINS_PRODUCT_NAME = "//div[@id='flyout-cart']//div[@class='name']//a";
    public static final String FLYOUT_CART_NO_ITEM_MESSAGE = "//div[@id='flyout-cart']//div[@class='count' " +
            "and contains(.,'You have no items in your shopping cart.')]";

    // Admin page
    public static final String ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_FIRST_MENU = "//nav[@class='mt-2']" +
            "//ul[@role='menu']//p[normalize-space()='%s']";
    public static final String ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_SECOND_MENU = "//nav[@class='mt-2']" +
            "//ul[@role='menu']//p[normalize-space()='%s']" +
            "//ancestor::a//following-sibling::ul//p[normalize-space()='%s']";
    public static final String ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_THIRD_MENU = "//nav[@class='mt-2']" +
            "//ul[@role='menu']//p[normalize-space()='%s']" +
            "//ancestor::a//following-sibling::ul//p[normalize-space()='%s']" +
            "//ancestor::a//following-sibling::ul//p[normalize-space()='%s']";

    public static final String ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_FIRST_MENU_ICON = "//nav[@class='mt-2']//ul[@role='menu']//p[normalize-space()='%s']/i";
    public static final String ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_SECOND_MENU_ICON = "//nav[@class='mt-2']//ul[@role='menu']//p[normalize-space()='%s']/i" +
            "//ancestor::a//following-sibling::ul//p[normalize-space()='%s']/i";
    public static final String CARD_COLLAPSED_BUTTON_ICON = "//div[@id='%s']//button[@data-card-widget]//i";
    public static final String CARD_INFO_COLLAPSED_BUTTON_BY_ID = "//div[@id='%s']//button[@data-card-widget]";
}
