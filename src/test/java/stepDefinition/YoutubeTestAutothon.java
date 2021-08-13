package stepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageFunctions.YoutubeHomePageFunctions;
import utilities.MasterClass;

public class YoutubeTestAutothon extends MasterClass{

	WebDriver driver;
	
	YoutubeHomePageFunctions objYoutubeHomePageFunctions;
	String videoFetched = "";
	List<String> listOfUpNextVideos;
	
	@Given("^Open Youtube$")
	public void open_Youtube() throws Throwable {
		
		System.out.println(Thread.currentThread().getId() + " "+Thread.currentThread().getName());
		this.driver = setUp();
		objYoutubeHomePageFunctions = new YoutubeHomePageFunctions(driver);
		navigateToUrl();
	
	}

	@When("^Search for \"([^\"]*)\"$")
	public void search_for(String channelName) throws Throwable {
	    
		objYoutubeHomePageFunctions.searchForChannelName(channelName);
		
	}
	
	@When("^Open \"([^\"]*)\" channel$")
	public void open_channel(String channelName) throws Throwable {
	    
		objYoutubeHomePageFunctions.openChannel(channelName);
		
	}

	@When("^Navigate to \"([^\"]*)\" tab$")
	public void navigate_to_tab(String tabName) throws Throwable {
	    
		objYoutubeHomePageFunctions.navigateToVideosTab(tabName);
		
	}

	@When("^Make an API call \"([^\"]*)\" and fetch video name$")
	public void make_an_API_call_and_fetch_video_name(String videoAPI) throws Throwable {
	    
		videoFetched = objYoutubeHomePageFunctions.getVideoNameFromVideoAPI(videoAPI);
		System.out.println(videoFetched);
	}

	@When("^Locate the video fetched in the video call in the videos list of channel$")
	public void locate_the_video_fetched_in_the_video_call_in_the_videos_list_of_channel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objYoutubeHomePageFunctions.locateVideoAndClick(videoFetched);
	}

	@When("^Capture screenshot of video located$")
	public void capture_screenshot_of_video_located() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Click on the video$")
	public void click_on_the_video() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Change the video quality to \"([^\"]*)\"$")
	public void change_the_video_quality_to(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	}

	@When("^Get the list of all upcoming videos in Up Next section$")
	public void get_the_list_of_all_upcoming_videos_in_Up_Next_section() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		listOfUpNextVideos = objYoutubeHomePageFunctions.getTitleOFUpNextVideos();
		System.out.println("Up Next : "+listOfUpNextVideos);
	}

	@When("^Write the list to a JSON file$")
	public void write_the_list_to_a_JSON_file() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("^Upload the list to the server \"([^\"]*)\"$")
	public void upload_the_list_to_the_server(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^Validate the file upload \"([^\"]*)\"$")
	public void validate_the_file_upload(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^Generate the test execution report$")
	public void generate_the_test_execution_report() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	
}
