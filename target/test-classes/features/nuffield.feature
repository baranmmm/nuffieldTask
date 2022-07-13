Feature: nuffield heath feature

  @nuffield
  Scenario: nuffield health scenario
    Given the user search for nearest gym
    When the user search for "DE1" on searchbox
    Then the first result should be the nearest one
#