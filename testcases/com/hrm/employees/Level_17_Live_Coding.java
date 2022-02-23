package com.hrm.employees;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;
import pageObjects.hrm.PersonalDetailsPO;

public class Level_17_Live_Coding extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		userNameAdmin = "Admin";
		passwordAdmin = "admin123";
		firstName = "nguyen";
		lastName = "test";
		userNameEmp = firstName + lastName + generatorNumberRandom();
		passwordEmp = firstName + lastName + "123";
		employeeID = "";
		statusValue = "Enabled";
		fullname = firstName + " " + lastName;

		log.info("Pre-Condition - Step 01: Opening the application with '" + browserName + "'");
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login to system as Admin role");
		dashboardPage = loginPage.loginToSystem(userNameAdmin, passwordAdmin);
	}

	@Description("Employee_01_Add_New_Employee")
	@Story("Add New Employee to system")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01 - Step 01: Open Employee List page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

		log.info("Employee_01 - Step 02: Click to Add button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

		log.info("Employee_01 - Step 03: Input to First Name textbox");
		addEmployeePage.inputToTextboxByID(driver, "firstName", firstName);

		log.info("Employee_01 - Step 04: Input to Last Name textbox");
		addEmployeePage.inputToTextboxByID(driver, "lastName", lastName);

		log.info("Employee_01 - Step 05: Get Employee ID");
		employeeID = addEmployeePage.getEmployeeIDByAttribute(driver, "employeeId", "value");

		log.info("Employee_01 - Step 06: Check to Create Login Details checkbox");
		addEmployeePage.checkToDefaultCheckboxByLabel(driver, "Create Login Details");

		log.info("Employee_01 - Step 07: Input to User Name textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_name", userNameEmp);

		log.info("Employee_01 - Step 08: Input to Password textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_password", passwordEmp);

		log.info("Employee_01 - Step 09: Input to Confirm Password textbox");
		addEmployeePage.inputToTextboxByID(driver, "re_password", passwordEmp);

		log.info("Employee_01 - Step 10: Select Enabled Status dropdown");
		addEmployeePage.selectItemInDefaultDropdownByName(driver, "status", statusValue);

		log.info("Employee_01 - Step 11: Click to Save button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);

		log.info("Employee_01 - Step 12: Open Employee List page");
		personalDetailsPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01 - Step 13: Input to Employee Name textbox");
		employeeListPage.inputToTextboxByID(driver, "empsearch_employee_name_empName", fullname);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01 - Step 14: Click to Search button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01 - Step 15: Verify Information Employee is displayed in data table");
		verifyEquals(employeeListPage.getValueTextInDataTableByRowAndColumnIndex(driver, "resultTable", "1", "Id"), employeeID);
		verifyEquals(employeeListPage.getValueTextInDataTableByRowAndColumnIndex(driver, "resultTable", "1", "First (& Middle) Name"), firstName);
		verifyEquals(employeeListPage.getValueTextInDataTableByRowAndColumnIndex(driver, "resultTable", "1", "Last Name"), lastName);
	}

	@Test
	public void Employee_02_Upload_Avatar() {

	}

	@Test
	public void Employee_03_Personal_Details() {

	}

	@Test
	public void Employee_04_Contact_Details() {

	}

	@Test
	public void Employee_05_Emergency_Contacts() {

	}

	@Test
	public void Employee_06_Assigned_Dependents() {

	}

	@Test
	public void Employee_07_Edit_View_Job() {

	}

	@Test
	public void Employee_08_Edit_View_Salary() {

	}

	@Test
	public void Employee_09_Edit_View_Tax() {

	}

	@Test
	public void Employee_10_Qualifications() {

	}

	@Test
	public void Employee_11_Search_Employee() {

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Cleaning the browser '" + browserName + "'");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	private AddEmployeePO addEmployeePage;
	private PersonalDetailsPO personalDetailsPage;
	String userNameAdmin, passwordAdmin, userNameEmp, passwordEmp, firstName, lastName, employeeID, statusValue, fullname;
}
