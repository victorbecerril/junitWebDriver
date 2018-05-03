package amazonProducts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
		List<WebElement> productList = driver.findElements(By.xpath("//li[starts-with(@id, 'result')]"));
		
		for (WebElement productElem : productList) {
			System.out.println(productElem.getAttribute("Id"));
			try {
				WebElement productName = productElem.findElement(By.xpath(".//h2[contains(@class, 'a-size-medium')]"));
				System.out.println("Item: " + productName.getText());
			} catch (Exception e1) {
				System.out.println("No se encontro el nombre del articulo");
			}
			WebElement productPrice = productElem.findElement(By.xpath(".//span[contains(@class, 'sx-price')]"));
			String formattedPrice = formatPrice(productPrice.getText());
			System.out.println("Price: " + formattedPrice);
			WebElement productRating;
			try {
				productRating = productElem.findElement(By.xpath(".//*[contains(text(), 'out of 5 stars')]"));
				String ratingText = productRating.getAttribute("textContent");
				String rating = getRating(ratingText);
				System.out.println("Rating: " + rating);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				System.out.println("No Rating Provided");
			}
			System.out.println();
		}
		
	}

	
	private String getRating(String ratingText) {
		// TODO Auto-generated method stub
		//X.X out of 5 stars
		int firstSpaceIndex = ratingText.indexOf(" ");
		String rating = ratingText.substring(0, firstSpaceIndex);
		return rating;
	}

	private String formatPrice(String text) {
		// TODO Auto-generated method stub
		//"$ XXX XX"
		String[] priceElements = text.split(" ");
		String formattedPrice = priceElements[0] + priceElements[1] + "." + priceElements[2];
		return formattedPrice;
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
