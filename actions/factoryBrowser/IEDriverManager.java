package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.iedriver().arch32().setup();
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability("disable-save-password-bubble", "true");
		return new InternetExplorerDriver(options);
	}
}
