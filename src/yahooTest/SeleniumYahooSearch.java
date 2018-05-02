package yahooTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumYahooSearch extends YahooSearchBaseTest {
	
	@Test
	@FileParameters("./data/yahooData.csv")
	public void testYahooSearch(String keyword, String link1, String link2) {
		System.out.println("Test");
		searchKeyword(keyword);
		clickLink(link1);
		changeWindow();
		clickLink(link2);
	}

}
