package module1.page;
import org.testng.Assert;
import org.openqa.selenium.By;
import auto.core.Global;

public class HomePage extends Global{
	
	public static HomePage getclass() {
		return new HomePage();
	}
	public HomePage openAmazonPage(String URL) {
		load_URL(URL);
		return this;
	}
	
	 public HomePage searchProduct(String searchText) throws InterruptedException  {
    	 enter(By.id("twotabsearchtextbox"),searchText);
    	 click(By.id("nav-search-submit-button"));
    	 Thread.sleep(3000);
    	 click(By.xpath(".//span[@data-action='a-dropdown-button']"));
    	 Thread.sleep(1000);
    	 click(By.xpath(".//a[text()='Price: High to Low']"));
    	 Thread.sleep(5000);
    	 return this;
    }
  
    public HomePage clickSecondProduct() throws InterruptedException  {
    	 click(By.xpath("(.//*[@class='s-main-slot s-result-list s-search-results sg-row']//div[@data-index='1'])[1]//h2//span"));
    	 return this;
    }				
	
	
	public HomePage VerifyProductName(String expectedProductName) {
		waitUntil(5);
		 String actualResult = find_element_text(By.id("productTitle")).toString().trim();
    	 System.out.println(actualResult+" " +expectedProductName);
    	Assert.assertTrue(actualResult.contains(expectedProductName),"Second Product name mismatched, actual is "+actualResult);
		return this;
	}

}
