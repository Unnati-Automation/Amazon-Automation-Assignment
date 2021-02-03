package auto.core;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class Listners implements ITestListener {
	
	DefaultConfiguration defaultConfig = new DefaultConfiguration();
	Calendar calendar;
	
	public void onStart(ITestContext context) {
	    String directoryPath = System.getProperty("user.dir").toString();
        String failedScreenshotPath = directoryPath.concat("\\Failed Test Screenshot\\");
        File file = new File (failedScreenshotPath);
        try {
			FileUtils.cleanDirectory(file);
		} catch (IOException e) {
			System.out.println("Folder not found");
			e.printStackTrace();
		}
		System.out.println("Driver is initializing...");
		String b = context.getCurrentXmlTest().getParameter("browser");
		defaultConfig.launchBrowser(b);
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}
	
	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		defaultConfig.quit();	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {

		 File scrFile = ((TakesScreenshot)defaultConfig.driver).getScreenshotAs(OutputType.FILE);
         String directoryPath = System.getProperty("user.dir").toString();
         String screenshotPath = directoryPath.concat("\\Screenshots\\");
         String fileName =("Login-"+calendar.HOUR + "-" + calendar.MINUTE + "-" + calendar.SECOND) + ".png";
            try {
				FileUtils.copyFile(scrFile, new File(screenshotPath+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");

	}

}
