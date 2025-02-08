package tr_technologies.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tr_technologies.TestComponents.BaseTest;
import tr_technologies.pageobjects.CartPage;
import tr_technologies.pageobjects.CheckOutPage;
import tr_technologies.pageobjects.ConfirmationPage;
import tr_technologies.pageobjects.LandingPage;
import tr_technologies.pageobjects.ProductCatalogue;

public class stepDefination extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue ;
	public ConfirmationPage confirmationPage;
	
@Given ("I landed on E-commerce Page")
	
public void I_landed_on_Ecommerce_Page() throws IOException {
	landingPage=launchApplication();
}

@Given ("^Logged in with username (.+) and password (.+)$")
public void logged_in_username_and_password(String username, String password) {
	productCatalogue = landingPage.loginApplication(username, password);
}


@When ("^I add product (.+) from cart$")
public void i_add_product_to_cart(String productName) throws InterruptedException {
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addProductToCart(productName);
}


@When ("^Checkout (.+) and submit the order$")
public void checkout_submit_order(String productName) {
	CartPage cartPage = productCatalogue.goToCartPage();

	Boolean match = cartPage.verifyProductDisplay(productName);
	Assert.assertTrue(match);
	CheckOutPage checkoutPage = cartPage.goToCheckout();
	checkoutPage.selectCountry("india");
	confirmationPage = checkoutPage.submitOrder();

}

@Then ("{string} message is displayed on ConfirmationPage")
public void message_displayed_confirmationPage(String string)
{ 
 
	String confirmMessage = confirmationPage.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	

}


@Then("{string} messaged is displayed")
public void messaged_is_displayed(String string) {
	Assert.assertEquals(string, landingPage.getErrorMessage());

    
    
}


}

