package actions.pageObjects.admin;

import actions.common.BasePage;
import interfaces.admin.CustomerPageUI;
import interfaces.admin.SearchProductPageUI;
import org.openqa.selenium.WebDriver;

public class SearchProductPageObject extends BasePage {
    public SearchProductPageObject(WebDriver driver) {
        super(driver);
    }

    public String getNumberOfItemDisplayedAtGridInfo() {
        String rawText = getElementText(SearchProductPageUI.GRID_INFO_NUMBER_OF_ITEM);
        return rawText.substring(rawText.indexOf("of")).replaceAll("\\D", "");
    }

    public boolean isItemNameInProductTableDisplayed(String productName) {
        return isElementsDisplayed(getDynamicXpath(SearchProductPageUI.SEARCH_RESULT_TABLE_ITEM_NAME, productName));
    }

    public String getProductSKUByProductName(String productName) {
        return getElementText(getDynamicXpath(SearchProductPageUI.SEARCH_RESULT_TABLE_SKU_BY_PRODUCT_NAME, productName));
    }

    public String getProductUnitPriceByProductName(String productName) {
        return getElementText(getDynamicXpath(SearchProductPageUI.SEARCH_RESULT_TABLE_UNIT_PRICE_BY_PRODUCT_NAME, productName));
    }

    public String getProductStockQuantityByProductName(String productName) {
        return getElementText(getDynamicXpath(SearchProductPageUI.SEARCH_RESULT_TABLE_STOCK_QUANTITY_BY_PRODUCT_NAME, productName));
    }

    public void checkTheSubcategoriesCheckbox(boolean status) {
        String subCategory = SearchProductPageUI.SUB_CATEGORIES_CHECKBOX;
        if ((!isCheckboxChecked(subCategory) && status) || (!status && isCheckboxChecked(subCategory))) {
            clickToElement(subCategory);
        }
    }

    public boolean isProductTableNoDataTextDisplayed() {
        return isElementsDisplayed(SearchProductPageUI.SEARCH_RESULT_TABLE_EMPTY_DATA_WITH_TEXT);
    }

    public EditProductPageObject clickToGoDirectlyToEditProductPage() {
        clickToElement(SearchProductPageUI.GO_BUTTON_AT_PRODUCT_MENU);
        return PageGeneratorManager.getEditProductPage(driver);
    }

}
