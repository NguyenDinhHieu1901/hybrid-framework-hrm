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
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, LoginPageUI.LOGIN_BUTTON);
		} else {
			clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
			sleepInSecond(2);
		}
		isJQueryAjaxLoadedSuccess(driver);
		return PageGeneratorManager.getDashboardPage(driver);
	}
}
