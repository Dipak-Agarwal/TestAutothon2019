package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.MasterClass;

public class WikipediaPage extends MasterClass{

//	@FindBy (xpath="//a[@href='/wiki/Main_Page']")
//	public WebElement logo_Wikipedia;
//	
//	@FindBy (xpath="//th[text()='Directed by']/following-sibling::td/a")
//	public WebElement directorName;
	
	public By logo_Wikipedia = By.xpath("//a[@href='/wiki/Main_Page']");
	
	public By directorName = By.xpath("//th[text()='Directed by']/following-sibling::td/a");
	
}

