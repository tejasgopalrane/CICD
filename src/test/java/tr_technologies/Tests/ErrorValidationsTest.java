package tr_technologies.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import tr_technologies.TestComponents.BaseTest;
import tr_technologies.TestComponents.Retry;
import tr_technologies.pageobjects.CartPage;
import tr_technologies.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest { 

	@Test (groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "IPHONE 13 PRO";

		ProductCatalogue productCatalogue = landingPage.loginApplication("tejas_random@gmail.com", "Tejas@12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "IPHONE 13 PRO";

		ProductCatalogue productCatalogue = landingPage.loginApplication("tejascart@gmail.com", "@Tejas123");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("IPHONE 13 PRO PRO");
		Assert.assertFalse(match);
	}

}
