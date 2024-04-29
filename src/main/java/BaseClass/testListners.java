package BaseClass;

import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class testListners implements ITestListener{

	public static final Logger log = Logger.getLogger(testListners.class);

	public void onTestStart(ITestResult result) {

		log.info("[Running Test] :- " + result.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult result) {
		log.info("[Test Passed] :- " + result.getMethod().getDescription());
	}

	public void onTestFailure(ITestResult result) {
		log.error("[Test Failed] :- " + result.getMethod().getDescription());
	}

	public void onTestSkipped(ITestResult result) {
		log.error("[Test Skipped] :- " + result.getMethod().getDescription());
	}

}
