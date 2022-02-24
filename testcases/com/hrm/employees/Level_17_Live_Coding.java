package com.hrm.employees;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
import pageObjects.hrm.PageGeneratorManager;
import pageObjects.hrm.MyInfoPO;

@Epic("Human Resource create information for a new employee and employee will be updated his every information")
public class Level_17_Live_Coding extends BaseTest {
	@Description("Pre-Condition: Opening web application and login to system")
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
		pathAvatarFile = GlobalConstants.UPLOAD_FILE_FOLDER + "Avatar.jpg";
		editEmpFirstName = "El";
		editEmpLastName = "Nino";
		editGender = "Male";
		editMaritalStatus = "Married";
		editNationality = "Spanish";

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
		log.info("Upload_Avatar_02 - Step 01: Login to system as User role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(userNameEmp, passwordEmp);

		log.info("Upload_Avatar_02 - Step 02: Open 'My Info' page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);

		log.info("Upload_Avatar_02 - Step 03: Click to Change photo link");
		myInfoPage.clickToChangePhotoImage();

		log.info("Upload_Avatar_02 - Step 04: Upload new Avatar");
		myInfoPage.uploadEmployeeImage(driver, pathAvatarFile);

		log.info("Upload_Avatar_02 - Step 05: Click to 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Upload_Avatar_02 - Step 05: Verify success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Upload_Avatar_02 - Step 06: Verify image uploaded successfully");
		verifyTrue(myInfoPage.isImageUploadedSuccess());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal_Details_03 - Step 01: Opening 'Personal Details' form at Sidebar link");
		myInfoPage.openSidebarLinkByName("Personal Details");

		log.info("Personal_Details_03 - Step 02: Verify is all of fileds in 'Personal Details' form disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 03: Verify is 'First Name' displayed correctly");
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmpFirstName", "value"), firstName);

		log.info("Personal_Details_03 - Step 04: Verify is 'Last Name' displayed correctly");
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmpLastName", "value"), lastName);

		log.info("Personal_Details_03 - Step 05: Verify is 'Employee Id' displayed correctly");
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmployeeId", "value"), employeeID);

		log.info("Personal_Details_03 - Step 06: Click to 'Edit' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal_Details_03 - Step 07: Verify is field 'Employee Id' disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtEmployeeId"));

		log.info("Personal_Details_03 - Step 08: Verify is field 'Driver's License Number' disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtLicenNo"));

		log.info("Personal_Details_03 - Step 09: Verify is field 'SSN Number' disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtNICNo"));

		log.info("Personal_Details_03 - Step 10: Verify is field 'SIN Number' disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_txtSINNo"));

		log.info("Personal_Details_03 - Step 11: Verify is field 'Date of Birth' disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 12: Updated 'First Name' textbox");
		myInfoPage.inputToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);

		log.info("Personal_Details_03 - Step 13: Updated 'Last Name' textbox");
		myInfoPage.inputToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);

		log.info("Personal_Details_03 - Step 14: Updated 'Male Gender' Radio butto");
		myInfoPage.checkToRadioButtonByLabel(driver, editGender);

		log.info("Personal_Details_03 - Step 15: Updated 'Marital Status' dropdown");
		myInfoPage.selectItemInDefaultDropdownByName(driver, "personal[cmbMarital]", editMaritalStatus);

		log.info("Personal_Details_03 - Step 16: Updated 'Nationality' dropdown");
		myInfoPage.selectItemInDefaultDropdownByName(driver, "personal[cmbNation]", editNationality);

		log.info("Personal_Details_03 - Step 17: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal_Details_03 - Step 18: Verify is Success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal_Details_03 - Step 19: Verify is all of fields updated successfully");
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmpFirstName", "value"), editEmpFirstName);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmpLastName", "value"), editEmpLastName);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "personal_txtEmployeeId", "value"), employeeID);
		verifyTrue(myInfoPage.isRadioButtonSelected(driver, editGender));
		verifyEquals(myInfoPage.getItemSelectedInDefaultDropdownByName(driver, "personal[cmbMarital]"), editMaritalStatus);
		verifyEquals(myInfoPage.getItemSelectedInDefaultDropdownByName(driver, "personal[cmbNation]"), editNationality);
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
	private MyInfoPO myInfoPage;
	String userNameAdmin, passwordAdmin, userNameEmp, passwordEmp, firstName, lastName, employeeID, statusValue, fullname, pathAvatarFile;
	String editEmpFirstName, editEmpLastName, editGender, editMaritalStatus, editNationality;
}
