
@tag
Feature:Purchase the order from Ecommerce website
 I want to use this template for my feature file
 
 Background:
Given I landed on Ecommerce page
 
 @Regression
 Scenario Outline: Positive test of submiting the order
 Given Logged in with username <username> and password <password>
 When I add product <productname> to cart
 And Checkout <productname> and submit the order
 Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
 
 Examples:
  |username                   | password| productname |
  |ramaganesh209@gmail.com    |Ganesh143| ZARA COAT 3 |
    
 