package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;
import io.qameta.allure.Step;
import pageUIs.hrm.LoginPageUI;

public class LoginPO extends BagePage {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Login to system with username & password: {0} & {1}")
	public DashboardPO loginToSystem(String userName, String password) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		isJQueryAjaxLoadedSuccess(driver);
		return PageGeneratorManager.gerDashboardPage(driver);
	}
}
