package pageUIs.hrm;

public class BasePageUI {
	public static final String MENU_PAGE_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']";
	public static final String SUB_MENU_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']/following-sibling::ul//a[text()='%s']";
	public static final String CHILD_SUB_MENU_BY_TEXT = "xpath=//div[@id='mainMenu']//a[string()='%s']/following-sibling::ul//a[text()='%s']/parent::li//a[text()='%s']";
	public static final String BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String CHECKBOX_BY_LABEL_TEXT = "xpath=//label[text()='%s']/following-sibling::input";
	public static final String DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String INDEX_COLUMN_BY_TABLE_NAME_ADN_HEADER_NAME = "xpath=//table[@id='%s']//tr//th[string()='%s']/preceding-sibling::th";
	public static final String VALUE_DATA_TABLE_BY_TABLE_NAME_ROW_INDEX_ADN_COLUMN_INDEX = "xpath=//table[@id='%s']//tbody//tr[%s]//td[%s]";
}
