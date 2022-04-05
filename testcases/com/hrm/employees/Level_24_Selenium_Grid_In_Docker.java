package com.hrm.employees;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;

public class Level_24_Selenium_Grid_In_Docker extends BaseTest {
	@Description("Pre-Condition: Opening web application and login to system")
	@Parameters({ "browser", "server", "environment", "ipAddress", "port", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName, @Optional("testing") String serverName, @Optional("local") String envName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber,
			@Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		userNameAdmin = "Admin";
		passwordAdmin = "admin123";

		log.info("Pre-Condition - Step 01: Opening the application with '" + browserName + "'");
		driver = getBrowserDriver(browserName, serverName, envName, ipAddress, portNumber, osName, osVersion, browserVersion);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login to system as Admin role with username & password: " + userNameAdmin + " " + passwordAdmin);
		dashboardPage = loginPage.loginToSystem(userNameAdmin, passwordAdmin);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01 - Step 01: Open Employee List page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 02: Click to Add button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
	}

	@Parameters({ "browser", "environment" })
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName, String envName) {
		log.info("Post-Condition: Cleaning the browser '" + browserName + "'");
		closeBrowserAndDriver(envName);
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	String userNameAdmin, passwordAdmin;
}
