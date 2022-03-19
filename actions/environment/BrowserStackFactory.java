package environment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class BrowserStackFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String osVersion;

	public BrowserStackFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}

	public WebDriver createDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", osName);
		caps.setCapability("os_version", osVersion);
		caps.setCapability("browser", browserName);
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("resolution", "1920x1080");
		caps.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
