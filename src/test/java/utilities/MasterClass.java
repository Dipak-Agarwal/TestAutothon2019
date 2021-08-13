package utilities;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class MasterClass {

	public WebDriver driver;

	 ReadProperties readProp = new ReadProperties();
	 String url = readProp.getPropertyValue("URL");
//	 String browser = readProp.getPropertyValue("Browser");
	 	 
	public WebDriver setUp(String browserName) throws Exception {
			driver = BrowserInitiation.browserLoad(browserName);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		}

	public void navigateToUrl() throws Exception {
		driver.navigate().to(url);
	}

	public FluentWait<WebDriver> getFluentWaitFromDriver(int timeOut, int pollingInterval) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingInterval)).ignoring(NoSuchElementException.class);
		return wait;
	}
	
	public void waitForElementToBeVisible(By element, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			 highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public void verifyElementVisible(By element, int timeOut, String failureMessage) throws Exception {
		try {
			waitForElementToBeVisible(element, timeOut);
			Assert.assertTrue(driver.findElement(element).isDisplayed(), failureMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public void waitForElementToBeInvisible(By element, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
//			 highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public void verifyElementInvisible(By element, int timeOut, String failureMessage) throws Exception {
		try {
			waitForElementToBeInvisible(element, timeOut);
			Assert.assertFalse(driver.findElement(element).isDisplayed(), failureMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
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
	
	public static String captureScreenshot(WebDriver driver, String fileName) {

        String pathOfScreenShot = null;
        try {
               File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
               pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\"+ fileName + ".png";
               FileUtils.copyFile(scrFile, new File(pathOfScreenShot));
        } catch (Exception e) {
               System.out.println("Screenshot Failed "+e.getMessage());
        }
        return pathOfScreenShot;
  }

	public void tearDown() {

		driver.quit();
		System.out.println("Browser Closed");
	}

	public void scrollDownTillEnd() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
}
