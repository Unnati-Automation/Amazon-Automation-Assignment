Feature: HRM Sign In

Scenario Outline: Login Check
Given User is on Amazon page <url>
When User searches for Nikon, sort the filter from highest to Lowest
And User clicks on second product
Then Verify the product name as Nikon D3X

Examples:

| url |
| https://www.amazon.com |