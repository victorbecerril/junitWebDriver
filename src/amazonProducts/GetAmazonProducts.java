package amazonProducts;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class GetAmazonProducts extends GetAmazonProductsBaseTest{
	@Test
	@FileParameters("./data/paramsAmazonProduct.csv")
	public void testAmazonProducts(String keyword) {
		searchKeyword(keyword);
		getProducts();
		
	}

	

	
}
