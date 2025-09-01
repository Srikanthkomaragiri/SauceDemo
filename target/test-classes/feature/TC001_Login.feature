# login.feature
@LOGIN 
Feature: Login to the Account

Scenario Outline: Valid login with different credentials
Given Log in to the Website.
When Enter valid "<username>" and "<password>" 
Then Click on login.

Examples:
  | username      | password     |
  | standard_user | secret_sauce |
  
  
