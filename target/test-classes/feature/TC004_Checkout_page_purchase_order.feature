@OrderPlaced
Feature:User go to the Checkout page and purchased the order

Background:
When Enter valid "standard_user" and "secret_sauce"  
Then Click on login.
When User selcted t-shirt
And  User selected bag
Then products sucessfully added into the cart
Given user navigated to the cart page
When  User removed one of the Added Product
Then Product was successfully removed

Scenario: Purchase order
Given Click on the Checkout button 
And  User Navigated to the Checkout page
When User Enetered the following valid checkout information details
    |First Name | Last Name |Zip code |
    | Fisrt     | Last      | 30304   |
    
And User click on continue
And User verified the user details before payment
Then User clicked on FInish and Order placed successfully
And  User click on Back to home and navigated back to homepage

    
