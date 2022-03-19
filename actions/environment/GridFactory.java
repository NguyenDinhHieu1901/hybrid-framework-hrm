package environment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;

	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	@SuppressWarnings("deprecation")
	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		DesiredCapabilities capability = null;

		switch (browserList) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().driverVersion("92.0.1").setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.merge(capability);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.merge(capability);
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			capability = DesiredCapabilities.edge();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.ANY);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.merge(capability);
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.WINDOWS);
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.merge(capability);
			break;
		case SAFARI:
			capability = DesiredCapabilities.safari();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.MAC);
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.merge(capability);
			break;
		case OPERA:
			WebDriverManager.operadriver().setup();
			capability = DesiredCapabilities.opera();
			capability.setBrowserName(browserName);
			capability.setPlatform(Platform.ANY);
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.merge(capability);
			break;

		default:
			throw new RuntimeException("Browser is not supported!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
