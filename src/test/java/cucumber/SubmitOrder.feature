#Author: 01ashu31@gmail.com


@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  #@tag1
  #Scenario: Title of your scenario
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
    
    Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given I Logged in with user name <name> and password <password>
    When I add the product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER" message is displayed on ConfirmationPage

    Examples: 
      | name                  |  password       | productName  |
      | shetty@gmail.com      |  Iamking@000    | ZARA COAT 3  |
      