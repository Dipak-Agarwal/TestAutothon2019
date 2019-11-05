@YoutubeActions
Feature: Perform operations on youtube

  @PerformActions
  Scenario: Perform the mentioned actions in youtube
  	Given Open Youtube
    When Search for "step-inforum"
    And Open "step-inforum" channel
    And Navigate to "Videos" tab
    And Make an API call "http://54.169.34.162:5252/video" and fetch video name
    And Locate the video fetched in the video call in the videos list of channel
#    And Capture screenshot of video located
#    And Click on the video
#    And Change the video quality to "360p"
    And Get the list of all upcoming videos in Up Next section
    And Write the list to a JSON file
    And Upload the list to the server "http://54.169.34.162:5252/upload"
    And Validate the file upload "http://54.169.34.162:5252/result/" 
    Then Generate the test execution report
