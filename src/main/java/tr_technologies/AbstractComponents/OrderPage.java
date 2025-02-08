package tr_technologies.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tr_technologies.pageobjects.CheckOutPage;

public class OrderPage extends AbstractComponent {

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		// TODO Auto-generated method stub

	}

	

}