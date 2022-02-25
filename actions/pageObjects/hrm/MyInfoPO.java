package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;
import io.qameta.allure.Step;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPO extends BagePage {
	private WebDriver driver;

	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Employee Photo")
	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, MyInfoPageUI.EMPLOYEE_PHOTO);
		clickToElement(driver, MyInfoPageUI.EMPLOYEE_PHOTO);
	}

	@Step("Verify Image is uploaded success")
	public boolean isImageUploadedSuccess() {
		String width = getElementAttribute(driver, MyInfoPageUI.EMPLOYEE_PHOTO, "width");
		String height = getElementAttribute(driver, MyInfoPageUI.EMPLOYEE_PHOTO, "height");
		return (width != "200") || (height != "200");
	}

	@Step("Open {0} link")
	public void openSidebarLinkByName(String formName) {
		waitForElementClickable(driver, MyInfoPageUI.SIDE_BAR_LINK, formName);
		clickToElement(driver, MyInfoPageUI.SIDE_BAR_LINK, formName);
	}

	public boolean isUploadedSuccessMessageDisplayed(String successMessage) {
		waitForElementVisible(driver, MyInfoPageUI.UPLOAED_SUCCESS_MESSAGE);
		return getElementText(driver, MyInfoPageUI.UPLOAED_SUCCESS_MESSAGE).contains(successMessage);
	}

}
