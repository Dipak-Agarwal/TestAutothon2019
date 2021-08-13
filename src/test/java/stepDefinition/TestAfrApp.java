package stepDefinition;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageFunctions.AfrHomePageFunctions;
import utilities.MasterClass;

public class TestAfrApp extends MasterClass{

	WebDriver driver;
	
	AfrHomePageFunctions objAfrHomePageFunctions;
	
//	@Parameters({"browser"})
	@Given("^the user navigates to the home page of AFR App in \"([^\"]*)\" browser$")
	public void the_user_navigates_to_the_home_page_of_AFR_App_in_browser(String browserName) throws Throwable {
		System.out.println(Thread.currentThread().getId() + " "+Thread.currentThread().getName());
		this.driver = setUp(browserName);
		System.out.println("Browser Opened");
		objAfrHomePageFunctions = new AfrHomePageFunctions(driver);
		objAfrHomePageFunctions.navigateToUrl();
		System.out.println("Navigated to Url");
		objAfrHomePageFunctions.verifyLogoDisplayed();
		System.out.println("User has successfully navigated to the home page");
	}
	
//	@Given("^the user navigates to the home page of AFR App$")
//	public void the_user_navigates_to_the_home_page_of_AFR_App() throws Throwable {
//	    
//		System.out.println(Thread.currentThread().getId() + " "+Thread.currentThread().getName());
//		this.driver = setUp();
//		System.out.println("Browser Opened");
//		objAfrHomePageFunctions = new AfrHomePageFunctions(driver);
//		objAfrHomePageFunctions.navigateToUrl();
//		System.out.println("Navigated to Url");
//		objAfrHomePageFunctions.verifyLogoDisplayed();
//		System.out.println("User has successfully navigated to the home page");
//	}

	@Given("^verify the subscription popup is displayed$")
	public void verify_the_subscription_popup_is_displayed() throws Throwable {
		objAfrHomePageFunctions.verifySubscriptionTextOrPopupDisplayed();
		System.out.println("Subscription popup is displayed");
		captureScreenshot(driver, "Step_2_Subscription_Popup_Displayed");
	}

	@When("^user naviagtes to the bottom to the web page$")
	public void user_naviagtes_to_the_bottom_to_the_web_page() throws Throwable {
		scrollDownTillEnd();
		System.out.println("Successfully scrolled to the bottom of the web page");
	}

	@Then("^after sometime the subscription popup disappears$")
	public void after_sometime_the_subscription_popup_disappears() throws Throwable {
		objAfrHomePageFunctions.verifySubscriptionTextOrPopupNotDisplayed();
		System.out.println("Subscription popup is not displayed anymore");
		captureScreenshot(driver, "Step_5_Subscription_Popup_Not_Displayed");
		tearDown();
	}

	
}
