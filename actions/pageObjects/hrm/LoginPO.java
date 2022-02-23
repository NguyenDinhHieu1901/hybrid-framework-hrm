package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;
import pageUIs.hrm.LoginPageUI;

public class LoginPO extends BagePage {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public DashboardPO loginToSystem(String userNameAdmin, String passwordAdmin) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userNameAdmin);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordAdmin);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.gerDashboardPage(driver);
	}
}
