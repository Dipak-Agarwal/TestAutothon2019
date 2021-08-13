@TestAfrApp
Feature: Test AFR App

  @ValidateHomePage
  Scenario Outline: Validate Home Page of AFR App
  	Given the user navigates to the home page of AFR App in "<browser>" browser
  	And verify the subscription popup is displayed
    When user naviagtes to the bottom to the web page
    Then after sometime the subscription popup disappears
    
    Examples:
    | browser |
    | chrome	|
    | firefox	|
    | edge		|
    