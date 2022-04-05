package commons;

import java.io.File;

import lombok.Getter;

@Getter
public class GlobalConstants {
	private static GlobalConstants globalInstance;

	private GlobalConstants() {

	}

	public synchronized static GlobalConstants getGlobalConstants() {
		if (globalInstance == null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}

	private final long longTimeout = 15;
	private final long shortTimeout = 5;
	private final String adminHRMUrl = "https://opensource-demo.orangehrmlive.com/";
	private final String portalHRMUrl = "https://opensource-demo.orangehrmlive.com/";
	private final String projectPath = System.getProperty("user.dir");
	private final String osName = System.getProperty("os.name");
	private final String uploadFileFolder = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String dowloadFileFolder = projectPath + File.separator + "dowload" + File.separator;
	private final String browserStackUsername = "wisenguyen_gCHSh6";
	private final String browserStackKeyAccess = "FxwzvaxbpKxYZnaEaipJ";
	private final String browserStackUrl = "https://" + browserStackUsername + ":" + browserStackKeyAccess + "@hub-cloud.browserstack.com/wd/hub";
	private final String sauceLabsUsername = "oauth-hieuwise1966-aba8a";
	private final String sauceLabsKeyAccess = "a7133ce5-c1fe-46b7-9324-9178c6cbf06b";
	private final String sauceLabsUrl = "https://" + sauceLabsUsername + ":" + sauceLabsKeyAccess + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
}
