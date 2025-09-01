# Addproduct.feature
@ADDPRODUCT

Feature: User can able to add his selcted products into the cart.

Background:
  When Enter valid "standard_user" and "secret_sauce"  
  Then Click on login.


Scenario: Add products into Addcart.
Given User should succesfully navigated to the products page.
When User selcted t-shirt
And  User selected bag
Then products sucessfully added into the cart
