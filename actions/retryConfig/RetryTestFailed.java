package retryConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailed implements IRetryAnalyzer {
	private Log log = LogFactory.getLog(getClass());
	private int retryCount = 0;
	private int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			log.info("Retry test name: " + result.getName() + " with: " + (retryCount + 1) + " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
}
