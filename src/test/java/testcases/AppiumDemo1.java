package testcases;

import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;

public class AppiumDemo1 {

       
       @Test
       public void demo1() throws MalformedURLException, InterruptedException
       {
             
             DesiredCapabilities cap=new DesiredCapabilities();
             
       
            cap.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
             
              cap.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);

             cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

             cap.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");

             cap.setCapability(MobileCapabilityType.VERSION,"9");

             
             
         AppiumDriver<WebElement> driver=new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
             
         
         driver.get("https://m.youtube.com/");
         
         int x1= driver.manage().window().getPosition().getX();
         
         int y1= driver.manage().window().getPosition().getX();

         
         System.out.println(x1);
         
         System.out.println(y1);
         
         System.out.println(driver.manage().window().getSize().getHeight());
         
         System.out.println(driver.manage().window().getSize().getWidth());

         
         driver.findElements(By.xpath("//*[@aria-label='Search YouTube']")).get(1).click();
         
         driver.findElement(By.xpath("//input[@name='search']")).sendKeys("step-inforum");
         
         Thread.sleep(5000);

         
         driver.findElements(By.xpath("//*[@aria-label='Search YouTube']")).get(0).click();     
         
         Thread.sleep(5000);
         
         driver.findElement(By.xpath("//*[text()='STeP-IN Forum']")).click();
         Thread.sleep(5000);

         driver.findElement(By.xpath("//a[text()='Videos']")).click();
       
         Thread.sleep(5000);
       
        
         String fetchVideo = RestAssured.given().get("http://54.169.34.162:5252/video").body().asString();
               
         int count=driver.findElements(By.xpath("//h4[text()='"+fetchVideo+"']")).size();
         
         while(count<0)
         {
               ((JavascriptExecutor)driver).executeScript("scroll(0,100)");
               
               count=driver.findElements(By.xpath("//h4[text()='"+fetchVideo+"']")).size();

               
         }
         
         boolean status=false;
         
         ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
         
         while(!status)
         try 
         {
             driver.findElements(By.xpath("//h4[text()='"+fetchVideo+"']//..//..//..//..//img")).get(0).click();
             status=true;
       } catch (Exception e) {
             ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElements(By.xpath("//h4[text()='"+fetchVideo+"']//..//..//..//..//img")).get(0));
               //((JavascriptExecutor)driver).executeScript("scroll(0,100)");

             status=false;
       }

         
        driver.findElement(By.xpath("//button[@aria-label='Account']")).click();
       
        
        List<WebElement> list=driver.findElements(By.xpath("//div[@class='menu-content']//button"));
       
        for(WebElement ele:list)
       {
             System.out.println(ele.getText());
             
              if(ele.getText().contains("Playback"))
             {
                    ele.click();
                    break;
             }
       }
       
         Thread.sleep(5000);
         
         System.out.println(driver.getContext());
         System.out.println(driver.getContextHandles());
       
         System.out.println(driver.getWindowHandles());
         
         

       
         Thread.sleep(3000);
         
        new Select(driver.findElement(By.xpath("//label[text()='Quality']//following::select[1]"))).selectByVisibleText("360p");

       Thread.sleep(3000);
       
        driver.findElement(By.xpath("//*[text()='OK']")).click();
        
        List<WebElement> elements = driver.findElements(By.xpath("//span[@id='video-title']"));
		List<String> upNext = new ArrayList<String>();
		for(WebElement ele : elements)
		{
			upNext.add(ele.getAttribute("title"));
		}
        
        JSONObject team = new JSONObject();
		team.put("team", "teamName");
		team.put("video", "videoName");
		team.put("upcoming-videos", upNext);
		
		try {
			FileWriter file = new FileWriter("/TestData/jsonFile.json");
            file.write(team.toJSONString());
            file.flush();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
       

       }
}
