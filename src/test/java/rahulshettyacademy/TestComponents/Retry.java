package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count = 0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		//You can Rerun tests as many times as you want in this method. here we will rerun tests once
		if(count < maxTry) {
			count++;
			return true;
		}
		return false;
	}
}
