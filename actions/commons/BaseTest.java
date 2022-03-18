package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import environment.BrowserStackFactory;
import environment.GridFactory;
import environment.LocalFactory;
import environment.SauceLabsFactory;
import environment.ServerList;

public abstract class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected final Logger log;

	protected BaseTest() {
		log = Logger.getLogger(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName, String serverName, String envName, String ipAddress, String portNumber, String osName, String osVersion, String browserVersion) {
		switch (envName) {
		case "local":
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		case "grid":
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;
		case "browserstack":
			driver.set(new BrowserStackFactory(browserName, osName, osVersion).createDriver());
			break;
		case "saucelabs":
			driver.set(new SauceLabsFactory(browserName, osName, browserVersion).createDriver());
			break;
		default:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		}
		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get().manage().window().maximize();
		driver.get().get(getBrowserEnvironment(serverName));
		return driver.get();
	}

	private String getBrowserEnvironment(String serverName) {
		String url = null;
		ServerList serverList = ServerList.valueOf(serverName.toUpperCase());
		switch (serverList) {
		case DEV:
			url = GlobalConstants.ADMIN_HRM_URL;
			break;
		case TESTING:
			url = GlobalConstants.PORTAL_HRM_URL;
			break;
		case STAGING:
			url = GlobalConstants.PORTAL_HRM_URL;
			break;
		case PREPRODUCTION:
			url = GlobalConstants.PORTAL_HRM_URL;
			;
			break;
		case LIVE:
			url = GlobalConstants.PORTAL_HRM_URL;
			break;

		default:
			throw new RuntimeException("Server name is invalid!");
		}
		return url;
	}

	protected int generatorNumberRandom() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public WebDriver getWebDriver() {
		return driver.get();
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

	protected void closeBrowserAndDriver(String envName) {
		String cmd = "";
		if (envName.equals("local") || envName.equals("gird")) {
			try {
				String osName = GlobalConstants.OS_NAME.toLowerCase();
				String driverInstanceName = driver.get().toString().toLowerCase();
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
					driver.get().manage().deleteAllCookies();
					driver.get().quit();
					driver.remove();
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
}
