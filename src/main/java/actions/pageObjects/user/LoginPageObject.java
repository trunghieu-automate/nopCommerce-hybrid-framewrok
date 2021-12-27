package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public String getSummaryLoginErrorMessage() {
        return getElementText(LoginPageUI.SUMMARY_LOGIN_ERROR_MESSAGE);
    }
}
