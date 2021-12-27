package actions.pageObjects.admin;

import actions.common.BasePage;
import interfaces.admin.CustomerPageUI;
import interfaces.user.BasePageUI;
import org.openqa.selenium.WebDriver;
import utilities.logs.Log;

public class CustomerPageObject extends BasePage {

    public CustomerPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickToGenderRadioButton(String gender) {
        checkRadioBtn(getDynamicXpath(CustomerPageUI.GENDER_SELECTION_RADIO_BUTTON, gender));
    }

    public void selectRoleDropdownList(String roleName) {
        String parent = CustomerPageUI.ROLE_SELECTION_CUSTOM_DROPDOWN_LIST_PARENT;
        String child = getDynamicXpath(CustomerPageUI.ROLE_SELECTION_CUSTOM_DROPDOWN_LIST_CHILD, roleName);
        clickToElement(parent);
        clickToElement(child);
    }

    public boolean isCustomerCreationSuccessfullyAlertDisplayed() {
        return isElementsDisplayed(CustomerPageUI.CUSTOMER_CREATION_SUCCESS_MESSAGE);
    }

    public String getTextboxValueById(String textBoxName) {
        return getElementAttribute(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, textBoxName), "value");
    }

    public boolean isCustomerRoleDisplayed(String roleName) {
        return isElementsDisplayed(getDynamicXpath(CustomerPageUI.CURRENT_ROLE_SELECTION_TEXTBOX_BY_ROLE_NAME, roleName));
    }

    public boolean isGenderRadioButtonSelected(String gender) {
        return isRadioChecked(getDynamicXpath(CustomerPageUI.GENDER_RADIO_BUTTON_SELECTED_BY_GENDER, gender));
    }

    public boolean isActiveCheckboxChecked() {
        return isCheckboxChecked(CustomerPageUI.ACTIVE_CHECK_BOX);
    }

    public String getTextAreaValueById(String textAreaName) {
        return getElementText(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, textAreaName));
    }

    public void clickToRoleDeleteButtonByName(String roleName) {
        jsClickToElement(getDynamicXpath(CustomerPageUI.DELETE_ROLE_BUTTON_BY_ROLE_NAME, roleName));
    }


    public boolean isCustomerNameInCustomerTableDisplayed(String fullName) {
        return isElementsDisplayed(getDynamicXpath(CustomerPageUI.CUSTOMER_TABLE_FULL_NAME, fullName));
    }

    public String getCustomerRoleInCustomerTableByFullName(String email) {
        return getElementText(getDynamicXpath(CustomerPageUI.CUSTOMER_TABLE_CUSTOMER_ROLE_BY_FULL_NAME, email));
    }

    public String getCustomerCompanyInCustomerTableByFullName(String email) {
        return getElementText(getDynamicXpath(CustomerPageUI.CUSTOMER_TABLE_CUSTOMER_COMPANY_BY_FULL_NAME, email));
    }

    public int getNumberOfItemDisplayedAtGridInfo() {
        String rawText = getElementText(CustomerPageUI.CUSTOMER_TABLE_GRID_INFO);
        return Integer.parseInt(rawText.substring(rawText.indexOf("of")).replaceAll("\\D", ""));
    }

    public void clickToEditButtonByCustomerFullName(String fullName) {
        clickToElement(getDynamicXpath(CustomerPageUI.CUSTOMER_TABLE_EDIT_BUTTON_BY_FULL_NAME, fullName));
    }

    public boolean isCustomerUpdatedSuccessfullyAlertDisplayed() {
        return isElementsDisplayed(CustomerPageUI.CUSTOMER_UPDATED_SUCCESSFULLY_MESSAGE);
    }

    public String getDropdownListOptionById(String dropdownListId) {
        return getCurrentOptionTextDefaultDropDownList(getDynamicXpath(BasePageUI.ELEMENT_BY_ID, dropdownListId));
    }

    public void clickEditButtonInAddressInfoCardByEmail(String email) {
        clickToElement(getDynamicXpath(CustomerPageUI.CUSTOMER_ADDRESS_TABLE_EDIT_BUTTON_BY_EMAIL, email));
    }

    public void clickDeleteButtonInAddressInfoCardByEmail(String email) {
        for (int i = 0; i <=20; i++){
            if(getElementText(CustomerPageUI.CUSTOMER_ADDRESS_TABLE_BODY).contains(email)){
                try {
                    jsClickToElementSimply(getDynamicXpath(CustomerPageUI.CUSTOMER_ADDRESS_TABLE_DELETE_BUTTON_BY_EMAIL, email));
                    acceptAlert();
                    sleepInSecond(1);
                } catch (Exception e) {
                        Log.info("Something wrong, try again");
                }
            }
        }
    }

    public boolean isAddressTableDataIsEmptyWithTextDisplayed() {
        return isElementsDisplayed(CustomerPageUI.CUSTOMER_ADDRESS_TABLE_EMPTY_DATA_TEXT);
    }

    public void clickToAddNewButton() {
        clickToElement(CustomerPageUI.ADD_NEW_BUTTON);
    }
}
