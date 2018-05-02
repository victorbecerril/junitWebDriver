package imdbClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestImdb {
	
	private WebDriver driver;
	private WebElement correctMovie;

	//@Test
	public void testSearchMovie() {
		searchMovie("It");
		validateMovieExists("It", "1990");
	}
	
	@Test
	public void testValidateMovieStars() {
		searchMovie("It");
		validateMovieExists("It", "1990");
		clickMovie("It", "1990");
		validateStartsExist("Richard Thomas", "Tim Reid", "Annette O'Toole");
	}
	
	@Before
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.imdb.com");
		correctMovie = null;
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}

	private void searchMovie(String movie) {
		WebElement movieField = driver.findElement(By.name("q"));
		movieField.clear();
		movieField.sendKeys(movie);
		WebElement movieSearchButton = driver.findElement(By.id("navbar-submit-button"));
		movieSearchButton.click();
		
	}

	private void validateMovieExists(String movie, String year) {
		
		List<WebElement> movieList = driver.findElements(By.tagName("td"));
		System.out.println(movieList.size());
		for(WebElement movieItem : movieList) {
			if(movieItem.getText().contains(year)) {
				correctMovie = movieItem;
				break;
			}
		}
		System.out.println("The correct movie: " + correctMovie.getText() + "was found");
	}

	private void clickMovie(String movie, String year) {
		correctMovie.findElement(By.linkText(movie)).click();
		
	}

	private void validateStartsExist(String star1, String star2, String star3) {
		WebElement starContainer = driver.findElement(By.className("credit_summary_item"));
		WebElement star1Element = starContainer.findElement(By.xpath(".//*[contains(text(), '" + star1 + "')]"));
		System.out.println("Found star1: " + star1Element.isDisplayed());
		WebElement star2Element = starContainer.findElement(By.xpath(".//*[contains(text(), '" + star2 + "')]"));
		System.out.println("Found star2: " + star2Element.isDisplayed());
		WebElement star3Element = starContainer.findElement(By.xpath(".//*[contains(text(), '" + star3 + "')]"));
		System.out.println("Found star3: " + star3Element.isDisplayed());
		
	}
	

}
