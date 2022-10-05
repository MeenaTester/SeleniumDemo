Feature: Your Logo Ecommerce Website

Background:
Given Landed on Your Logo Website

@Ad
Scenario: postive test when clicked on Ad Page
Given Clicked on Adposter
When Navigate to correct Ad page
Then Navigate back to Your Logo URL



@SignIn
Scenario Outline: Sign In page check with correct and wrong credentials
Given Click on sign In button
When Give <username> and <password> and <isWrongCredential> and click signIn button
Then If correct credential page should signed in with correct registered name else error message should displayed
Examples:
|username            |password |isWrongCredential|
|revana10@gmail.com  |Texas@123|false|
|wrongEmail@gmail.com|Texas@123|true|


@Purchase
Scenario Outline: purchase item order with correct credentials
Given Select dresses and in that summer dresses
When Select <dressToSelect> and add to cart and call checkout
Then Sign in to checkout page and confirm the order with <username> <password>
And Amount is paid and "Your order on My Store is complete." message is displayed
Examples:
|username            |password |dressToSelect        |
|revana10@gmail.com  |Texas@123|Printed Chiffon Dress|

@Purchase
Scenario Outline: purchase item order with wrong credentials
Given Select dresses and in that summer dresses
When Select <dressToSelect> and add to cart and call checkout
Then Sign in with wrong credentials <username> <password>
And error message should displayed
Examples:
|username            |password  |dressToSelect        |
|wrong10@gmail.com   |Texas     |Printed Summer Dress |

