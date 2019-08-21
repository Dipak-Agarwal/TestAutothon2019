package stepDefinition;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pageFunctions.GoogleSearchFunctions;
import pageFunctions.ImdbPageFunctions;
import pageFunctions.WikipediaPageFunctions;
import utilities.MasterClass;
import utilities.ReadCSV;

public class ValidateDirectorNames extends MasterClass {

//	ImdbPageFunctions objImdbPageFunctions = new ImdbPageFunctions();
//	GoogleSearchFunctions objGoogleSearchFunctions = new GoogleSearchFunctions();
//	WikipediaPageFunctions objWikipediaPageFunctions = new WikipediaPageFunctions();
	
	WebDriver driver;
	
	ImdbPageFunctions objImdbPageFunctions;
	GoogleSearchFunctions objGoogleSearchFunctions;
	WikipediaPageFunctions objWikipediaPageFunctions;
	
	HashMap<String, String> dataMap;
	ArrayList<HashMap<String, String>> arrayListData;
//	BaseClass base=new BaseClass();
	
	@Given("^a list of movie names$")
	public void a_list_of_movie_names() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		arrayListData = ReadCSV.readFile("@ValidateDirectorNames");
		this.driver = setUp();
		objImdbPageFunctions = new ImdbPageFunctions(driver);
		objGoogleSearchFunctions = new GoogleSearchFunctions(driver);
//		objGoogleSearchFunctions = new GoogleSearchFunctions();
		objWikipediaPageFunctions = new WikipediaPageFunctions(driver);
//		base.startSession();
	}

	@When("^user tries to extract the director names on wikipedia and imdb$")
	public void user_tries_to_extract_the_director_names_on_wikipedia_and_imdb() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		boolean match = false;
		for (int i = 0; i < arrayListData.size(); i++) {
			dataMap = arrayListData.get(i);
			navigateToUrl();
			objGoogleSearchFunctions.EnterSearchTextWikipedia(dataMap);
			objGoogleSearchFunctions.clickLinkWikipedia(dataMap);
			String wikipediaDirectorName = objWikipediaPageFunctions.getDirectorName();
			navigateToUrl();
			objGoogleSearchFunctions.EnterSearchTextImdb(dataMap);
			objGoogleSearchFunctions.clickLinkImdb();
			String imdbDirectorName = objImdbPageFunctions.getDirectorName();
			match = wikipediaDirectorName.equalsIgnoreCase(imdbDirectorName);
			if(!match)
			{
				System.out.println(dataMap.get("Movie Name") + " : Director name in IMDB is "+imdbDirectorName+ " and in wikipedia is "+wikipediaDirectorName);
			}
		}
		
	}

	@Then("^the director names should match$")
	public void the_director_names_should_match() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Director Names validated successfully");
	}
	
	
}
