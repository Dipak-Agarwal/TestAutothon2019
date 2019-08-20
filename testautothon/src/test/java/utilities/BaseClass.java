package utilities;

import org.openqa.selenium.WebDriver;

public class BaseClass {

	public WebDriver driver;
	
	//@BeforeSuite
	public void startSession()
	{
		System.out.println("Suite Started");
		driver=BrowserInitiation.startBrowser("Chrome");
		System.out.println("Browser Started");

	}
	
}
