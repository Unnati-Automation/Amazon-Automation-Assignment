package stepDefs;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class stepDefinition {
	String userDir = System.getProperty("user.dir").toString();
	String filePath = userDir.concat("\\chromedriver.exe");
	static WebDriver driver;
	
	@Given("^User is on Amazon page (.*?)$")
    public void launchWebsite(String URL){
		 System.setProperty("webdriver.chrome.driver", filePath);
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(URL);
    }

    @When("^User searches for (.*?), sort the filter from highest to Lowest$")
    public void searchProduct(String searchText) throws InterruptedException  {
    	 driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchText);
    	 driver.findElement(By.id("nav-search-submit-button")).click();
    	 Thread.sleep(3000);
    	 driver.findElement(By.xpath(".//span[@data-action='a-dropdown-button']")).click();
    	 Thread.sleep(1000);
    	 driver.findElement(By.xpath(".//a[text()='Price: High to Low']")).click();
    	 Thread.sleep(5000);
    }
  
    @And("^User clicks on second product$")
    public void clickSecondProduct() throws InterruptedException  {
    	 driver.findElement(By.xpath("(.//*[@class='s-main-slot s-result-list s-search-results sg-row']//div[@data-index='1'])[1]//h2//span")).click();
    }												
    
    @Then("^Verify the product name as (.*?)$")
    public void verifyProductName(String expectedProductName)  {
    	 String actualResult = driver.findElement(By.id("productTitle")).getText().toString().trim();
    	 System.out.println(actualResult+" " +expectedProductName);
    	 driver.quit();
    	assertTrue("Second Product name mismatched, actual is "+actualResult,actualResult.contains(expectedProductName));
      }
}
