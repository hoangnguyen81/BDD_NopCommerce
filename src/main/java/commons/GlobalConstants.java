package commons;

public class GlobalConstants {
	
		// Sytem Info
		public static final String PROJECT_PATH = System.getProperty("user.dir");
		public static final String OS_NAME = System.getProperty("os.name");
		public static final String JAVA_VERSION = System.getProperty("java.version");

		// App Info User
		public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
		public static final String TEST_USER_URL = "https://test.nopcommerce.com/";
		public static final String STAGING_USER_URL = "https://staging.nopcommerce.com/";
		public static final String LIVE_USER_URL = "https://www.nopcommerce.com/";

		// App Infor Admin
		public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
		public static final String STAGING_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
		public static final String LIVE_ADMIN_URL = "https://admin.nopcommerce.com/";

		public static final String ADMIN_USERNAME = "admin@yourstore.com";
		public static final String ADMIN_PASSWORD = "admin";

		// Wait Info
		public static final long SHORT_TIMEOUT = 10;
		public static final long LONG_TIMEOUT = 30;

		// Download/Upload file
		public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles/";
		public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles/";

		// Retry Case failed
		public static final int RETRY_NUMBER = 3;

		// Browser Logs/Extentsion
		public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLogs/";
		public static final String BROWSER_EXTENTSION_PATH = PROJECT_PATH + "/browserExtentsions/";

		// HTML Report Folder
		public static final String REPORTNG_PATH = PROJECT_PATH + "/htmlReportNG/";
		public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent/";
		public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure/";

		public static final String USERNAME = "nguyenhoang_H6K1Zw";
		public static final String AUTOMATE_KEY = "yUEesMz3QMyxYPZGpWkR";
		public static final String BROWSER_STACK_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

}
