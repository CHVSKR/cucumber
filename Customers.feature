Feature: Customers

Background: Below are common steps for every scenario
    Given User Launch Chrome browser 
    When URL is "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User Enter Email as "admin@yourstore.com" and Password as "admin"
    And click on Login button
    Then User can view Dashboard
    When user click on Customer icon
    And Click on Customer menu item
    
@sanity
Scenario: Add Customer
    And click on AddNew button 
    Then user can view Add new customer page
    When user enter customer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully" 
    And close browser

@sanity
Scenario: Search a Customer by Email
    When user enter customer email 
    And click search button
    Then search record should be displayed in table
    And close browser

@regression
Scenario: Search a Customer by Name
    When user enter customer first Name
    And user enter customer last Name
    And click search button
    Then search name should be displayed in table
    And close browser

@regression
Scenario: Search a Customer by Company
    When user enter customer Company
    And click search button
    Then search Company should be displayed in table
    And close browser

 @regression
 Scenario: Search a Customer by DateOfBirth
    When user enter customer Month
    And user enter customer Date
    And click search button
    Then search record should be displayed in table
    And close browser

@regression
 Scenario: Search a Customer by CustomerRole
    When user enter CustomerRole
    And click search button
    Then search CustomerRole should be displayed in table
    And close browser
    