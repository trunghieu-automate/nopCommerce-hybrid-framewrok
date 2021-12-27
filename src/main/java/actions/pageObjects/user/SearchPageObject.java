package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.SearchPageUI;
import org.openqa.selenium.WebDriver;

public class SearchPageObject extends BasePage {
    public SearchPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickToSearchButton() {
        clickToElement(SearchPageUI.SEARCH_PAGE_SEARCH_BUTTON);
    }

    public Object getSearchResultText() {
        return getElementText(SearchPageUI.SEARCH_PAGE_RESULT);
    }


    public void setCheckboxStatusById(String status, String checkboxID) {
        boolean checked = isCheckboxChecked(getDynamicXpath(SearchPageUI.SEARCH_PAGE_CHECKBOX_BY_ID, checkboxID));
        if(!checked && (status.equalsIgnoreCase("checked") || status.equalsIgnoreCase("check"))){
            clickToElement(getDynamicXpath(SearchPageUI.SEARCH_PAGE_CHECKBOX_BY_ID, checkboxID));
        } else if(checked && (status.equalsIgnoreCase("unchecked") || status.equalsIgnoreCase("uncheck"))){
            clickToElement(getDynamicXpath(SearchPageUI.SEARCH_PAGE_CHECKBOX_BY_ID, checkboxID));
        }
    }


    public int getNumberOfResultItemWithText(String compareText) {
        return waitElementsVisible(getDynamicXpath(SearchPageUI.SEARCH_PAGE_RESULT_PRODUCT_CONTAINS_NAME, compareText.toLowerCase())).size();
    }
}
