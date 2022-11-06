Feature: Test Sales Website

@smoke
Scenario: Test the Dynamic Webtable
    Given User Launched Chrome browser 
    When URL is entered
    And User Entered Email as "demo" and Password as "demo"
    And clicked on Login button
    Then Page title should be verified as "Dashboard"
    When user click on Sales icon
    And Click on order item
    And close browser