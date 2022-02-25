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
}
