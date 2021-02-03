package auto.core;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Global{
	
	WebDriver driver  = DefaultConfiguration.driver;
	
	protected static Global getclass() {
		return new Global();
	}
	
	protected void load_URL(String baseURL){
		driver.get(baseURL);
	}
	
	protected void click(By locator){
		driver.findElement(locator).click();
		waitUntil(2);
	}
	
	protected void clear(By locator){
		driver.findElement(locator).clear();
		waitUntil(2);
	}
	
	protected void enter(By locator, String text){
		try {
			driver.findElement(locator).sendKeys(text);
			}
		catch(StaleElementReferenceException ex) {
			wait_until_visibility_of_element(locator);
			driver.findElement(locator).sendKeys(text);
		}
		waitUntil(2);
	}
	
	protected String find_element_text(By locator) {
		waitUntil(2);
  		return driver.findElement(locator).getText();
  	}
	
	protected WebElement find_web_element(By locator) {
	WebElement element = driver.findElement(locator);
	waitUntil(2);
	return element;
	}
	
	protected ArrayList find_values_from_dropdown(By locator){
		Select select = new Select(driver.findElement(locator));
		ArrayList data = (ArrayList) select.getOptions();
		return data;
	} 
	
	protected void select_by_value(By locator, String value) {
	    Select select = new Select(driver.findElement(locator));
	    waitUntil(2);
	   	select.selectByValue(value);
	   	waitUntil(2);
	}
	    
	protected void select_by_name(By locator, String name) {
    	Select select = new Select(driver.findElement(locator));
    	waitUntil(2);
   		select.selectByVisibleText(name);
   		waitUntil(2);
	}
	
	protected void select_by_index(By locator, int index) {
    	Select select = new Select(driver.findElement(locator));
    	waitUntil(2);
   		select.selectByIndex(index);
   		waitUntil(2);
	}
    
	protected void deselect_all(By locator) {
    	Select select =  new Select(driver.findElement(locator));
    	select.deselectAll();
    	waitUntil(2);
	}
    
    protected boolean is_text_present_on_page(String text) {
    	waitUntil(2);
    	return driver.getPageSource().contains(text);
    }
    
    protected String find_current_url() {
    	waitUntil(2);
    	return driver.getCurrentUrl();
    }
    
    protected Boolean is_element_selected(By locator) {
    	waitUntil(2); 
    	WebElement element = driver.findElement(locator);
		return element.isSelected();
	}
	
	protected Boolean is_element_enabled(By locator) {
		waitUntil(2); 
		WebElement element = driver.findElement(locator);
	     return element.isEnabled();
	}
	
	protected Boolean is_element_displayed(By locator) {
		waitUntil(2); 
		WebElement element = driver.findElement(locator);
		 return element.isDisplayed();
	}
	protected void mouse_hover(By locator){
		waitUntil(2);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}
	
	protected Global closeBrowser(){
		driver.quit();
		driver = null;
		return this;
	}
	
	
	protected void wait_until_visibility_of_element(By locator) {
		waitUntil(2);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected void wait_until_visibility_of_text(String text) {
		waitUntil(2);
		WebDriverWait wait = ExpliciteWait();
		WebElement element = driver.findElement(By.xpath(".//*[text()='"+text+"']"));
		wait.until(ExpectedConditions.visibilityOf(element));	
	}
	
	protected WebDriverWait ExpliciteWait() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait;
	}
	
	protected void alert_comments(String text) {
	   	driver.switchTo().alert().sendKeys(text);
	   	waitUntil(2);
	}

	protected void alert_accept() {
    	driver.switchTo().alert().accept();
    	waitUntil(2);
	 }
	    
	protected void alert_cancel() {
	   	driver.switchTo().alert().dismiss();
	   	waitUntil(2);
	}
	
	protected void switch_to_frame_by_locator(By locator) {
		WebElement element = driver.findElement(locator);
		driver.switchTo().frame(element);
		waitUntil(2);
	}
	
	protected void switch_to_frame_by_index(int index) {
		driver.switchTo().frame(index);
		waitUntil(2);
	}
	
	protected void switch_to_frame_by_name(String name) {
		driver.switchTo().frame(name);
		waitUntil(2);
	}

	protected void scroll_down_to_element(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", locator);
		waitUntil(2);
	}
	
	protected void scroll_down_by_pixel(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,"+pixel+")");
		waitUntil(2);
	}
	
	
	public Global switch_to_popup_window() {
		String ParentWindow = driver.getWindowHandle();
		Set <String> WindowHandles = driver.getWindowHandles();
		for(String window : WindowHandles) {
			if(!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
			}
		}
		waitUntil(2);
		return this;
	} 
	
	public Global switch_to_parent_window() {
		Set <String> WindowHandles = driver.getWindowHandles();
		for(String window : WindowHandles) {
			driver.switchTo().window(window);
		}
		waitUntil(2);
		return this;
	} 
	
	public Global close_window() {
		driver.close();
		waitUntil(3);
		return this;
	} 
	
	public Global waitUntil(int sleepInSecs) {
    	
	    	try {
				Thread.sleep(sleepInSecs*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return this;
	    }
	
	public int getElementXCordinate(By locator) {
		Point dimension = driver.findElement(locator).getLocation();
		return dimension.getX();
		
	}
	public int getElementYCordinate(By locator) {
		Point dimension = driver.findElement(locator).getLocation();
		return dimension.getY();
	}
	
	public int getElementHeight(By locator) {
		Dimension size = driver.findElement(locator).getSize();
		return size.getHeight();
		
	}
	public int getElementWidth(By locator) {
		Dimension size = driver.findElement(locator).getSize();
		return size.getWidth();
	}
	
}


