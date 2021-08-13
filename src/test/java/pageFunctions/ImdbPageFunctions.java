package pageFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.GoogleSearch;
import pageObjects.ImdbPage;
import utilities.MasterClass;

public class ImdbPageFunctions extends MasterClass{

	ImdbPage imdbPageObjects;
	int timeOut = 10;

	public ImdbPageFunctions(WebDriver driver)
	{
		this.driver = driver;
		imdbPageObjects = PageFactory.initElements(driver, ImdbPage.class);
	}
	
	public String getDirectorName() throws Exception {
		return getText(imdbPageObjects.directorName, timeOut);
	}
	
}
