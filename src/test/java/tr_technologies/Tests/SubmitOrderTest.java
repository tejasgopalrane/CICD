package tr_technologies.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tr_technologies.AbstractComponents.OrderPage;
import tr_technologies.TestComponents.BaseTest;
import tr_technologies.pageobjects.CartPage;
import tr_technologies.pageobjects.CheckOutPage;
import tr_technologies.pageobjects.ConfirmationPage;
import tr_technologies.pageobjects.LandingPage;
import tr_technologies.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify IPHONE 13 PRO is displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("tejas_random@gmail.com", "Tejas@123");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		System.out.println(ordersPage.verifyOrderDisplay(productName));
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap <String, String> map1=new HashMap <String, String>();
//		map1.put("email", "tejas_random@gmail.com");
//		map1.put("password", "Tejas@123");
//		map1.put("product", "IPHONE 13 PRO");
//		
//		HashMap <String, String> map2=new HashMap <String, String>();
//		map2.put("email", "tejascart@gmail.com");
//		map2.put("password", "@Tejas123");
//		map2.put("product", "BANARSI SAREE");

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\tr_technologies\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
