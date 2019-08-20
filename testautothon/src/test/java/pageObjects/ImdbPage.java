package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.MasterClass;

public class ImdbPage extends MasterClass{

//	@FindBy (xpath="//a[text()='IMDb']")
//	public WebElement header_IMDB_Page;
//	
//	@FindBy (xpath="//h4[text()='Director:']/following::a")
//	public WebElement directorName;
	
	public By header_IMDB_Page = By.xpath("//a[text()='IMDb']");
	
	public By directorName = By.xpath("//h4[text()='Director:']/following::a");
	
}

