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

	public void clickToJobFormToDisappearDateTimePicker() {
		clickToElement(driver, EmployeeListPageUI.JOB_FORM);
	}
}
