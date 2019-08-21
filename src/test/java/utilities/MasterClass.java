package utilities;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;

public class MasterClass {

	public WebDriver driver;

	 ReadProperties readProp = new ReadProperties();
	 String url = readProp.getPropertyValue("URL");
	 String ieDriver = readProp.getPropertyValue("IEDriver");
	 String ieDriverServer = readProp.getPropertyValue("IEDriverServer");
	 String chromeDriver = readProp.getPropertyValue("ChromeDriver");
	 String chromeDriverServer = readProp.getPropertyValue("ChromeDriverServer");
	 String username = readProp.getPropertyValue("Username");
	 String password = readProp.getPropertyValue("Password");
	 String browser = readProp.getPropertyValue("Browser");
	// protected String csvFileLocation = readProp.getPropertyValue("CSVFile");
	// String csvFileLocation = null;

//	public MasterClass(WebDriver driver) throws Exception
//	{
//		this.driver = setUp();
//	}
//	
//	public MasterClass() {
//		// TODO Auto-generated constructor stub
//	}

	 @BeforeTest
	public WebDriver setUp() throws Exception {
		baseURI = readProp.getPropertyValue("StagingAPI");
		basic(username, password);
//		driver = BrowserInitiation.browserLoad(url, ieDriver, ieDriverServer, chromeDriver, chromeDriverServer, username, password, browser);
		driver = BrowserInitiation.zeleniumBrowsers(baseURI, browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		return BrowserInitiation.browserLoad(url, ieDriver, ieDriverServer, chromeDriver, chromeDriverServer, username, password, browser);
		return driver;
	}

	public void navigateToUrl() throws Exception {
		driver.navigate().to(url);
	}

	/*
	 * public void tearDown() { driver.quit(); }
	 */

	public void waitForElementAsXpath(String xpath, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(Throwable.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void waitForElement(By element, int timeOut) throws Exception {
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(Throwable.class);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
			// wait.until(ExpectedConditions.elementToBeClickable(element));
			// highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public void waitForElement(WebElement element, int timeOut) throws Exception {
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(Throwable.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			// wait.until(ExpectedConditions.elementToBeClickable(element));
			// highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void waitForElementNew(By element, int timeOut) throws Exception {

		int counter = 0;
		int x = 0;
		while (x <= 0 && counter < timeOut) {
			try {
				System.out.println("Displayed : "+driver.findElement(element).isDisplayed());
				x = driver.findElement(element).getLocation().getX();
				if (x > 0) {
					System.out.println("WebElement Found");
					break;
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				counter++;
				System.out.println("Searching for WebElement");
			}

		}

	}
	
	public void waitForElementNew(WebElement element, int timeOut) throws Exception {

		int counter = 0;
		int x = 0;
		while (x <= 0 && counter < timeOut) {
			try {
				System.out.println("Displayed : "+element.isDisplayed());
				x = element.getLocation().getX();
				if (x > 0) {
					System.out.println("WebElement Found");
					break;
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				counter++;
				System.out.println("Searching for WebElement");
			}

		}

	}

	public void highlightElement(By element) throws Exception {
		sleep(1000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", driver.findElement(element));
	}

	public void sleep(long time) throws Exception {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public WebElement findElement(String xpath, int timeOut) throws Exception {
		WebElement element = null;
		try {
			waitForElementAsXpath(xpath, timeOut);
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			int elementSize = elements.size();
			if (elementSize > 0) {
				element = elements.get(0);
			} else
				element = driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			if (element == null) {
				System.out.println("Not able to find Element with xpath : " + xpath);
				System.out.println(e.getMessage());
				throw e;
			}
		}
		return element;
	}

	public void clickOnElement(By element, int timeOut, String successMsg, String failureMsg) throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			driver.findElement(element).click();
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println("Not able to find element");
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}
	public void clickOnElement(WebElement element, int timeOut, String successMsg, String failureMsg) throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			element.click();
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println("Not able to find element");
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void clickOnElement(String xpath, int timeOut, String successMsg, String failureMsg) throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		clickOnElement(element, timeOut, successMsg, failureMsg);
	}

	/*public void sendKeysToTextBox(WebElement element, String textToBeSend, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			// waitForElement(element, timeOut);
			// element.clear();
			System.out.println(textToBeSend);
			System.out.println(driver);
//			waitForElementNew(element, timeOut);
			waitForElement(element, timeOut);
			element.sendKeys(textToBeSend);
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}*/

	public void sendKeysToTextBox(By element, String textToBeSend, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			// waitForElement(element, timeOut);
			// element.clear();
//			System.out.println(textToBeSend);
//			System.out.println(driver);
			waitForElementNew(element, timeOut);
//			waitForElement(element, timeOut);
			driver.findElement(element).sendKeys(textToBeSend);
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}
	
	public void sendKeysToTextBox(WebElement element, String textToBeSend, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			// waitForElement(element, timeOut);
			// element.clear();
			System.out.println(textToBeSend);
			System.out.println(driver);
			waitForElementNew(element, timeOut);
//			waitForElement(element, timeOut);
			element.sendKeys(textToBeSend);
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}
	
	public void keyboardPressEnter(String successMsg, String failureMsg) throws Exception {
		try {
			Thread.sleep(2000);
			new Actions(driver).sendKeys(Keys.ENTER).build().perform();
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void sendKeysToTextBox(String xpath, String textToBeSend, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		//sendKeysToTextBox(element, textToBeSend, timeOut, successMsg, failureMsg);
	}

	public void clearTextBox(WebElement element, int timeOut, String successMsg, String failureMsg) throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			element.clear();
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void selectVisibleTextFromDropDown(WebElement element, String visibleText, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			new Select(element).selectByVisibleText(visibleText);
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void selectVisibleTextFromDropDown(String xpath, String visibleText, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		selectVisibleTextFromDropDown(element, visibleText, timeOut, successMsg, failureMsg);
	}

	public void selectValueFromDropDown(WebElement element, String value, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			new Select(element).selectByValue(value);
			System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void selectValueFromDropDown(String xpath, String value, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		selectValueFromDropDown(element, value, timeOut, successMsg, failureMsg);
	}

	public void verifyElementDisplayed(WebElement element, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			if (element.isDisplayed()) {
				System.out.println(successMsg);
			}
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}
	
	public void verifyElementDisplayed(By element, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		try {
			// sleep(1000);
			waitForElement(driver.findElement(element), timeOut);
			if (driver.findElement(element).isDisplayed()) {
				System.out.println(successMsg);
			}
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void verifyElementDisplayed(String xpath, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		try {
			// sleep(1000);
			waitForElementAsXpath(xpath, timeOut);
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				System.out.println(successMsg);
			}
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
	}

	public void verifyElementNotDisplayed(WebElement element, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			if (element.isDisplayed()) {
				System.out.println(failureMsg);
				throw new Exception(failureMsg);
			} else
				System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(successMsg);
			throw e;
		}
		// sleep(1000);
	}

	public void verifyElementNotDisplayed(String xpath, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		try {
			// sleep(1000);
			waitForElementAsXpath(xpath, timeOut);
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				System.out.println(failureMsg);
				throw new Exception(failureMsg);
			} else
				System.out.println(successMsg);
		} catch (Exception e) {
			System.out.println(successMsg);
			throw e;
		}
		// sleep(1000);
	}

	public String getText(WebElement element, int timeOut) throws Exception {
		String text = null;
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			text = element.getText();
		} catch (Exception e) {
			System.out.println("Could not find element");
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
		return text;

	}
	
	public String getText(By element, int timeOut) throws Exception {
		String text = null;
		try {
			// sleep(1000);
			waitForElement(element, timeOut);
			text = driver.findElement(element).getText();
		} catch (Exception e) {
			System.out.println("Could not find element");
			System.out.println(e.getMessage());
			throw e;
		}
		// sleep(1000);
		return text;

	}

	public void getText(String xpath, int timeOut) throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		getText(element, timeOut);
	}

	public void verifyTextEquals(WebElement element, String expectedText, int timeOut, String successMsg,
			String failureMsg) throws Exception {
		try {
			// sleep(1000);
			String actualText = getText(element, timeOut);
			// System.out.println("actual = "+actualText);
			// System.out.println("expected = "+expectedText);
			if (actualText.equals(expectedText) || actualText.contains(expectedText))
				System.out.println(successMsg);
			else {
				System.out.println(failureMsg);
				throw new Exception(failureMsg);
			}
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void verifyTextEquals(String actualText, String expectedText, String successMsg, String failureMsg)
			throws Exception {
		try {
			if (actualText.equals(expectedText) || actualText.contains(expectedText))
				System.out.println(successMsg);
			else {
				System.out.println(failureMsg);
				throw new Exception(failureMsg);
			}
		} catch (Exception e) {
			System.out.println(failureMsg);
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void verifyTextEquals(String xpath, String expectedText, int timeOut, String successMsg, String failureMsg)
			throws Exception {
		WebElement element = null;
		element = findElement(xpath, timeOut);
		verifyTextEquals(element, expectedText, timeOut, successMsg, failureMsg);
	}

	// public void setupDriver() throws Throwable {
	// this.driver = this.setUp();
	// }
	//
	
	@AfterSuite
	public void tearDown() {

		driver.quit();
		System.out.println("Browser Closed");
	}

	public static void verifyStatusCode(int actualCode, int expectedCode) {
		Assert.assertEquals(actualCode, expectedCode);
	}

	public static void verifyStatusCode200(int actualCode) {
		Assert.assertEquals(actualCode, 200);
	}

	public static JsonPath getJsonPathObject(String responseJsonData) {
		return new JsonPath(responseJsonData);
	}

	public static String getJsonData(String responseJsonData, String key) {
		return getJsonPathObject(responseJsonData).get(key);
	}

	public static void verifyJsonData(String responseJsonData, String key, String expectedValue) {
		String actualValue = getJsonPathObject(responseJsonData).get(key);
		Assert.assertEquals(actualValue, expectedValue);
	}

	public static void verifyJsonDataContains(String responseJsonData, String key, String expectedValue) {
		String actualValue = getJsonPathObject(responseJsonData).get(key);
		Assert.assertTrue(actualValue.contains(expectedValue));
	}

	public static XmlPath getXmlPathObject(String responseXmlData) {
		return new XmlPath(responseXmlData);
	}

	public static String getXmlData(String responseXmlData, String key) {
		return getXmlPathObject(responseXmlData).get(key);
	}

	public static void verifyXmlData(String responseXmlData, String key, String expectedValue) {
		String actualValue = getXmlPathObject(responseXmlData).get(key);
		Assert.assertEquals(actualValue, expectedValue);
	}

	public static void verifyXmlDataContains(String responseXmlData, String key, String expectedValue) {
		String actualValue = getXmlPathObject(responseXmlData).get(key);
		Assert.assertTrue(actualValue.contains(expectedValue));
	}

}
