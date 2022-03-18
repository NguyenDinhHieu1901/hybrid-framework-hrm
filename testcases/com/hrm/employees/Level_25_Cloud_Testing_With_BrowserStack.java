package com.hrm.employees;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hrm.data.Employee;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGeneratorManager;

@Epic("Human Resource create information for a new employee and employee will be updated his every information")
public class Level_25_Cloud_Testing_With_BrowserStack extends BaseTest {
	@Description("Pre-Condition: Opening web application and login to system")
	@Severity(SeverityLevel.BLOCKER)
	@Parameters({ "browser", "server", "environment", "ipAddress", "port", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName, @Optional("testing") String serverName, @Optional("local") String envName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber,
			@Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		userNameAdmin = "Admin";
		passwordAdmin = "admin123";
		firstName = Employee.Add_New_Employee.firstName;
		lastName = Employee.Add_New_Employee.lastName;
		userNameEmp = Employee.Add_New_Employee.username;
		passwordEmp = Employee.Add_New_Employee.password;
		employeeID = "";
		statusValue = Employee.Add_New_Employee.employeeStatus;
		fullname = firstName + " " + lastName;
		pathAvatarFile = GlobalConstants.UPLOAD_FILE_FOLDER + "Avatar.jpg";
		editEmpFirstName = Employee.PersonalDetails.firstName;
		editEmpLastName = Employee.PersonalDetails.lastName;
		editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		editGender = Employee.PersonalDetails.gender;
		editMaritalStatus = Employee.PersonalDetails.maritalStatus;
		editNationality = Employee.PersonalDetails.nationality;

		addressStreet1 = "2 Le Lai";
		addressStreet2 = "1 Le Loi";
		city = "Ho Chi Minh";
		zipCode = "7000000";
		country = "Viet Nam";
		homeTelephone = "02837777777";
		mobile = "0909777777";
		workTelephone = "0909888888";
		workEmail = "as" + generatorNumberRandom() + "@as.live";
		otherEmail = userNameEmp + "@gmail.net";
		emergencyContactName = "Son Heung-min";
		emergencyContactRelationship = "Brother";
		emergencyContactHomeTelephone = "02838888888";
		emergencyContactMobile = "0909999999";
		emergencyContactWorkTelephone = "0909444444";
		jobTitle = "QA Engineer";
		employmentStatus = "Full-Time Permanent";
		jobCategory = "Technicians";
		joinedDate = "2020-12-12";
		subUnit = "Engineering";
		location = "New York Sales Office";
		startDate = "2020-02-12";
		endDate = "2025-02-12";
		payGrade = "Grade 3";
		salaryComponent = "Basic Salary";
		payFrequency = "Monthly";
		currency = "United States Dollar";
		amount = "35000";
		floatAmount = "35000.00";
		accountNumber = "4214 0281 1201 0975";
		accountType = "Savings";
		routingNumber = "41215032";
		comments = "Day of payment is 3";
		statusFedIncomeTax = "Married";
		exemptionsFedIncomeTax = "3";
		state = "Indiana";
		statusStateIncomeTax = "Married";
		exemptionsStateIncomeTax = "10";
		workState = "Arizona";
		company = "AS";
		jobTitleWorkExperience = "Engineer";
		from = "2019-12-12";
		to = "2020-12-12";
		commentWorkExperience = "Work as Manual Tester";
		levelEducation = "Bachelor's Degree";
		yearGraduated = "2019";
		gpa = "7.3";
		skill = "Java";
		yearsOfExperience = "2";
		language = "English";
		fluency = "Writing";
		competency = "Good";

		log.info("Pre-Condition - Step 01: Opening the application with '" + browserName + "'");
		driver = getBrowserDriver(browserName, serverName, envName, ipAddress, portNumber, osName, osVersion, browserVersion);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login to system as Admin role with username & password: " + userNameAdmin + " " + passwordAdmin);
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
		employeeID = addEmployeePage.getTextValueByAttribute(driver, "employeeId", "value");

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
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);

		log.info("Employee_01 - Step 12: Open Employee List page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
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

	@Description("Upload Avatar for New Employee")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Employee_02_Upload_Avatar() {

	}

	@Description("Updated information in 'Personal Details' form")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_03_Personal_Details() {

	}

	@Description("Updated information in 'Contact Details' form")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_04_Contact_Details() {

	}

	@Description("Add Emergency Contacs of Employee")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_05_Emergency_Contacts() {

	}

	@Description("Add Dependents of Employee")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_06_Assigned_Dependents() {

	}

	@Description("Just only Admin role has accessed editable 'Job' form")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_07_Edit_View_Job() {

	}

	@Description("Just only Admin role has accessed editable 'Salary' form")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_08_Edit_View_Salary() {

	}

	@Description("Just only Admin role has accessed editable 'Tax Exemptions' form")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_09_Edit_View_Tax() {

	}

	@Description("Employee add their qualifications")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_10_Qualifications() {

	}

	@Description("Search Employee")
	@Severity(SeverityLevel.CRITICAL)
	public void Employee_11_Search_Employee() {

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
	private AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
	String userNameAdmin, passwordAdmin, userNameEmp, passwordEmp, firstName, lastName, employeeID, statusValue, fullname, pathAvatarFile;
	String editEmpFirstName, editEmpLastName, editGender, editMaritalStatus, editNationality, editEmpFullName;
	String addressStreet1, addressStreet2, city, zipCode, country, homeTelephone, mobile, workTelephone, workEmail, otherEmail;
	String emergencyContactName, emergencyContactRelationship, emergencyContactHomeTelephone, emergencyContactMobile, emergencyContactWorkTelephone;
	String jobTitle, employmentStatus, jobCategory, joinedDate, subUnit, location, startDate, endDate;
	String payGrade, salaryComponent, payFrequency, currency, amount, floatAmount, accountNumber, accountType, routingNumber, comments;
	String statusFedIncomeTax, exemptionsFedIncomeTax, state, statusStateIncomeTax, exemptionsStateIncomeTax, workState;
	String company, jobTitleWorkExperience, from, to, commentWorkExperience, levelEducation, yearGraduated, gpa, skill, yearsOfExperience, language, fluency, competency;
}