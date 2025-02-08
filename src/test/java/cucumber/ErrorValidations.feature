@tag
Feature: Error validation
  I want to use this template for my feature file

 @ErrorValidation
  Scenario Outline: I landed on E-commerce Page
    Given I landed on E-commerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." messaged is displayed

    Examples: 
      | name 									 |     password    | 
      | tejas_random@gmail.com |     Tejas@12345 | 
       
