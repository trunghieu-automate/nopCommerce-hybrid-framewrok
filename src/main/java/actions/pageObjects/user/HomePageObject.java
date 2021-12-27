package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.BasePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageSliderIsDisplayed() {
        try {
            waitElementsVisible(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, "nivo-slider"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutButtonOnTopNavBarDisplayed() {
        try {
            waitElementsVisible(getDynamicXpath(BasePageUI.TOP_NAV_BAR_MENU_BY_NAME, "logout"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}