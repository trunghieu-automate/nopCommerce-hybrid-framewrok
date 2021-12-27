package interfaces.user;

public class MyAccountPageUI {
    public static final String GENDER_CHECKBOX_BY_TYPE = "//*[@id='gender-%s']";
    public static final String DOB_DROPDOWN_LIST_BY_NAME = "//select[@name='DateOfBirth%s']";
    public static final String LEFT_MENU_BAR_BY_NAME = "//div[@class='side-2']//a[text()='%s']";
    public static final String NEW_ADDRESS_RESULT_INFO_BY_CLASS_NAME = "//ul[@class='info']//li[@class='%s']";
    public static final String PASSWORD_CHANGED_SUCCESS_MESSAGE = "//div[@class='bar-notification success']//p";
    public static final String PASSWORD_CHANGED_SUCCESS_MESSAGE_BAR_CLOSE_BUTTON = "//div[@class='bar-notification success']//span";

    // Product reviews
    public static final String PRODUCT_REVIEW_RATING_STAR = "//div[@class='rating']//div";
    public static final String PRODUCT_REVIEW_FOR_PRODUCT_NAME = "//div[@class='review-info']//a";
    public static final String PRODUCT_REVIEW_CONTENT_TEXT = "//div[@class='review-text']";
    public static final String PRODUCT_REVIEW_TITLE = "//div[@class='review-title']";

    // Orders
    public static final String ORDER_PAGE_ORDER_NUMBER = "//div[@class='page-body']//div[@class='title']//strong";
    public static final String ORDER_PAGE_DETAIL_BUTTON_BY_ORDER_NUMBER = "//div[@class='order-list']//strong[contains(.,'%s')]//parent::div//following-sibling::div/button";
}
