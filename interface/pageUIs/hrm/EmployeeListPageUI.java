package pageUIs.hrm;

public class EmployeeListPageUI {
	public static final String SIDE_BAR_LINK = "xpath=//div[@id='sidebar']//a[text()='%s']";
	public static final String COLUMN_INDEX = "xpath=//table[@id='tblSalary']//tr//th[string()='Amount']/preceding-sibling::th[@class]";
	public static final String AMOUNT_VALUE_IN_SALARY_TABLE = "xpath=//table[@id='tblSalary']/tbody/tr[%s]/td[%s]";
}
