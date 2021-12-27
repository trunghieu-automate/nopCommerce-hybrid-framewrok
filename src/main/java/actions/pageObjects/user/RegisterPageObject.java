package actions.pageObjects.user;

import actions.common.BasePage;
import interfaces.user.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    public RegisterPageObject(WebDriver driver) {
        super(driver);
    }

    public String getRegisterSuccessMsg() {
        return getElementText(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getRegisteredEmailMessage() {
        return getElementText(RegisterPageUI.REGISTERED_EMAIL_ERROR_MESSAGE);
    }
}
