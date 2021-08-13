@ValidationActions
Feature: Wikipedia vs IMDB

  @ValidateMovieDirectorNames
  Scenario: Validate director name of movies in Wikipedia vs IMDB
  	Given a list of movie names
    When user tries to extract the director names on wikipedia and imdb
    Then the director names should match
    
  @LoginToOrangeApp
  Scenario: Validate Login functionality of Orange App
  	Given a list of usernames and passwords
    When user tries to login to Orange App with given creds
    Then the user should be login
