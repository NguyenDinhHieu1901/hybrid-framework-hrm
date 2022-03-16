package environment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class SauceLabsFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String browserVersion;

	public SauceLabsFactory(String browserName, String osName, String browserVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.browserVersion = browserVersion;
	}

	public WebDriver createDriver() {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", osName);
		caps.setCapability("browserName", browserName);
		caps.setCapability("browserVersion", browserVersion);
		Map<String, Object> sauceOptions = new HashMap<>();
		if (osName.contains("Windows")) {
			sauceOptions.put("screenResolution", "1920x1080");
		} else {
			sauceOptions.put("screenResolution", "1920x1440");
		}
		caps.setCapability("sauce:options", sauceOptions);
		caps.setCapability("name", "Run on " + osName + " | " + browserName + " | " + browserVersion);
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCELABS_URL), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
