package interfaces.user;

public class CompareProductPageUI {
    public static final String COMPARE_PRODUCT_PAGE_CLEAR_BUTTON = "//a[@class='clear-list']";
    public static final String COMPARE_TABLE_PRODUCT_NAME_ROW = "//tr[@class='product-name']//a";
    public static final String COMPARE_PRODUCT_PAGE_PRICE_BY_PRODUCT_NAME = "//tr[@class='product-name']" +
            "/td/a[text()='%s']//ancestor::tr//following-sibling::tr[@class='product-price']/td[text()='%s']";

    public static final String COMPARE_TABLE_REMOVAL_BUTTON_ROW = "//tr[@class='remove-product']//button";
}
