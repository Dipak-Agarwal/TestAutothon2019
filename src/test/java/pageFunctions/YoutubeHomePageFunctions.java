package pageFunctions;

import java.io.FileWriter;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.restassured.RestAssured;
import pageObjects.YoutubeHomePage;
import utilities.MasterClass;

public class YoutubeHomePageFunctions extends MasterClass{

	YoutubeHomePage objYoutubeHomePage;
	int timeOut = 10;
	
	public YoutubeHomePageFunctions(WebDriver driver)
	{
		this.driver = driver;
		objYoutubeHomePage = PageFactory.initElements(driver, YoutubeHomePage.class);
	}

	public void searchForChannelName(String channelName) throws Exception {
		sendKeysToTextBox(objYoutubeHomePage.search, channelName, timeOut, "Entered "+channelName+" into search field", "Could not enter "+channelName+" into search field");
		keyboardPressEnter("Pressed ENTER key", "Could not press ENTER key");
	}
	
	public void openChannel(String channelName) throws Exception {
		clickOnElement(objYoutubeHomePage.channelSearch, timeOut, "Click on "+channelName, "Could not click on "+channelName);
	}
	
	public void navigateToVideosTab(String tabName) throws Exception {
		clickOnElement(objYoutubeHomePage.videosTab, timeOut, "Navigated to "+tabName+" tab", "Could not navigate to "+tabName);
	}
	
	public String getVideoNameFromVideoAPI(String apiUrl) throws Exception {
		return RestAssured.given().get(apiUrl).body().asString();
	}
	
	public void locateVideoAndClick(String fetchVideo) throws Exception {
//		String xpathForVideo = "//div[@id='items'][contains(@class,'grid-renderer')]//a[text()='"+videoName+"']";
//		String xpathForAllVideos = "//div[@id='items'][contains(@class,'grid-renderer')]//a[@id='video-title']";
//		scrollDownUntilFound(xpathForAllVideos,videoName, 30, videoName+" Found", videoName+" not found");
//		String xpathForVideo = "//div[@id='items'][contains(@class,'grid-renderer')]//a[text()='DevOps Tool Chain']";
//		clickOnElement(xpathForVideo, timeOut, "Clicked on "+videoName, "Could not click on "+videoName);
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
	}
	
	public List<String> getTitleOFUpNextVideos(){
		return getTitleOFUpNext(objYoutubeHomePage.upNextVideos);
	}
	
	public void writeToFile(List<String> videoNames)
	{
		JSONObject team = new JSONObject();
		team.put("team", "teamName");
		team.put("video", "videoName");
		team.put("upcoming-videos", videoNames);
		
		try {
			FileWriter file = new FileWriter("/TestData/jsonFile.json");
            file.write(team.toJSONString());
            file.flush();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
	}
	
	public void changeVideoQuality()
	{
		String filepath = "/screenshot/";
        
        Screen s = new Screen();
        Pattern Settings = new Pattern(filepath + "Settings.PNG");
        Pattern Quality = new Pattern(filepath + "Quality.PNG");
        Pattern QualityRes = new Pattern(filepath + "360p.PNG");
        s.click(Settings);
        s.wait(Quality, 20);
        s.click(Quality);
        s.wait(QualityRes, 10);
        s.click(QualityRes);

	}
	
	
	
	
	
}
