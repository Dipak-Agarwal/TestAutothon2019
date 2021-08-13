package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.MasterClass;

public class GoogleSearch{

//	@FindBy (xpath="//input[@name='q'][@type='text']")
//	public WebElement textBox_Search;
	
	public By search=By.xpath("//input[@name='q'][@type='text']");
	public By wikipedia_link_movie=By.xpath("(//a[contains(@href,'https://en.wikipedia.org/wiki/')])[1]");
	public By imdb_link_movie=By.xpath("(//a[contains(@href,'https://www.imdb.com/title')])[1]");
	
//	@FindBy (xpath="//a[contains(@href,'https://en.wikipedia.org/wiki/')]")
//	public WebElement wikipedia_link_movie;
//	
//	@FindBy (xpath="//a[contains(@href,'https://www.imdb.com/title')]")
//	public WebElement imdb_link_movie;
	
}

