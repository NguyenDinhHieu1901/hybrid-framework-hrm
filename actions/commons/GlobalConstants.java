package commons;

import java.io.File;

public class GlobalConstants {
	public static final long LONG_TIMEOUT = 15;
	public static final long SHORT_TIMEOUT = 5;
	public static final String ADMIN_HRM_URL = "https://opensource-demo.orangehrmlive.com/";
	public static final String PORTAL_HRM_URL = "https://opensource-demo.orangehrmlive.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "dowload" + File.separator;
	public static final String BROWSERSTACK_USER_NAME = "wisenguyen_gCHSh6";
	public static final String BROWSERSTACK_KEY_ACCESS = "FxwzvaxbpKxYZnaEaipJ";
	public static final String BROWSERSTACK_URL = "https://" + BROWSERSTACK_USER_NAME + ":" + BROWSERSTACK_KEY_ACCESS + "@hub-cloud.browserstack.com/wd/hub";
	public static final String SAUCELABS_USER_NAME = "oauth-hieuwise1966-aba8a";
	public static final String SAUCELABS_KEY_ACCESS = "a7133ce5-c1fe-46b7-9324-9178c6cbf06b";
	public static final String SAUCELABS_URL = "https://" + SAUCELABS_USER_NAME + ":" + SAUCELABS_KEY_ACCESS + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
}
