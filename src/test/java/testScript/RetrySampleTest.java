package testScript;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetrySampleTest implements IRetryAnalyzer {
	
	private int retryCount = 0;
	  private static final int maxRetryCount = 3;
	 
	  public boolean retry(ITestResult result) {
		  if(!result.isSuccess()) {
	    if (retryCount < maxRetryCount) {
	      retryCount++;
	      return true;
	    }
	 }
	    return false;
	  }


}
