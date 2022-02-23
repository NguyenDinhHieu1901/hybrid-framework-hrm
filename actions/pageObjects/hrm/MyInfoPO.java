package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BagePage;

public class PersonalDetailsPO extends BagePage {
	private WebDriver driver;

	public PersonalDetailsPO(WebDriver driver) {
		this.driver = driver;
	}
}
