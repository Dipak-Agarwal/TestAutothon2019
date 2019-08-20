package pageFunctions;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.ImdbPage;
import pageObjects.OrangeAppLoginPage;
import utilities.MasterClass;

public class OrangeAppLoginPageFunctions extends MasterClass{

	OrangeAppLoginPage orangeAppLoginPageObjects;
	int timeOut = 10;
	
	public OrangeAppLoginPageFunctions(WebDriver driver)
	{
		this.driver = driver;
		orangeAppLoginPageObjects = PageFactory.initElements(driver, OrangeAppLoginPage.class);
	}

	public void testSuccessfulLogin(HashMap<String, String> dataMap) throws Exception {
		sendKeysToTextBox(orangeAppLoginPageObjects.usernameTextBox, dataMap.get("Username"), timeOut, "Entered "+dataMap.get("Username")+" into username field", "Could not enter "+dataMap.get("Username")+" into username field");
		sendKeysToTextBox(orangeAppLoginPageObjects.passwordTextBox, dataMap.get("Password"), timeOut, "Entered "+dataMap.get("Password")+" into Password field", "Could not enter "+dataMap.get("Password")+" into Password field");
		clickOnElement(orangeAppLoginPageObjects.button_login, timeOut, "Clicked on login button", "Could not click on login button");
	}
	
	public void testNegativeLogin(HashMap<String, String> dataMap) throws Exception {
		sendKeysToTextBox(orangeAppLoginPageObjects.usernameTextBox, dataMap.get("Username"), timeOut, "Entered "+dataMap.get("Username")+" into username field", "Could not enter "+dataMap.get("Username")+" into username field");
		sendKeysToTextBox(orangeAppLoginPageObjects.passwordTextBox, dataMap.get("Password"), timeOut, "Entered "+dataMap.get("Password")+" into Password field", "Could not enter "+dataMap.get("Password")+" into Password field");
		clickOnElement(orangeAppLoginPageObjects.button_login, timeOut, "Clicked on login button", "Could not click on login button");
		verifyElementDisplayed(orangeAppLoginPageObjects.text_invalidCreds, timeOut, "Tested Negative login for "+dataMap.get("Username"), "Test failed for Negative login for "+dataMap.get("Username"));
	}
	
}
