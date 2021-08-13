package pageFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.ImdbPage;
import pageObjects.WikipediaPage;
import utilities.MasterClass;

public class WikipediaPageFunctions extends MasterClass{

	WikipediaPage wikipediaPageObjects;
	int timeOut = 10;

	public WikipediaPageFunctions(WebDriver driver)
	{
		this.driver = driver;
		wikipediaPageObjects = PageFactory.initElements(driver, WikipediaPage.class);
	}
	
	public String getDirectorName() throws Exception {
		waitForElement(wikipediaPageObjects.logo_Wikipedia, timeOut);
		return getText(wikipediaPageObjects.directorName, timeOut);
	}
	
}
