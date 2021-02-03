package module1.Test;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import module1.page.HomePage;


public class AmazonTest {
	
	String directoryPath = System.getProperty("user.dir").toString();
	String expctedProductName = "Nikon D3X";
	
	@Test
	@Parameters("url")
	public void amazonProductVerification(String url) throws InterruptedException {
		HomePage.getclass()
			.openAmazonPage(url);
		
		HomePage.getclass()
			.searchProduct("Nikon")
			.clickSecondProduct()
			.VerifyProductName(expctedProductName);
	}
	
}
