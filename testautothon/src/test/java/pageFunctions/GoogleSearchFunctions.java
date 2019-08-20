package pageFunctions;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.GoogleSearch;
import utilities.Constants;
import utilities.MasterClass;

public class GoogleSearchFunctions extends MasterClass{

//	WebDriver driver;
	GoogleSearch googleSearchObjects;
	int timeOut = Constants.SLEEP_SLOW;
	
	public GoogleSearchFunctions(WebDriver driver)
	{
		this.driver = driver;
		googleSearchObjects = PageFactory.initElements(driver, GoogleSearch.class);
	}
	
	public void EnterSearchText(HashMap<String, String> dataMap) throws Exception {
//		sendKeysToTextBox(googleSearchObjects.textBox_Search, dataMap.get("Movie Name"), timeOut, "Entered "+dataMap.get("Movie Name")+" into Search field", "Could not enter "+dataMap.get("Movie Name")+" into Search field");
	}

	public void EnterSearchTextWikipedia(HashMap<String, String> dataMap) throws Exception {
		sendKeysToTextBox(googleSearchObjects.search, dataMap.get("Movie Name") + " Wikipedia", timeOut, "Entered "+dataMap.get("Movie Name")+" into Search field", "Could not enter "+dataMap.get("Movie Name")+" into Search field");
		keyboardPressEnter("Pressed ENTER", "Could not press ENTER");
	}
	
	public void EnterSearchTextImdb(HashMap<String, String> dataMap) throws Exception {
		sendKeysToTextBox(googleSearchObjects.search, dataMap.get("Movie Name") + " IMDB", timeOut, "Entered "+dataMap.get("Movie Name")+" into Search field", "Could not enter "+dataMap.get("Movie Name")+" into Search field");
		keyboardPressEnter("Pressed ENTER", "Could not press ENTER");
	}
	
	public void clickLinkWikipedia(HashMap<String, String> dataMap) throws Exception {
		clickOnElement(googleSearchObjects.wikipedia_link_movie, timeOut, "Clicked wikipedia link", "Could not click wikipedia link");
	}
	
	public void clickLinkImdb() throws Exception {
		clickOnElement(googleSearchObjects.imdb_link_movie, timeOut, "Clicked IMDB link", "Could not click IMDB link");
	}

}
