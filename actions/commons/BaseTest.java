package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Logger log;

	protected BaseTest() {
		log = Logger.getLogger(getClass());
	}

	private enum BrowserList {
		FIREFOX, CHROME, EDGE, IE, SAFARI, COCCOC, OPERA, BRAVE;
	}

	private enum EnvironmentList {
		DEV, TESTING, PREPRODUCTION, LIVE;
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case IE:
//			WebDriverManager.iedriver().arch32().setup();
			System.setProperty("webdriver.ie.driver", GlobalConstants.PROJECT_PATH + File.separator + "driverBrowsers" + File.separator + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		case OPERA:
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("95.0.4638.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			break;
		case BRAVE:
			WebDriverManager.chromedriver().driverVersion("96.0.4664.45").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(option);
			driver = new FirefoxDriver();
			break;

		default:
			throw new RuntimeException("Browser is not supported!");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getBrowserEnvironment("TESTING"));
		return driver;
	}

	private String getBrowserEnvironment(String environmentName) {
		String url = null;
		EnvironmentList environmentList = EnvironmentList.valueOf(environmentName.toUpperCase());
		switch (environmentList) {
		case DEV:
			url = GlobalConstants.ADMIN_HRM_URL;
			break;
		case TESTING:
			url = GlobalConstants.PORTAL_HRM_URL;
			break;

		default:
			throw new RuntimeException("Environment name is invalid!");
		}
		return url;
	}

	protected int generatorNumberRandom() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info("--------------------- PASSED ---------------------");
			} else {
				log.info("--------------------- FAILED ---------------------");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFalse(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info("--------------------- PASSED ---------------------");
			} else {
				log.info("--------------------- FAILED ---------------------");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFalse(condition);
	}

	private boolean checkEquals(Object actualValue, Object expectedValue) {
		boolean pass = true;
		try {
			Assert.assertEquals(actualValue, expectedValue);
			log.info("--------------------- PASSED ---------------------");
		} catch (Throwable e) {
			pass = false;
			log.info("--------------------- FAILED ---------------------");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actualValue, Object expectedValue) {
		return checkEquals(actualValue, expectedValue);
	}

	@BeforeTest
	public void deleteAllFilesInReportFolder() {
		log.info("------------ START delete file in folder ------------");
		String pathFolderReport = GlobalConstants.PROJECT_PATH + File.separator + "allure-json";
		File file = new File(pathFolderReport);
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (!listOfFiles[i].toString().contains(".properties")) {
				log.info("---------- FILE is removed ---------------");
				new File(listOfFiles[i].toString()).delete();
			}
		}
		log.info("------------ END delete file in folder ------------");
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = GlobalConstants.OS_NAME.toLowerCase();
			String driverInstanceName = driver.toString().toLowerCase();
			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("internet explorer")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				} else {
					cmd = "pkill IEDriverServer";
				}
			} else if (driverInstanceName.contains("safari")) {
				cmd = "pkill safaridriver";
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Process process;
			try {
				process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
