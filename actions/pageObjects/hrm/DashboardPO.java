package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;

public class DashboardPO extends BagePage {
	private WebDriver driver;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
	}
}
