package yahooTest;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahooSearchBaseTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://espanol.yahoo.com/?p=us");
	
	}
	
	protected void searchKeyword(String keyword) {
		WebElement searchBox = driver.findElement(By.name("p"));
		searchBox.clear();
		searchBox.sendKeys(keyword);
		WebElement searchButton = driver.findElement(By.id("uh-search-button"));
		searchButton.click();		
	}
	protected void changeWindow() {
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		  System.out.println("Number of windows: " + windowIds.size());
		  
		  for(String windowId: windowIds) {
		   driver.switchTo().window(windowId);
		  }

		
	}
	protected void clickLink(String linkText) {
		WebElement link = driver.findElement(By.linkText(linkText));
		link.click();
		
	}
	
	
	@After
	public void tearDown() {
		driver.quit();
	}
	

}
