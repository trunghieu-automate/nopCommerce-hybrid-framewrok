package interfaces.user;


public class SearchPageUI{
    public static final String SEARCH_PAGE_RESULT_PRODUCT_CONTAINS_NAME = "//div[@class='item-box']//h2" +
            "/a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'%s')]";
    public static final String SEARCH_PAGE_SEARCH_BUTTON = "//div[@class='page search-page']//button";
    public static final String SEARCH_PAGE_CHECKBOX_BY_ID = "//input[@id='%s']";
    public static final String SEARCH_PAGE_DROPDOWN_LIST_BY_ID = "//select[@id='%s']";
    public static final String SEARCH_PAGE_RESULT = "//div[@class='search-results']";

}
