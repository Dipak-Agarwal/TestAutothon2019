package stepDefinition;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageFunctions.ImdbPageFunctions;
import pageFunctions.OrangeAppLoginPageFunctions;
import utilities.BaseClass;
import utilities.MasterClass;
import utilities.ReadCSV;

public class LoginOrangeApp extends MasterClass{

	WebDriver driver;
	
	OrangeAppLoginPageFunctions objOrangeAppLoginPageFunctions;
	
	HashMap<String, String> dataMap;
	ArrayList<HashMap<String, String>> arrayListData;
	
	@Given("^a list of usernames and passwords$")
	public void a_list_of_usernames_and_passwords() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(Thread.currentThread().getId() + " "+Thread.currentThread().getName());
		arrayListData = ReadCSV.readFile("@OrangeAppLoginFunc");
		System.out.println(arrayListData);
		this.driver = setUp();
		objOrangeAppLoginPageFunctions = new OrangeAppLoginPageFunctions(driver);
//		setUp();
	}

	@When("^user tries to login to Orange App with given creds$")
	public void user_tries_to_login_to_Orange_App_with_given_creds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		for (int i = 0; i < arrayListData.size(); i++) {
			dataMap = arrayListData.get(i);
			navigateToUrl();
			objOrangeAppLoginPageFunctions.testNegativeLogin(dataMap);
		}
	}

	@Then("^the user should be login$")
	public void the_user_should_be_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Login Functionality validated successfully");
//		objMasterClass.tearDown();
	}
	
	
}
