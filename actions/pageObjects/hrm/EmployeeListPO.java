package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;

public class EmployeeListPO extends BagePage {
	private WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}
}
