package auto.core;

import java.util.concurrent.TimeUnit;

import javax.management.ListenerNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

final class DefaultConfiguration {
	
	public static WebDriver driver;
	protected void launchBrowser(String browser) {
			String UserDirectory = System.getProperty("user.dir").toString(); 
			String filePath = null;
			
		switch(browser) {
		
		case "Chrome": 	 
			filePath = UserDirectory.concat("\\src\\test\\resources\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", filePath);
			driver = new ChromeDriver();
		break;
	
		case "IE":  
			
			filePath = UserDirectory.concat("\\src\\test\\resources\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", filePath);
			driver = new InternetExplorerDriver();
		break;	 
		
		case "FF":
			//driver = new FirefoxDriver();
			filePath = UserDirectory.concat("\\src\\test\\resources\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", filePath);
			driver = new FirefoxDriver();
		break;
		
		case "Opera":
			//driver = new FirefoxDriver();
			filePath = UserDirectory.concat("\\src\\test\\resources\\operadriver.exe");
			System.setProperty("webdriver.opera.driver", filePath);
			driver = new OperaDriver();
		break;
		
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}

	protected void quit() {
		try{
			driver.quit();
		}catch(RuntimeException  e) {
			driver.quit();
		}
	}
	
	protected void close() {
		try{
			driver.quit();
		}catch(RuntimeException  e) {
			driver.close();
		}
	}
	
	}

