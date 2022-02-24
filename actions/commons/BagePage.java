package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;
import pageUIs.hrm.BasePageUI;

public class BagePage {

	protected BagePage() {
		log = Logger.getLogger(getClass());
	}

	protected void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver).accept();
	}

	protected void cancleAlert(WebDriver driver) {
		waitAlertPresence(driver).dismiss();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitAlertPresence(driver).sendKeys(textValue);
	}

	protected String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	protected void switchWindowByID(WebDriver driver, String oppositeParentWindow) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			if (!windowID.equals(oppositeParentWindow)) {
				driver.switchTo().window(windowID);
				sleepInSecond(1);
			}
		}
	}

	protected void switchWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			sleepInSecond(1);
			String actualTitle = getPageTitle(driver);
			if (actualTitle.trim().equals(expectedTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowWithoutParent(WebDriver driver, String parentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			sleepInSecond(1);
			if (!windowID.equals(parentWindowID)) {
				driver.close();
			}
		}
		switchWindowByID(driver, parentWindowID);
	}

	protected void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("xpath=") | locatorType.startsWith("Xpath=") | locatorType.startsWith("XPATH") | locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") | locatorType.startsWith("Css=") | locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("id=") | locatorType.startsWith("Id=") | locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("name=") | locatorType.startsWith("Name=") | locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("class=") | locatorType.startsWith("Class=") | locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else {
			throw new RuntimeException("This locator is invalid. Please check again the syntax!");
		}
		return by;
	}

	private String getDynamicLocator(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") | locatorType.startsWith("Xpath=") | locatorType.startsWith("XPATH") | locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	private List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendkeyToElementWithoutClear(WebDriver driver, String locatorType, String textValue) {
		getWebElement(driver, locatorType).sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).getText();
	}

	protected List<String> getAllElementText(WebDriver driver, String locatorType) {
		List<String> allElementText = new ArrayList<String>();
		List<WebElement> allElement = getListWebElement(driver, locatorType);
		for (WebElement element : allElement) {
			allElementText.add(element.getText());
		}
		return allElementText;
	}

	protected List<String> getAllElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		List<String> allElementText = new ArrayList<String>();
		List<WebElement> allElement = getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		for (WebElement element : allElement) {
			allElementText.add(element.getText());
		}
		return allElementText;
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).size();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String itemText) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(itemText);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String itemText, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
		select.selectByVisibleText(itemText);
	}

	protected String getFirstSelectedItemInDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getFirstSelectedItemInDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedText) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator)));
		getWebElement(driver, parentLocator).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		List<WebElement> allElements = getListWebElement(driver, childLocator);
		for (WebElement element : allElements) {
			String actualText = element.getText().trim();
			if (actualText.equals(expectedText)) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
					element.click();
				}
				break;
			}
		}
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType, String... dynamicValues) {
		driver.switchTo().frame(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues))).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, String key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, String key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	protected void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)));
	}

	protected void removeAttributeOfElementByJS(WebDriver driver, String locatorType, String attributeName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", getWebElement(driver, locatorType));
	}

	protected void removeAttributeOfElementByJS(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", getDynamicLocator(locatorType, dynamicValues));
	}

	protected void highlightElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = (String) jsExecutor.executeScript("arguments[0].getAttribute(arguments[1]);", getWebElement(driver, locatorType), "style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(driver, locatorType), "style", "border: 3px solid red");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(driver, locatorType), "style", originalStyle);
	}

	protected void highlightElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = (String) jsExecutor.executeScript("arguments[0].getAttribute(arguments[1]);", getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)), "style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)), "style", "border: 3px solid red");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(driver, getDynamicLocator(locatorType, dynamicValues)), "style", originalStyle);
	}

	protected boolean checkImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean statusImage = (boolean) jsExecutor.executeScript("return arguments[0].complete && arguments[0].naturalWidth >  0 && arguments[0] != 'underfined';", getWebElement(driver, locatorType));
		if (statusImage == true) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean checkImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean statusImage = (boolean) jsExecutor.executeScript("return arguments[0].complete && arguments[0].naturalWidth >  0 && arguments[0] != 'underfined';", getDynamicLocator(locatorType, dynamicValues));
		if (statusImage == true) {
			return true;
		} else {
			return false;
		}
	}

	protected String getValidationHTML5ByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	protected String getValidationHTML5ByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getDynamicLocator(locatorType, dynamicValues));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	private void overridingGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overridingGlobalTimeout(driver, shortTimeout);
		List<WebElement> listElement = getListWebElement(driver, locatorType);
		overridingGlobalTimeout(driver, longTimeout);
		if (listElement.size() == 0) {
			log.info("Element is invisible and not in DOM");
			return true;
		} else if (listElement.size() > 0 && !listElement.get(0).isDisplayed()) {
			log.info("Element is invisible and in DOM");
			return true;
		} else {
			log.info("Element is invisible");
			return false;
		}
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overridingGlobalTimeout(driver, shortTimeout);
		List<WebElement> listElement = getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues));
		overridingGlobalTimeout(driver, longTimeout);
		if (listElement.size() == 0) {
			log.info("Element is invisible and not in DOM");
			return true;
		} else if (listElement.size() > 0 && !listElement.get(0).isDisplayed()) {
			log.info("Element is invisible and in DOM");
			return true;
		} else {
			log.info("Element is invisible");
			return false;
		}
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicLocator(locatorType, dynamicValues))));
	}

	@Step("Open Menu page with Menu: {1}")
	public void openMenuPage(WebDriver driver, String menuName) {
		waitForElementClickable(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		clickToElement(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		isJQueryAjaxLoadedSuccess(driver);
	}

	@Step("Open Sub Menu page with Sub Menu: {2}")
	public void openSubMenuPage(WebDriver driver, String menuName, String subMenuName) {
		waitForElementClickable(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		clickToElement(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		waitForElementClickable(driver, BasePageUI.SUB_MENU_BY_TEXT, menuName, subMenuName);
		clickToElement(driver, BasePageUI.SUB_MENU_BY_TEXT, menuName, subMenuName);
		isJQueryAjaxLoadedSuccess(driver);
	}

	@Step("Open Child Sub Menu page with Child Sub Menu: {3}")
	public void openChildSubMenuPage(WebDriver driver, String menuName, String subMenuName, String childSubMenu) {
		waitForElementClickable(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		clickToElement(driver, BasePageUI.MENU_PAGE_BY_TEXT, menuName);
		waitForElementVisible(driver, BasePageUI.SUB_MENU_BY_TEXT, menuName, subMenuName);
		hoverMouseToElement(driver, BasePageUI.SUB_MENU_BY_TEXT, menuName, subMenuName);
		clickToElement(driver, BasePageUI.CHILD_SUB_MENU_BY_TEXT, menuName, subMenuName, childSubMenu);
		isJQueryAjaxLoadedSuccess(driver);
	}

	@Step("Click to Button with ID: {1}")
	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonID);
	}

	@Step("Input to textbox with ID: {1} and value: {2}")
	public void inputToTextboxByID(WebDriver driver, String textboxID, String textValue) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, textValue, textboxID);
	}

	@Step("Get text value of {1} with Attribute name: {2}")
	public String getTextValueByAttribute(WebDriver driver, String textboxID, String attributeName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, attributeName, textboxID);
	}

	@Step("Click to Checkbox with Label name: {1}")
	public void checkToDefaultCheckboxByLabel(WebDriver driver, String labelText) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL_TEXT, labelText);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL_TEXT, labelText);
	}

	@Step("Click to Radio button with Label name: {1}")
	public void checkToRadioButtonByLabel(WebDriver driver, String labelText) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL_TEXT, labelText);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.RADIO_BUTTON_BY_LABEL_TEXT, labelText);
	}

	@Step("Select item in Default Dropdown with value: {2}")
	public void selectItemInDefaultDropdownByName(WebDriver driver, String dropdownName, String itemValue) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, itemValue, dropdownName);
	}

	@Step("Get first selected item in dropdown {1}")
	public String getItemSelectedInDefaultDropdownByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
		return getFirstSelectedItemInDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
	}

	@Step("Get value text in Data Table")
	public String getValueTextInDataTableByRowAndColumnIndex(WebDriver driver, String tableName, String rowIndex, String headerTable) {
		int columnIndex = getElementSize(driver, BasePageUI.INDEX_COLUMN_BY_TABLE_NAME_ADN_HEADER_NAME, tableName, headerTable) + 1;
		waitForElementVisible(driver, BasePageUI.VALUE_DATA_TABLE_BY_TABLE_NAME_ROW_INDEX_ADN_COLUMN_INDEX, tableName, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.VALUE_DATA_TABLE_BY_TABLE_NAME_ROW_INDEX_ADN_COLUMN_INDEX, tableName, rowIndex, String.valueOf(columnIndex));
	}

	@Step("Logout to System")
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER);
		clickToElement(driver, BasePageUI.WELCOME_USER);
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	@Step("Upload file with path: {1}")
	public void uploadEmployeeImage(WebDriver driver, String pathAvatarFile) {
		waitForElementVisible(driver, BasePageUI.UPLOAD_FILE);
		sendkeyToElementWithoutClear(driver, BasePageUI.UPLOAD_FILE, pathAvatarFile);
	}

	@Step("Verify Success message is displayed with message: {1}")
	public boolean isSuccessMessageDisplayed(WebDriver driver, String successMessage) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT, successMessage);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT, successMessage);
	}

	@Step("Verify is field {1} enabled")
	public boolean isAnyFieldsEnabledByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELDS_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELDS_BY_ID, fieldID);
	}

	@Step("Verify is Radio button selected with label {1}")
	public boolean isRadioButtonSelected(WebDriver driver, String labelText) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_LABEL_TEXT, labelText);
		return isElementSelected(driver, BasePageUI.RADIO_BUTTON_BY_LABEL_TEXT, labelText);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private final Logger log;
}
