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
	@Severity(SeverityLevel.BLOCKER)
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
		editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		editGender = "Male";
		editMaritalStatus = "Married";
		editNationality = "Spanish";
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
		subUnit = "  Quality Assurance";
		location = "New York Sales Office";
		startDate = "2020-02-12";
		endDate = "2025-02-12";
		payGrade = "Grade 3";
		salaryComponent = "Basic Salary";
		payFrequency = "Monthly";
		currency = "United States Dollar";
		amount = "35000";
		accountNumber = "4214 0281 1201 0975";
		accountType = "Savings";
		routingNumber = "41215032";

		log.info("Pre-Condition - Step 01: Opening the application with '" + browserName + "'");
		driver = getBrowserDriver(browserName);
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
		log.info("Upload_Avatar_02 - Step 01: Login to system as User role with username & password: " + userNameEmp + " " + passwordEmp);
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

	@Description("Updated information in 'Personal Details' form")
	@Severity(SeverityLevel.CRITICAL)
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

	@Description("Updated information in 'Contact Details' form")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' form at Sidebar link");
		myInfoPage.openSidebarLinkByName("Contact Details");

		log.info("Contact_Details_04 - Step 02: Verify is all of fields in 'Contact Details' form disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03: Click to 'Edit' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 04: Input to all of fields in 'Contact Details' form");
		myInfoPage.inputToTextboxByID(driver, "contact_street1", addressStreet1);
		myInfoPage.inputToTextboxByID(driver, "contact_street2", addressStreet2);
		myInfoPage.inputToTextboxByID(driver, "contact_city", city);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_zipcode", zipCode);
		myInfoPage.selectItemInDefaultDropdownByName(driver, "contact[country]", country);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_hm_telephone", homeTelephone);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_mobile", mobile);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_work_telephone", workTelephone);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_work_email", workEmail);
		myInfoPage.inputToTextboxByID(driver, "contact_emp_oth_email", otherEmail);

		log.info("Contact_Details_04 - Step 05: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 06: Verify is success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact_Details_04 - Step 07: Verify is all of fields updated correctly");
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_street1", "value"), addressStreet1);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_street2", "value"), addressStreet2);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_city", "value"), city);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_zipcode", "value"), zipCode);
		verifyEquals(myInfoPage.getItemSelectedInDefaultDropdownByName(driver, "contact[country]"), country);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_hm_telephone", "value"), homeTelephone);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_mobile", "value"), mobile);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_work_telephone", "value"), workTelephone);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_work_email", "value"), workEmail);
		verifyEquals(myInfoPage.getTextValueByAttribute(driver, "contact_emp_oth_email", "value"), otherEmail);
	}

	@Description("Add Emergency Contacs of Employee")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency_Contacts_05 - Step 01: Open 'Emergency Contacts' form at Sidebar link");
		myInfoPage.openSidebarLinkByName("Emergency Contacts");

		log.info("Emergency_Contacts_05 - Step 02: Click to 'Add' button");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Contacts_05 - Step 03: Input to 'Name' textbox");
		myInfoPage.inputToTextboxByID(driver, "emgcontacts_name", emergencyContactName);

		log.info("Emergency_Contacts_05 - Step 04: Input to 'Relationship' textbox");
		myInfoPage.inputToTextboxByID(driver, "emgcontacts_relationship", emergencyContactRelationship);

		log.info("Emergency_Contacts_05 - Step 05: Input to 'Home Telephone' textbox");
		myInfoPage.inputToTextboxByID(driver, "emgcontacts_homePhone", emergencyContactHomeTelephone);

		log.info("Emergency_Contacts_05 - Step 06: Input to 'Mobile' textbox");
		myInfoPage.inputToTextboxByID(driver, "emgcontacts_mobilePhone", emergencyContactMobile);

		log.info("Emergency_Contacts_05 - Step 07: Input to 'Work Telephone' textbox");
		myInfoPage.inputToTextboxByID(driver, "emgcontacts_workPhone", emergencyContactWorkTelephone);

		log.info("Emergency_Contacts_05 - Step 08: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 09: Verify is success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Emergency_Contacts_05 - Step 10: Verify is information in data table correctly");
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "emgcontact_list", "1", "Name"), emergencyContactName);
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "emgcontact_list", "1", "Relationship"), emergencyContactRelationship);
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "emgcontact_list", "1", "Home Telephone"), emergencyContactHomeTelephone);
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "emgcontact_list", "1", "Mobile"), emergencyContactMobile);
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "emgcontact_list", "1", "Work Telephone"), emergencyContactWorkTelephone);
	}

	@Description("Add Dependents of Employee")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Assigened_Dependents_06 - Step 01: Open 'Dependents' form at Sidebar link");
		myInfoPage.openSidebarLinkByName("Dependents");

		log.info("Assigened_Dependents_06 - Step 02: Click to 'Add' button");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Assigened_Dependents_06 - Step 03: Input to 'Name' textbox");
		myInfoPage.inputToTextboxByID(driver, "dependent_name", "Antony");

		log.info("Assigened_Dependents_06 - Step 04: Select Relationship item in Dropdown");
		myInfoPage.selectItemInDefaultDropdownByName(driver, "dependent[relationshipType]", "Child");

		log.info("Assigened_Dependents_06 - Step 05: Input to 'Date of Birth' textbox with format yyyy-mm-dd");
		myInfoPage.inputToTextboxByID(driver, "dependent_dateOfBirth", "2019-12-12");

		log.info("Assigened_Dependents_06 - Step 06: Click to 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigened_Dependents_06 - Step 07: Verify is success message displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigened_Dependents_06 - Step 08: Verify is information in data table displayed correctly");
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "dependent_list", "1", "Name"), "Antony");
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "dependent_list", "1", "Relationship"), "child");
		verifyEquals(myInfoPage.getValueTextInDataTableByRowAndColumnIndex(driver, "dependent_list", "1", "Date of Birth"), "2019-12-12");
	}

	@Description("Just only Admin role has accessed editable 'Job' form")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Edit_View_Job_07 - Step 01: Open 'Job' form at Sidebar link");
		myInfoPage.openSidebarLinkByName("Job");

		log.info("Edit_View_Job_07 - Step 02: Verify all of fields in 'Job' form is disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_job_title"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_location"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_contract_end_date"));

		log.info("Edit_View_Job_07 - Step 03: Verify is 'Edit' button not displayed");
		verifyTrue(myInfoPage.isEditButtonUndisplayed(driver, "btnSave"));

		log.info("Edit_View_Job_07 - Step 04: Login to system as Admin role with username & password:" + userNameAdmin + " " + passwordAdmin);
		loginPage = myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(userNameAdmin, passwordAdmin);

		log.info("Edit_View_Job_07 - Step 05: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

		log.info("Edit_View_Job_07 - Step 06: Input to 'Employee Name' textbox");
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);
		employeeListPage.inputToTextboxByID(driver, "empsearch_employee_name_empName", editEmpFullName);

		log.info("Edit_View_Job_07 - Step 07: Click To 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("Edit_View_Job_07 - Step 08: Click to 'First Name' in Data table");
		employeeListPage.clickToDataTableLink(driver, "resultTable", "1", "First (& Middle) Name");

		log.info("Edit_View_Job_07 - Step 09: Open 'Job' form in Sidebar link");
		employeeListPage.openSidebarLinkByName("Job");

		log.info("Edit_View_Job_07 - Step 10: Verify all of fields in 'Job' form is disabled");
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_job_title"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_location"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isAnyFieldsEnabledByID(driver, "job_contract_end_date"));

		log.info("Edit_View_Job_07 - Step 11: Verify is 'Edit' button displayed");
		verifyFalse(employeeListPage.isEditButtonUndisplayed(driver, "btnSave"));

		log.info("Edit_View_Job_07 - Step 12: Click to 'Edit' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 13: Select item in 'Job Title' dropdown");
		employeeListPage.selectItemInDefaultDropdownByName(driver, "job[job_title]", jobTitle);

		log.info("Edit_View_Job_07 - Step 14: Select item in 'Employment Status' dropdown");
		employeeListPage.selectItemInDefaultDropdownByName(driver, "job[emp_status]", employmentStatus);

		log.info("Edit_View_Job_07 - Step 15: Select item in 'Job Category' dropdown");
		employeeListPage.selectItemInDefaultDropdownByName(driver, "job[eeo_category]", jobCategory);

		log.info("Edit_View_Job_07 - Step 16: Input to 'Joined Date' textbox");
		employeeListPage.inputToTextboxByID(driver, "job_joined_date", joinedDate);
		employeeListPage.clickToJobFormToDisappearDateTimePicker();

		log.info("Edit_View_Job_07 - Step 17: Select item in 'Sub Unit' dropdown");
		employeeListPage.selectItemInDefaultDropdownByName(driver, "job[sub_unit]", subUnit);

		log.info("Edit_View_Job_07 - Step 18: Select item in 'Location' dropdown");
		employeeListPage.selectItemInDefaultDropdownByName(driver, "job[location]", location);

		log.info("Edit_View_Job_07 - Step 19: Input to 'Start Date' textbox");
		employeeListPage.inputToTextboxByID(driver, "job_contract_start_date", startDate);

		log.info("Edit_View_Job_07 - Step 20: Input to 'JEnd Date' textbox");
		employeeListPage.inputToTextboxByID(driver, "job_contract_end_date", endDate);

		log.info("Edit_View_Job_07 - Step 21: Click to 'Save' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 22: Verify is success message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		log.info("Edit_View_Job_07 - Step 23: Verify all of fields in 'Job' form updated correctly");
		verifyEquals(employeeListPage.getItemSelectedInDefaultDropdownByName(driver, "job[job_title]"), jobTitle);
		verifyEquals(employeeListPage.getItemSelectedInDefaultDropdownByName(driver, "job[emp_status]"), employmentStatus);
		verifyEquals(employeeListPage.getItemSelectedInDefaultDropdownByName(driver, "job[eeo_category]"), jobCategory);
		verifyEquals(employeeListPage.getTextValueByAttribute(driver, "job_joined_date", "value"), joinedDate);
		verifyEquals(employeeListPage.getItemSelectedInDefaultDropdownByName(driver, "job[sub_unit]"), subUnit);
		verifyEquals(employeeListPage.getItemSelectedInDefaultDropdownByName(driver, "job[location]"), location);
		verifyEquals(employeeListPage.getTextValueByAttribute(driver, "job_contract_start_date", "value"), startDate);
		verifyEquals(employeeListPage.getTextValueByAttribute(driver, "job_contract_end_date", "value"), endDate);
	}

	@Test
	public void Employee_08_Edit_View_Salary() {
		log.info("Edit_View_Salary_08 - Step 01: open 'Salary' form at Sidebar link");
		log.info("Edit_View_Salary_08 - Step 02: Verify 'Add' button is displayed as Admin role");
		log.info("Edit_View_Salary_08 - Step 03: Click to 'Add' button");
		log.info("Edit_View_Salary_08 - Step 04: Select item in 'Pay Grade' dropdown");
		log.info("Edit_View_Salary_08 - Step 05: Input to 'Salary Component' textbox");
		log.info("Edit_View_Salary_08 - Step 06: Select item in 'Pay Frequency' dropdown");
		log.info("Edit_View_Salary_08 - Step 07: Select item in 'Currency' dropdown");
		log.info("Edit_View_Salary_08 - Step 08: Input to 'Amount' textbox");
		log.info("Edit_View_Salary_08 - Step 09: Input to 'Comments' textarea");
		log.info("Edit_View_Salary_08 - Step 10: Click to 'Add Direct Deposit Details' checkbox");
		log.info("Edit_View_Salary_08 - Step 11: Input to 'Account Number' textbox");
		log.info("Edit_View_Salary_08 - Step 12: Select item in 'Account Type' dropdown");
		log.info("Edit_View_Salary_08 - Step 13: Input to 'Routing Number' textbox");
		log.info("Edit_View_Salary_08 - Step 14: Input to 'Amount' textbox");
		log.info("Edit_View_Salary_08 - Step 15: Click to 'Save' button");
		log.info("Edit_View_Salary_08 - Step 16: Verify success message is displayed");
		log.info("Edit_View_Salary_08 - Step 17: Verify information in data table is displayed correctly");
		log.info("Edit_View_Salary_08 - Step 18: Click to 'Show Direct Deposit Details' checkbox in data table");
		log.info("Edit_View_Salary_08 - Step 19: Verify information in 'Direct Deposit details' table is displayed correctly");
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
	String editEmpFirstName, editEmpLastName, editGender, editMaritalStatus, editNationality, editEmpFullName;
	String addressStreet1, addressStreet2, city, zipCode, country, homeTelephone, mobile, workTelephone, workEmail, otherEmail;
	String emergencyContactName, emergencyContactRelationship, emergencyContactHomeTelephone, emergencyContactMobile, emergencyContactWorkTelephone;
	String jobTitle, employmentStatus, jobCategory, joinedDate, subUnit, location, startDate, endDate;
	String payGrade, salaryComponent, payFrequency, currency, amount, accountNumber, accountType, routingNumber;
}
