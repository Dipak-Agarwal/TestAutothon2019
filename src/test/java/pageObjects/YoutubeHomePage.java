package pageObjects;

import org.openqa.selenium.By;

import utilities.MasterClass;

public class YoutubeHomePage extends MasterClass{

	public By search=By.xpath("//input[@placeholder='Search']");
	public By channelSearch=By.xpath("(//span[text()='STeP-IN Forum'][contains(@class,'channel-renderer')])[1]");
	public By videosTab=By.xpath("//paper-tabs[@id='tabs']//paper-tab[2]");
	public By upNextVideos=By.xpath("//span[@id='video-title']");	
}

