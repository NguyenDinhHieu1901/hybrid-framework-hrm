package environment;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
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
			// WebDriverManager.iedriver().arch32().setup();
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
		return driver;
	}
}
