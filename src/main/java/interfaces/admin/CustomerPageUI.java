package interfaces.admin;

public class CustomerPageUI {
    public static final String GENDER_SELECTION_RADIO_BUTTON = "//label[@for='Gender_%s']//preceding-sibling::input";
    public static final String ROLE_SELECTION_CUSTOM_DROPDOWN_LIST_PARENT = "//ul[@id='SelectedCustomerRoleIds_taglist']//following-sibling::input";
    public static final String ROLE_SELECTION_CUSTOM_DROPDOWN_LIST_CHILD = "//*[@id='SelectedCustomerRoleIds_listbox']//li[text()='%s']";
    public static final String CUSTOMER_CREATION_SUCCESS_MESSAGE = "//div[@class='alert alert-success alert-dismissable' " +
            "and contains(.,'The new customer has been added successfully.')]";
    public static final String CURRENT_ROLE_SELECTION_TEXTBOX_BY_ROLE_NAME = "//ul[@id='SelectedCustomerRoleIds_taglist']//li[normalize-space()='%s']";
    public static final String ACTIVE_CHECK_BOX = "//*[@id='Active']";
    public static final String DELETE_ROLE_BUTTON_BY_ROLE_NAME = "//ul[@id='SelectedCustomerRoleIds_taglist']//span[normalize-space()='%s']//following-sibling::span";
    public static final String GENDER_RADIO_BUTTON_SELECTED_BY_GENDER = "//label[normalize-space()='%s']//preceding-sibling::input";
    public static final String CUSTOMER_TABLE_FULL_NAME = "//table[@id='customers-grid']//tbody//tr//td[text()='%s']";
    public static final String CUSTOMER_TABLE_CUSTOMER_ROLE_BY_FULL_NAME  = "//table[@id='customers-grid']" +
            "//tbody//tr//td[text()='%s']//following-sibling::td[1]";
    public static final String CUSTOMER_TABLE_CUSTOMER_COMPANY_BY_FULL_NAME  = "//table[@id='customers-grid']" +
            "//tbody//tr//td[text()='%s']//following-sibling::td[2]";
    public static final String CUSTOMER_TABLE_GRID_INFO = "//div[@id='customers-grid_info']";
    public static final String CUSTOMER_TABLE_EDIT_BUTTON_BY_FULL_NAME = "//table[@id='customers-grid']" +
            "//tbody//tr//td[text()='%s']//following-sibling::td[4]/a";
    public static final String CUSTOMER_UPDATED_SUCCESSFULLY_MESSAGE = "//div[@class='alert alert-success alert-dismissable' " +
            "and contains(.,'The customer has been updated successfully.')]";

    public static final String CUSTOMER_ADDRESS_TABLE_EDIT_BUTTON_BY_EMAIL = "//div[@id='customer-address']" +
            "//td[normalize-space()='%s']//following-sibling::td[4]/a";
        public static final String CUSTOMER_ADDRESS_TABLE_BODY = "//div[@id='customer-address']//tbody";
    public static final String CUSTOMER_ADDRESS_TABLE_DELETE_BUTTON_BY_EMAIL = "//div[@id='customer-address']" +
            "//td[normalize-space()='%s']//following-sibling::td[5]/a";
    public static final String CUSTOMER_ADDRESS_TABLE_EMPTY_DATA_TEXT = "//table[@id='customer-addresses-grid']" +
            "//td[normalize-space()='No data available in table']";
    public static final String ADD_NEW_BUTTON = "//a[@class='btn btn-primary' and normalize-space()='Add new']";
}
