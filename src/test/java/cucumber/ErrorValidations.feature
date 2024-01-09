
@tag
Feature: error validation
  I want to use this template for my feature file
 

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When I Logged in with user name <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                  |  password       | 
      | shetty@gmail.com      |  Iamking@0      |
