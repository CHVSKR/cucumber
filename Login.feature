Feature: Test NoCommerce Website

@smoke
Scenario: Test the login and logout Functionality
    Given User Launch Chrome browser 
    When URL is "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User Enter Email as "admin@yourstore.com" and Password as "admin"
    And click on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When user click on Logout button
    Then Login Page title should be "Your store. Login"
    And close browser
    
@smoke   
Scenario Outline: Test the login and logout Functionality
    Given User Launch Chrome browser 
    When URL is "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User Enter Email as "<email>" and Password as "<password>"
    And click on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When user click on Logout button
    Then Login Page title should be "Your store. Login"
    And close browser
    
    Examples:
    | email | password |
    | admin@yourstore.com | admin |
    | admin@yourstore.com1 | admin1 |