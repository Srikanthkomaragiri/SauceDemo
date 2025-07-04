@CART_PAGE_REMOVE_PRODUCT

Feature: Verify the acrt page and Remove the product

Background:
When Enter valid "standard_user" and "secret_sauce"  
Then Click on login.
When User selcted t-shirt
And  User selected bag
Then products sucessfully added into the cart



Scenario: Go to Cart page and remove one product.

Given user navigated to the cart page
When  User removed one of the Added Product
Then Product was successfully removed

