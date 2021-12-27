package actions.common;

import actions.pageObjects.user.HomePageObject;
import actions.pageObjects.user.PageGeneratorManager;
import actions.pageObjects.user.ProductPageObject;
import interfaces.admin.CustomerPageUI;
import interfaces.user.BasePageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor jsExecutor;
    public Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Constants.LONG_TIME_OUT);
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public void navigate(String direction) {
        switch (direction) {
            case "back":
                driver.navigate().back();
            case "forward":
                driver.navigate().forward();
        }
    }

    // dynamic xpath
    public String getDynamicXpath(String xpathLocator, String... params) {
        return String.format(xpathLocator, (Object[]) params);
    }

    //wait element visible
    public WebElement waitElementVisible(String xpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            return waitElementsVisible(xpath).get(0);
        }
    }

    // wait and find elements
    public List<WebElement> waitElementsVisible(String xpath) {
        List<WebElement> elements = new ArrayList<>();
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpath))));
        } catch (Exception e) {
            return elements;
        }
    }

    public List<WebElement> waitElementsVisible(String xpath, long overrideTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, overrideTimeout);
        List<WebElement> elements = new ArrayList<>();
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpath))));
        } catch (Exception e) {
            return elements;
        }
    }

    // wait element in DOM
    public WebElement waitElementPresence(String xpath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public List<WebElement> waitElementsPresence(String xpath) {
        WebDriverWait quickWait = new WebDriverWait(driver, 5);
        return quickWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    // Alert
    public Alert getAlert(){
        WebDriverWait waitAlert = new WebDriverWait(driver, 3);
        return waitAlert.until(ExpectedConditions.alertIsPresent());
    }

    public void closeAlert(){
        getAlert().dismiss();
    }

    public void acceptAlert(){
        getAlert().accept();
    }



    //action
    public void hoverToElement(String xpath) {
        action.moveToElement(waitElementVisible(xpath)).perform();
    }

    // click
    public void clickToElement(String xpath) {
        waitElementVisible(xpath).click();
        areJQueryAndJSLoadedSuccess(5);
    }

    // click to checkbox
    public void checkToCheckbox(String xpath) {
        if (!isCheckboxChecked(xpath)) {
            clickToElement(xpath);
        }
    }

    // sendKeys
    public void sendKeysToElement(String xpath, String expectedText) {
        waitElementVisible(xpath).clear();
        waitElementVisible(xpath).sendKeys(expectedText);
    }

    // Select
    public void selectItemDefaultDropdownListByName(String xpath, String itemName) {
        Select select = new Select(waitElementVisible(xpath));
        try {
            areJQueryAndJSLoadedSuccess(3);
            select.selectByVisibleText(itemName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get firstOption
    public String getCurrentOptionTextDefaultDropDownList(String xpath) {
        Select select = new Select(waitElementVisible(xpath));
        return select.getFirstSelectedOption().getText();
    }

    // get Text
    public String getElementText(String xpath) {
        return waitElementVisible(xpath).getText();
    }

    // get attribute by name
    public String getElementAttribute(String xpath, String attributeName) {
        return waitElementVisible(xpath).getAttribute(attributeName);
    }

    // get css value
    public String getElementCssValue(String xpath, String cssAttributeName) {
        return waitElementVisible(xpath).getCssValue(cssAttributeName);
    }

    public void checkRadioBtn(String xpath) {
        WebElement radioBtn = waitElementVisible(xpath);
        if (!radioBtn.isSelected()) {
            radioBtn.click();
        }
    }

    // check status
    public boolean isRadioChecked(String xpath) {
        return waitElementVisible(xpath).isSelected();
    }

    public boolean isCheckboxChecked(String xpath) {
        return waitElementVisible(xpath).isSelected();
    }

    public boolean isElementsDisplayed(String xpath) {
        List<WebElement> elements = waitElementsVisible(xpath);
        boolean result = false;
        if (elements.size() != 0) {
            for (WebElement element : elements) {
                result = element.isDisplayed();
            }
        }
        return result;
    }


    // js injection
    public void jsClickToElement(String xpath) {
        jsExecutor.executeScript("arguments[0].click()", waitElementPresence(xpath));
        areJQueryAndJSLoadedSuccess(3);
    }
    public void jsClickToElementSimply(String xpath) {
        jsExecutor.executeScript("arguments[0].click()", waitElementPresence(xpath));
    }

    public void removeAttribute(String xpath, String attributeName) {
        jsExecutor.executeScript("arguments[0].removeAttribute(" + attributeName + ")", waitElementVisible(xpath));
    }

    public void setAttribute(String xpath, String attributeName, String attrValue) {
        jsExecutor.executeScript("arguments[0].setAttribute(" + attributeName + "," + attrValue + ")", waitElementVisible(xpath));
    }

    public boolean areJQueryAndJSLoadedSuccess(long timeout) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait explicitWait = new WebDriverWait(driver, timeout);

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    // Thread sleep
    public void sleepInSecond(long sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // nopcomerce specific method
    public BasePage clickToTopNavBar(String menuName) {
        jsClickToElement(getDynamicXpath(BasePageUI.TOP_NAV_BAR_MENU_BY_NAME, menuName));
        try {
            switch (menuName) {
                case "register":
                    return PageGeneratorManager.getRegisterPage(driver);
                case "logout":
                    return PageGeneratorManager.getHomePage(driver);
                case "login":
                    return PageGeneratorManager.getLoginPage(driver);
                case "account":
                    return PageGeneratorManager.getMyAccountPage(driver);
                case "wishlist":
                    return PageGeneratorManager.getWishlistPage(driver);
                case "cart":
                    return PageGeneratorManager.getCartPage(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PageGeneratorManager.getHomePage(driver);
    }

    public void clickToElementById(String elementId) {
        clickToElement(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, elementId));
    }

    public void sendKeysToElementById(String elementId, String expectedText) {
        sendKeysToElement(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, elementId), expectedText);
    }

    public void clickToButtonByTextName(String buttonTextName) {
        clickToElement(getDynamicXpath(BasePageUI.BUTTON_BY_TEXT_NAME, buttonTextName));
        areJQueryAndJSLoadedSuccess(5);
    }

    public String getErrorMessageByFieldID(String fieldID) {
        return getElementText(getDynamicXpath(BasePageUI.VALIDATION_ERROR_MESSAGE_BY_FIELD_ID, fieldID));
    }

    public BasePage clickToFooterLinkByTextName(String footerMenu) {
        clickToElement(getDynamicXpath(BasePageUI.FOOTER_MENU_LINK_BY_TEXT_NAME, footerMenu));
        try {
            switch (footerMenu.toLowerCase()) {
                case "my account":
                    return PageGeneratorManager.getMyAccountPage(driver);
                case "search":
                    return PageGeneratorManager.getSearchPage(driver);
                case "compare products list":
                    return PageGeneratorManager.getCompareProductPage(driver);
                case "recently viewed products":
                    return PageGeneratorManager.getRecentViewPage(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void selectDropDownListById(String dropdownId, String optionText) {
        if (!getCurrentOptionTextDefaultDropDownList(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, dropdownId)).equalsIgnoreCase(optionText)) {
            selectItemDefaultDropdownListByName(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, dropdownId), optionText);
        }
    }

    public void clickToLinkTagByTextName(String textName) {
        clickToElement(getDynamicXpath(BasePageUI.LINK_BY_TEXT_NAME, textName));
    }

    public HomePageObject clickToHeaderLogoImg() {
        clickToElement(BasePageUI.HEADER_LOGO_IMG);
        return PageGeneratorManager.getHomePage(driver);
    }

    public ProductPageObject clickToProductMenuBarByTextName(String menuName) {
        jsClickToElement(getDynamicXpath(BasePageUI.PRODUCT_MENU_BAR_LINK, menuName));
        return PageGeneratorManager.getProductPage(driver);
    }

    public void clickToCheckBoxById(String checkboxId) {
        checkToCheckbox(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, checkboxId));
    }

    public void hoverToTopNavBarMenu(String menuName) {
        hoverToElement(getDynamicXpath(BasePageUI.TOP_NAV_BAR_MENU_BY_NAME, "cart"));
    }

    public String getNumberOfItemTextInFlyoutCart() {
        return getElementText(BasePageUI.FLYOUT_CART_NUMBER_OF_ITEM);
    }

    public boolean isPriceInFlyoutCartByProductName(String productName, String unitPrice) {
        return getElementText(getDynamicXpath(BasePageUI.FLYOUT_CART_ITEM_PRICE_BY_PRODUCT_NAME, productName)).contains(unitPrice);
    }

    public boolean isQuantityInFlyoutCartByProductName(String productName, String quantity) {
        return getElementText(getDynamicXpath(BasePageUI.FLYOUT_CART_ITEM_QUANTITY_BY_PRODUCT_NAME, productName)).contains(quantity);
    }

    public boolean isSubTotalInfoInFlyoutCartByProductName(String productName, String subTotal) {
        return getElementText(getDynamicXpath(BasePageUI.FLYOUT_CART_ITEM_SUBTOTAL, productName)).contains(subTotal);
    }

    public boolean isShoppingCartNumberEqualTo(String numberOfItem) {
        return getElementText(BasePageUI.SHOPPING_CART_NUMBER_OF_ITEM).replaceAll("[()]", "").equals(numberOfItem);
    }

    public boolean isProductContainsInFlyoutCartByProductName(String... productNames) {
        boolean result = false;
        List<WebElement> elements = waitElementsVisible(BasePageUI.FLYOUT_CART_CONTAINS_PRODUCT_NAME, 3);
        if (elements.size() != 0) {
            for (WebElement element : elements) {
                for (String productName : productNames) {
                    result = element.getText().equalsIgnoreCase(productName);
                }
            }
        } return result;
    }

    public boolean isNoItemTextMessageInFlyoutCart() {
        return isElementsDisplayed(BasePageUI.FLYOUT_CART_NO_ITEM_MESSAGE);
    }

    public boolean isNumberInNumberOfItemsInFlyoutCartEqualTo(String numberOfItems) {
        return getElementText(BasePageUI.FLYOUT_CART_NUMBER_OF_ITEM).replaceAll("\\D", "").equals(numberOfItems);
    }


    // Login page function
    public void clickToLeftNavBarByMenuName(String... menuFLow) {
        String rotated = "matrix(6.12323e-17, -1, 1, 6.12323e-17, 0, 0)";
        if (menuFLow.length == 2){
            String firstMenuIcon = getDynamicXpath(BasePageUI.ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_FIRST_MENU_ICON, menuFLow);
            String secondMenu = getDynamicXpath(BasePageUI.ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_SECOND_MENU, menuFLow);
            if(!getElementCssValue(firstMenuIcon, "transform").equalsIgnoreCase(rotated)){
                clickToElement(firstMenuIcon);
                sleepInSecond(1);
            }
            clickToElement(secondMenu);
        } else if (menuFLow.length == 3) {
            String firstMenuIcon = getDynamicXpath(BasePageUI.ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_FIRST_MENU_ICON, menuFLow);
            String secondMenuIcon = getDynamicXpath(BasePageUI.ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_SECOND_MENU_ICON, menuFLow);
            String thirdMenu = getDynamicXpath(BasePageUI.ADMIN_PAGE_LOGIN_LEFT_NAV_BAR_THIRD_MENU, menuFLow);
            if(!getElementCssValue(firstMenuIcon, "transform").equalsIgnoreCase(rotated)){
                clickToElement(firstMenuIcon);
                sleepInSecond(1);
                clickToElement(secondMenuIcon);
            } else if( getElementCssValue(firstMenuIcon, "transform").equalsIgnoreCase(rotated) &&
                    !getElementCssValue(secondMenuIcon, "transform").equalsIgnoreCase(rotated)){
                clickToElement(secondMenuIcon);
                sleepInSecond(1);
            }
            clickToElement(thirdMenu);
        }
    }

    public void clickToCardById(String cardId) {
        String icon = getDynamicXpath(BasePageUI.CARD_COLLAPSED_BUTTON_ICON, cardId);
        String collapseButton = getDynamicXpath(BasePageUI.CARD_INFO_COLLAPSED_BUTTON_BY_ID, cardId);
        if(!getElementAttribute(icon, "class").contains("fa-minus")){
            clickToElement(collapseButton);
        }
    }
}
