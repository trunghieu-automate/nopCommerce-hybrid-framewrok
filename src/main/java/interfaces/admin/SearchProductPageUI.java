package interfaces.admin;

public class SearchProductPageUI {
    public static final String GRID_INFO_NUMBER_OF_ITEM = "//div[@id='products-grid_info']";
    public static final String SEARCH_RESULT_TABLE_ITEM_NAME = "//table[@id='products-grid']//tbody//tr//td[text()='%s']";
    public static final String SEARCH_RESULT_TABLE_SKU_BY_PRODUCT_NAME = "//table[@id='products-grid']//tbody//tr" +
            "//td[text()='%s']//following-sibling::td[1]";
    public static final String SEARCH_RESULT_TABLE_UNIT_PRICE_BY_PRODUCT_NAME = "//table[@id='products-grid']//tbody//tr" +
            "//td[text()='%s']//following-sibling::td[2]";
    public static final String SEARCH_RESULT_TABLE_STOCK_QUANTITY_BY_PRODUCT_NAME = "//table[@id='products-grid']//tbody//tr" +
            "//td[text()='%s']//following-sibling::td[3]";
    public static final String SUB_CATEGORIES_CHECKBOX = "//*[@id='SearchIncludeSubCategories']";
    public static final String SEARCH_RESULT_TABLE_EMPTY_DATA_WITH_TEXT = "//table[@id='products-grid']//tbody//tr//td[@class='dataTables_empty' and normalize-space()='No data available in table']";
    public static final String GO_BUTTON_AT_PRODUCT_MENU= "//button[@id='go-to-product-by-sku']";
}
