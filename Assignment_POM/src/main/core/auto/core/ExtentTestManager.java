package auto.core;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
		static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
		static Map<Integer, ExtentTest> extentNodeMap = new HashMap<Integer, ExtentTest>();
		static ExtentReports extent = ExtentManager.getInstance();
		public static ExtentTest test;
		public static ExtentTest node;
		
		public static synchronized ExtentTest getTest() {
			return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		}

		public static synchronized void endTest() {
			extent.flush();
		}

		public static synchronized ExtentTest startTest(String testName) {
			 test = extent.createTest(testName);
			extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
			return test;
		}
		
		public static synchronized ExtentTest getNode() {
			return (ExtentTest) extentNodeMap.get((int) (long) (Thread.currentThread().getId()));
		}

		public static synchronized ExtentTest startNode(String testName) {
			node = test.createNode(testName);
			extentNodeMap.put((int) (long) (Thread.currentThread().getId()), node);
			return node;
		}
}
