package orangeHRM;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerClass implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WriteExcel wr = new WriteExcel();
		
		String[] data = new String[3];
		data[0] = result.getTestName();
		data[1] = (String)result.getAttribute("tdata");
		data[2] = "Passed";
		
		try {
			wr.writeExcel("Result", data);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WriteExcel wr = new WriteExcel();
		
		String[] data = new String[3];
		data[0] = result.getTestName();
		data[1] = (String)result.getAttribute("tdata");
		data[2] = "Failed";
		
		try {
			wr.writeExcel("Result", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
