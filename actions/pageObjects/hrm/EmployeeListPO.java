package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;
import io.qameta.allure.Step;
import pageUIs.hrm.EmployeeListPageUI;

public class EmployeeListPO extends BagePage {
	private WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Open Side bar link with {0} page")
	public void openSidebarLinkByName(String pageName) {
		waitForElementClickable(driver, EmployeeListPageUI.SIDE_BAR_LINK, pageName);
		clickToElement(driver, EmployeeListPageUI.SIDE_BAR_LINK, pageName);
	}

	// @Step("Click to Job form to disappear Date Time Picker")
	// public void clickToJobFormToDisappearDateTimePicker() {
	// clickToElement(driver, EmployeeListPageUI.JOB_FORM);
	// }

	// @Step("Input to comments textare with comments: {0}")
	// public void inputToCommentsTextarea(String comments) {
	// waitForElementVisible(driver, EmployeeListPageUI.COMMENTS_TEXTAREA);
	// sendkeyToElement(driver, EmployeeListPageUI.COMMENTS_TEXTAREA, comments);
	// }

	@Step("Get Amount Value in Salary Table")
	public String getAmountValueInSalaryTableByRowIndex(String rowIndex) {
		int columnIndex = getElementSize(driver, EmployeeListPageUI.COLUMN_INDEX) + 1;
		waitForElementVisible(driver, EmployeeListPageUI.AMOUNT_VALUE_IN_SALARY_TABLE, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, EmployeeListPageUI.AMOUNT_VALUE_IN_SALARY_TABLE, rowIndex, String.valueOf(columnIndex));
	}
}
