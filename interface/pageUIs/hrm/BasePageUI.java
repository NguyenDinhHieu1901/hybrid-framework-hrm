package pageUIs.hrm;

public class BasePageUI {
	public static final String MENU_PAGE_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']";
	public static final String SUB_MENU_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']/following-sibling::ul//a[text()='%s']";
	public static final String CHILD_SUB_MENU_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']/following-sibling::ul//a[text()='%s']/parent::li//a[text()='%s']";
	public static final String BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String CHECKBOX_BY_LABEL_TEXT = "xpath=//label[text()='%s']/following-sibling::input";
	public static final String RADIO_BUTTON_BY_LABEL_TEXT = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String INDEX_COLUMN_BY_TABLE_NAME_ADN_HEADER_NAME = "xpath=//table[@id='%s']//tr//th[string()='%s']/preceding-sibling::th";
	public static final String VALUE_DATA_TABLE_BY_TABLE_NAME_ROW_INDEX_ADN_COLUMN_INDEX = "xpath=//table[@id='%s']/tbody/tr[%s]/td[%s]";
	public static final String LINK_VALUE_DATA_TABLE_BY_TABLE_NAME_ROW_INDEX_ADN_COLUMN_INDEX = "xpath=//table[@id='%s']/tbody/tr[%s]/td[%s]/a";
	public static final String SUCCESS_MESSAGE_BY_TEXT = "xpath=//div[@class='message success fadable' and contains(text(),'%s')]";
	public static final String ANY_FIELDS_BY_ID = "xpath=//*[@id='%s']";

	public static final String WELCOME_USER = "xpath=//a[@id='welcome']";
	public static final String LOGOUT_LINK = "xpath=//div[@id='welcome-menu']//a[text()='Logout']";
	public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
}
