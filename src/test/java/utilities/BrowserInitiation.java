package utilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserInitiation {

//	WebDriver driver;
	
	public static WebDriver startBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			return new ChromeDriver();
		}
		
		return null;
	}
	
	
	public static WebDriver zeleniumBrowsers(String url,String browser) throws Exception
	{
		
		
		
		
		if (browser.equalsIgnoreCase("Firefox")) {
			
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName(BrowserType.FIREFOX);
			
			return new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), cap);
			
		} else if (browser.equalsIgnoreCase("Chrome")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName(BrowserType.CHROME);
			
			return new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), cap);

		} 
		return null;
//		return driver;
	}
	
	public static WebDriver browserLoad(String url, String ieDriver, String ieDriverServer, String chromeDriver, String chromeDriverServer, String username, String password, String browser) throws Exception
	{
		
		
		if (browser.equalsIgnoreCase("Firefox")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);

			return new FirefoxDriver();
			
		} else if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions option = new ChromeOptions();

//			Map<String, Object> prefs = new HashMap<String, Object>();
//
//			prefs.put("credentials_enable_service", false);
//
//			prefs.put("password_manager_enabled", false);
//
//			prefs.put("profile.default_content_settings.popups", 0);
//
//			option.setExperimentalOption("prefs", prefs);
//
//			DesiredCapabilities cap = new DesiredCapabilities();
//
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//
//			cap.setCapability(ChromeOptions.CAPABILITY, option);
//
//			//ConfigDataProvider config=new ConfigDataProvider();
//			
			System.setProperty(chromeDriver, chromeDriverServer);
			
//			driver = new ChromeDriver(cap);
			return new ChromeDriver();

		} else if (browser.equalsIgnoreCase("IE")) {

//			DesiredCapabilities cap = new DesiredCapabilities();
//
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//
//			//ConfigDataProvider config=new ConfigDataProvider();
			
			System.setProperty(ieDriver, ieDriverServer);
			
//			driver = new InternetExplorerDriver(cap);
			return new InternetExplorerDriver();

		}
		
		return null;
		
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		return driver;
	}
}
