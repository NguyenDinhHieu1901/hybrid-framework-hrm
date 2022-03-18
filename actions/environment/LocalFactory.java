package environment;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;

import factoryBrowser.BraveDriverManager;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.CocCocDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.OperaDriverManager;
import factoryBrowser.SafariDriverManager;
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
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case IE:
			if (SystemUtils.IS_OS_WINDOWS) {
				driver = new IEDriverManager().getBrowserDriver();
			} else {
				throw new BrowserNotSupportedException(browserName);
			}
			break;
		case SAFARI:
			if (SystemUtils.IS_OS_MAC) {
				driver = new SafariDriverManager().getBrowserDriver();
			} else {
				throw new BrowserNotSupportedException(browserName);
			}
			break;
		case OPERA:
			driver = new OperaDriverManager().getBrowserDriver();
			break;
		case COCCOC:
			driver = new CocCocDriverManager().getBrowserDriver();
			break;
		case BRAVE:
			driver = new BraveDriverManager().getBrowserDriver();
			break;

		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
