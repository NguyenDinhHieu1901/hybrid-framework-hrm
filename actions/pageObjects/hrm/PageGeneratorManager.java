package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	private PageGeneratorManager() {

	}

	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}

	public static DashboardPO gerDashboardPage(WebDriver driver) {
		return new DashboardPO(driver);
	}

	public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
		return new EmployeeListPO(driver);
	}

	public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
		return new AddEmployeePO(driver);
	}

	public static PersonalDetailsPO getPersonalDetailsPage(WebDriver driver) {
		return new PersonalDetailsPO(driver);
	}
}
