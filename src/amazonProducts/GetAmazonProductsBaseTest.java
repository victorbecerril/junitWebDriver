package amazonProducts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetAmazonProductsBaseTest {
	WebDriver driver;
	
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/");
	}

	protected void searchKeyword(String keyword) {
		WebElement searchBox = driver.findElement(By.name("field-keywords"));
		searchBox.clear();
		searchBox.sendKeys(keyword);
		WebElement searchButton = driver.findElement(By.xpath("//*[@value = 'Go']"));
		searchButton.click();		
	}
	
	protected void getProducts() {
		List<WebElement> productList = driver.findElements(By.xpath("//li[contains(@id, 'result')]"));
		
		for (WebElement productElem : productList) {
			WebElement productName = productElem.findElement(By.xpath(".//h2[contains(@class, 'a-size-medium')]"));
			System.out.println(productName.getText());
			WebElement productPrice = productElem.findElement(By.xpath(".//span[contains(@class, 'sx-price')"));
			System.out.println(productPrice.getText().toString());
			System.out.println();
		}
		
	}

	
	@After
	public void tearDown() {
		driver.quit();
	}
}
