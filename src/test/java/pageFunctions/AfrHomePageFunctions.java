package pageFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.AfrHomePage;
import utilities.MasterClass;

public class AfrHomePageFunctions extends MasterClass{

	AfrHomePage afrHomePageObjects;
	int timeOut = 10;
	
	public AfrHomePageFunctions(WebDriver driver)
	{
		this.driver = driver;
		afrHomePageObjects = PageFactory.initElements(driver, AfrHomePage.class);
	}
	
	public void verifyLogoDisplayed() throws Exception {
		verifyElementVisible(afrHomePageObjects.homeScreenLogo, timeOut, "Logo is not displayed on home screen");
	}
	
	public void verifySubscriptionTextOrPopupDisplayed() throws Exception {
		verifyElementVisible(afrHomePageObjects.subscriptionText, timeOut, "Subscription text/popup is not displayed on home screen");
	}
	
	public void verifySubscriptionTextOrPopupNotDisplayed() throws Exception {
		verifyElementInvisible(afrHomePageObjects.subscriptionText, timeOut, "Subscription text/popup is displayed on home screen");
	}
	
}
